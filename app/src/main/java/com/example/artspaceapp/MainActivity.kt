package com.example.artspaceapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceAppScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceAppScreen() {
    // Position of an ArtObject
    var position by remember { mutableStateOf(0) }
    // List of ArtObjects
    val artObjectList by remember {
        mutableStateOf(
            listOf<ArtObject>(
                ArtObject("Anybody One", "1991", "A woman art image with beautiful blend of many hair colors", R.drawable.free_img_1),
                ArtObject("Anybody Two", "1992", "A beautiful woman drawn in black and white with red lips", R.drawable.free_img_2),
                ArtObject("Anybody Three", "1993", "Another beautiful woman drawn in black and white with red lips", R.drawable.free_img_3),
                ArtObject("Anybody Four", "1994", "The figure ten in a beautiful and amazing color blend design", R.drawable.free_img_4),
                ArtObject("Anybody Five", "1995", "A well designed polyhedron with many color patterns", R.drawable.free_img_5),
                ArtObject("Anybody Six", "1996", "A full image of a deadly Samurai with a killer sword", R.drawable.free_img_6),
                ArtObject("Anybody Seven", "1997", "An adorable image of a guitarist", R.drawable.free_img_7),
                ArtObject("Anybody Eight", "1998", "A spiral image with red and black colors", R.drawable.free_img_8),
                ArtObject("Anybody Nine", "1999", "A blue container with different fine items",R.drawable.free_img_9),
                ArtObject("Anybody Ten", "2000", "An un-even green background color art picture", R.drawable.free_img_10)
            )
        )
    }
    // An ArtObject
    var artObject: ArtObject? = artObjectList[position]

    val context = LocalContext.current

    Column(modifier = Modifier
        .wrapContentSize()
        .padding(10.dp)
    ) {
        ArtObjectImage(artObject!!.image)
        Spacer(modifier = Modifier.height(10.dp))
        ArtObjectInformation(
            artObject!!.description,
            artObject!!.artistName,
            artObject!!.yearOfPub
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (position == 0) {
                        Toast.makeText(context, "No previous image. Click the Next Button", Toast.LENGTH_SHORT).show()
                    } else {
                        position--
                        artObject = artObjectList[position]
                    }
                },
            ) {
                Text(text = stringResource(id = R.string.previous))
            }
            Button(
                onClick = {
                    if (position == artObjectList.size - 1) {
                        Toast.makeText(context, "No further image. Click the Previous Button", Toast.LENGTH_SHORT).show()
                    } else {
                        position++
                        artObject = artObjectList[position]
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Composable
fun ArtObjectImage(@DrawableRes image: Int) {
    Card(
        elevation = 10.dp,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = image), 
            contentDescription = "An Art Image"
        )
    }
}

@Composable
fun ArtObjectInformation(description: String, name: String, year: String) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.padding(20.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = description, fontSize = 20.sp)
            Text(text = "$name($year)", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceAppTheme {
        ArtSpaceAppScreen()
    }
}