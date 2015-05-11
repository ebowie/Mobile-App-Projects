/*
 * Assignment: Homework 3
 * File name: HW3.zip (PhotoGallery)
 * Names: David Farynyk and Eric Bowers
 * 
 * Class: Mobile Application and Development (ITCS 5180), Summer I 2014
 * Due: 6/15/2014
 * Description: Photo Gallery which retrieves URLs from the internet and displays photos
 * 
 * NOTE: The name of this Activity is ambiguous in the Homework 3 documentation: on page 2 it is stated that it must be named "PhotosActivity" while on page 3 (which is the page containing the detailed information) it is called PhotoActivity. I have chosen the second option since it appears more often in the assignment instructions.
 */


package com.example.photogallery;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PhotoActivity extends Activity {

	ArrayList<String> URLs;
	Handler photoHandler;
	Handler slideshowHandler;
	ImageView mainPhoto;
	final int SHOW_PROGRESS = 0;
	final int HIDE_PROGRESS = 1;
	final int LAUNCH_PHOTOS = 0;
	final int LAUNCH_SLIDESHOW = 1;
	final int SLIDESHOW_PHOTO_FROM_CACHE = 3;
	int launchType = -1;
	ProgressDialog progressDialog;
	int currPhoto = 0;
	boolean stillActive = true;
	
	// Gesture responses
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    
    // Memory Cache variable
    private LruCache< String, Bitmap > mMemoryCache = new LruCache< String, Bitmap >( 30 );
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo);
		
		// Receive array with all image Urls
		if( getIntent().getExtras() != null ) {
			URLs = getIntent().getExtras().getStringArrayList( "URLs" );
			launchType = getIntent().getExtras().getInt( "launchType", -2 );
			Log.d("demo", "Photo Activity launched with a type of " + launchType );
		}
		
		photoHandler = new Handler( new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				
				switch( launchType ) {
					case LAUNCH_PHOTOS:
						switch( msg.what ) {
							case SHOW_PROGRESS:
								progressDialog = new ProgressDialog( PhotoActivity.this );
								progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
								progressDialog.setCancelable( false );
								progressDialog.setMessage( (String) msg.obj );
								progressDialog.show();
								break;
							case HIDE_PROGRESS:
								progressDialog.hide();
								mainPhoto.setImageBitmap( (Bitmap) msg.obj );
								// Log.d( "demo", "Attempted retrieval from above: " + getBitmapFromMemCache( currPhoto + "" ) );
								addBitmapToMemoryCache( currPhoto + "", (Bitmap) msg.obj );
								break;
						}
						break;
					case LAUNCH_SLIDESHOW:
						switch( msg.what ) {
							case SHOW_PROGRESS:
								// No progress bar for slideshow
								break;
							case HIDE_PROGRESS:
								mainPhoto.setImageBitmap( (Bitmap) msg.obj );

								// It is unclear from Sections 3 & 4 in the assignment whether the memory cache is supposed to be used in the final submission. I am choosing to include it based on the assignment. Please see note below (slideshowHandler).
								addBitmapToMemoryCache( currPhoto + "", (Bitmap) msg.obj );
								
								if( currPhoto == ( URLs.size() - 1 ) )
									currPhoto = 0;
								else
									currPhoto++;
								
								// Wait two seconds
								Message rerun = new Message();
								rerun.what = 0;
								slideshowHandler.sendMessageDelayed( rerun, 2000 );
								break;
							case SLIDESHOW_PHOTO_FROM_CACHE:
								
								mainPhoto.setImageBitmap( getBitmapFromMemCache( currPhoto + "" ) );
								
								if( currPhoto == ( URLs.size() - 1 ) )
									currPhoto = 0;
								else
									currPhoto++;
								
								// Wait two seconds
								Message rerun2 = new Message();
								rerun2.what = 0;
								slideshowHandler.sendMessageDelayed( rerun2, 2000 );
								break;
						}
						break;
				}
				
				return false;
			}
		});
		
		// This method is called after the 2 second pause under the slideshow version of PhotoActivity
		slideshowHandler = new Handler( new Handler.Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				// Check to see if Activity has been closed
				if( !stillActive )
					return false;
				
				
				// Display new photo
				// This block of code was used instead of the block below based on the ambiguity in the instructions over whether the Memory Cache should be used for the slideshow portion of the assignment. In the "Photo Activity in Slide Show Mode" (page 5) it says the slideshow should not store the photos, but part 4 (Caching) says that storing photos should be implemented in the Photo Activity (whether that is both the slideshow AND photos portion of the Photo Activity it does not specify). I am choosing to include the cache in both portions, but that can be easily disabled by uncommenting this portion of code and commenting out the if statements above.
				if( getBitmapFromMemCache( currPhoto + "" ) == null ) {
					Log.d("demo", "Loading next photo: " + currPhoto );
					HttpRetrievePhoto nextPhoto = new HttpRetrievePhoto();
					nextPhoto.handler = photoHandler;
					nextPhoto.execute( URLs.get( currPhoto ) );
				} else {
					Log.d("demo", "Bitmap already in cache: " + getBitmapFromMemCache( currPhoto + "" ) );
					Log.d("demo", "Loading photo from slideshow cache: " + currPhoto );
					mainPhoto.setImageBitmap( getBitmapFromMemCache( currPhoto + "" ) );
					Message rerun = new Message();
					rerun.what = SLIDESHOW_PHOTO_FROM_CACHE;
					photoHandler.sendMessageDelayed( rerun, 2000 );
				}
				
				/*
				
				// See note above on why this code is commented out.
				HttpRetrievePhoto nextPhoto = new HttpRetrievePhoto();
				nextPhoto.handler = photoHandler;
				nextPhoto.execute( URLs.get( currPhoto ) );
				*/
				
				return false;
			}
		});
		
		
		/*
		// HERE ARE TWO BUTTONS TO ASSIST WITH MANUALLY SWAPPING PHOTOS. DISABLED FOR FINAL PRODUCT DUE TO TOUCH GESTURES.
		Button lr = (Button) findViewById( R.id.button1 );
		lr.setVisibility( 0 );
		lr.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Increment the photo
				if( currPhoto == ( URLs.size() - 1 ) )
					currPhoto = 0;
				else
					currPhoto++;
				
				// Display new photo
				if( getBitmapFromMemCache( currPhoto + "" ) == null ) {
					Log.d("demo", "Loading next photo: " + currPhoto );
					HttpRetrievePhoto nextPhoto = new HttpRetrievePhoto();
					nextPhoto.handler = photoHandler;
					nextPhoto.execute( URLs.get( currPhoto ) );
				} else {
					Log.d("demo", "Loading photo from cache: " + currPhoto );
					mainPhoto.setImageBitmap( getBitmapFromMemCache( currPhoto + "" ) );
				}

			}
		});
		
		Button rl = (Button) findViewById( R.id.button2 );
		rl.setVisibility( 0 );
		rl.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Increment the photo
				if( currPhoto == 0 )
					currPhoto = URLs.size() - 1;
				else
					currPhoto--;
				
				// Display new photo
				if( getBitmapFromMemCache( currPhoto + "" ) == null ) {
					Log.d("demo", "Loading next photo: " + currPhoto );
					HttpRetrievePhoto nextPhoto = new HttpRetrievePhoto();
					nextPhoto.handler = photoHandler;
					nextPhoto.execute( URLs.get( currPhoto ) );
				} else {
					Log.d("demo", "Loading photo from cache: " + currPhoto );
					mainPhoto.setImageBitmap( getBitmapFromMemCache( currPhoto + "" ) );
				}

			}
		});
		*/
		
		// Attach gesture listener ONLY if the user is in the Photos mode
		if( launchType == LAUNCH_PHOTOS ) {
			gestureDetector = new GestureDetector( this, new MyGestureDetector() );
			gestureListener = new View.OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return gestureDetector.onTouchEvent( event );
				}
			};
		}
		
		switch( launchType ) {
			case LAUNCH_PHOTOS:
				// Load the first photo
				mainPhoto = (ImageView) findViewById( R.id.imageView2 );
				mainPhoto.setOnTouchListener( gestureListener );
				HttpRetrievePhoto getPhoto = new HttpRetrievePhoto();
				getPhoto.handler = photoHandler;
				getPhoto.execute( URLs.get( currPhoto ) );
				break;
			case LAUNCH_SLIDESHOW:
				// Load the first photo for the slideshow
				mainPhoto = (ImageView) findViewById( R.id.imageView2 );
				mainPhoto.setOnTouchListener( gestureListener );
				// Toast.makeText(PhotoActivity.this, "You have requested the slideshow feature", Toast.LENGTH_SHORT).show();
				
				HttpRetrievePhoto nextPhoto = new HttpRetrievePhoto();
				nextPhoto.handler = photoHandler;
				nextPhoto.execute( URLs.get( currPhoto ) );
				break;
		}
		
		
	}
	
	public void swipeLeftToRight() {
		
		// Increment the photo
		if( currPhoto == ( URLs.size() - 1 ) )
			currPhoto = 0;
		else
			currPhoto++;
		
		// Display new photo
		if( getBitmapFromMemCache( currPhoto + "" ) == null ) {
			Log.d("demo", "Loading next photo: " + currPhoto );
			HttpRetrievePhoto nextPhoto = new HttpRetrievePhoto();
			nextPhoto.handler = photoHandler;
			nextPhoto.execute( URLs.get( currPhoto ) );
		} else {
			Log.d("demo", "Loading photo from cache: " + currPhoto );
			mainPhoto.setImageBitmap( getBitmapFromMemCache( currPhoto + "" ) );
		}
		
	}
	
	public void swipeRightToLeft() {
			
			// Increment the photo
			if( currPhoto == 0 )
				currPhoto = URLs.size() - 1;
			else
				currPhoto--;
			
			// Display new photo
			if( getBitmapFromMemCache( currPhoto + "" ) == null ) {
				Log.d("demo", "Loading next photo: " + currPhoto );
				HttpRetrievePhoto nextPhoto = new HttpRetrievePhoto();
				nextPhoto.handler = photoHandler;
				nextPhoto.execute( URLs.get( currPhoto ) );
			} else {
				Log.d("demo", "Loading photo from cache: " + currPhoto );
				mainPhoto.setImageBitmap( getBitmapFromMemCache( currPhoto + "" ) );
			}
			
	}
	
	public void addBitmapToMemoryCache( String key, Bitmap bitmap ) {
		if( getBitmapFromMemCache( key ) == null ) {
			mMemoryCache.put( key, bitmap );
			// Log.d( "demo", "Put in memory cache: " + bitmap.toString() );
		}
	}
	
	public Bitmap getBitmapFromMemCache( String key ) {
		return mMemoryCache.get( key );
	}

	class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH )
                    return false;
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    // Toast.makeText(PhotoActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
                	swipeRightToLeft();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    // Toast.makeText(PhotoActivity.this, "Right Swipe", Toast.LENGTH_SHORT).show();
                	swipeLeftToRight();
                }
            } catch (Exception e) {
                // nothing
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
              return true;
        }
        
    }
	
    @Override
	protected void onDestroy() {
		super.onDestroy();
		stillActive = false;
	}    
}
