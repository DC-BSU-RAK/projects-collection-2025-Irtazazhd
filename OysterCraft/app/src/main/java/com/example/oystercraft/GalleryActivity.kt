package com.example.oystercraft

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        // Corrected ImageView references to match XML and logical flow
        val submarinerImage: ImageView = findViewById(R.id.image1) // This is the Submariner in XML
        val daytonaImage: ImageView = findViewById(R.id.image2)    // This is the Daytona in XML
        val gmtMasterImage: ImageView = findViewById(R.id.image3)  // This is the GMT-Master II in XML
        val datejustImage: ImageView = findViewById(R.id.image4)   // This is the Datejust in XML
        val yachtMasterImage: ImageView = findViewById(R.id.image5)
        val skyDwellerImage: ImageView = findViewById(R.id.image6) // This is the Sky-Dweller in XML


        // Helper function to launch WatchDetailActivity
        fun launchWatchDetail(name: String, imageResId: Int, description: String) {
            val intent = Intent(this, WatchDetailActivity::class.java).apply {
                putExtra("WATCH_NAME", name)
                putExtra("WATCH_IMAGE_RES_ID", imageResId)
                putExtra("WATCH_DESCRIPTION", description)
            }
            startActivity(intent)
        }

        // Set up click listeners for each watch
        submarinerImage.setOnClickListener {
            launchWatchDetail(
                "Rolex Submariner",
                R.drawable.rolex1, // Assuming rolex1 is the Submariner image
                "The Rolex Submariner is a legendary diving watch, known for its robustness, precision, and timeless design. Introduced in 1953, it was the first diver's wristwatch waterproof to a depth of 100 meters (330 feet). It's an iconic tool watch that has transcended its original purpose to become a symbol of adventurous spirit and elegant style."
            )
        }

        daytonaImage.setOnClickListener {
            launchWatchDetail(
                "Rolex Daytona",
                R.drawable.rolex2, // Assuming rolex2 is the Daytona image
                "The Rolex Daytona is an iconic chronograph designed for endurance racing drivers. Launched in 1963, it's celebrated for its reliable performance and tachymetric scale, allowing drivers to measure average speeds up to 400 units per hour. Its highly sought-after status makes it one of the most famous and collectible watches in the world."
            )
        }

        gmtMasterImage.setOnClickListener {
            launchWatchDetail(
                "Rolex GMT-Master II",
                R.drawable.rolex3, // Assuming rolex3 is the GMT-Master II image
                "Originally developed for airline pilots, the Rolex GMT-Master II allows the simultaneous display of two different time zones. Its distinctive bi-directional rotating bezel, often in two colors (like 'Pepsi' or 'Batman'), has made it a favorite among travelers and collectors alike. It's a testament to global connectivity and precision."
            )
        }

        datejustImage.setOnClickListener {
            launchWatchDetail(
                "Rolex Datejust",
                R.drawable.rolex4, // Assuming rolex4 is the Datejust image
                "The Rolex Datejust, introduced in 1945, was the first self-winding waterproof chronometer wristwatch to display the date in a window at 3 o'clock on the dial. It's a classic and elegant watch, renowned for its versatility and diverse range of aesthetic variations, from fluted bezels to Jubilee bracelets, making it a timeless choice for any occasion."
            )
        }

        // Yacht-Master
        yachtMasterImage.setOnClickListener {
            launchWatchDetail(
                "Rolex Yacht-Master",
                R.drawable.rolex5,
                "The Rolex Yacht-Master blends luxury and functionality, tailored for sailing enthusiasts. With its distinctive bidirectional rotatable bezel in precious materials and a robust yet elegant design, it exemplifies the spirit of nautical adventure and prestige."
            )
        }

        skyDwellerImage.setOnClickListener {
            launchWatchDetail(
                "Rolex Sky-Dweller",
                R.drawable.rolex6, // Assuming rolex6 is the Sky-Dweller image
                "The Rolex Sky-Dweller is a sophisticated and innovative timepiece designed for global travelers. It features an annual calendar that automatically differentiates between 30- and 31-day months, and a dual time zone display with an off-center 24-hour disc. Its unique Ring Command bezel allows easy setting of its functions, showcasing Rolex's technical prowess."
            )
        }
    }
}