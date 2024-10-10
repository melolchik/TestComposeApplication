package com.example.testcomposeapplication.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testcomposeapplication.InstagramModel
import com.example.testcomposeapplication.R

@Composable
fun InsatgramProfileCard(model: InstagramModel,
                         onFollowedButtonClickListener: (InstagramModel) -> Unit){

    Log.d("RECOMPOSITION","InsatgramProfileCard")
   // val isFollowed = rememberSaveable {  mutableStateOf(false) }
    //val isFollowed by viewModel.isFollowing.observeAsState(false)
    Card(
        shape = RoundedCornerShape(topStart =  4.dp, topEnd = 4.dp),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onBackground),
        colors = CardColors(containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        disabledContentColor = Color.Unspecified, disabledContainerColor = Color.Unspecified)

    ) {
        Log.d("RECOMPOSITION","Card")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.background(color = Color.Transparent)
                //.height(500.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.drawable.ic_favorite_border),
                contentDescription = "")
            UserStatistics(title = "Posts", value = "6,958")
            UserStatistics(title = "Followers", value = "436M")
            UserStatistics(title = "Following", value = "76")

        }

        Text(
            text = "Instagram ${model.id}",
            fontSize = 32.sp,
            fontFamily = FontFamily.Cursive
        )

        Text(
            text = model.title,
            fontSize = 14.sp
        )

        FollowButton(isFollowed = model.isFollowed) { onFollowedButtonClickListener(model) }



    }
}
@Composable
private fun FollowButton(isFollowed : Boolean, clickListener: () -> Unit){
    Log.d("RECOMPOSITION","FollowButton")
    Button(onClick = {
        clickListener()
    },
        colors = ButtonDefaults.buttonColors(
            containerColor = if(isFollowed)
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            else MaterialTheme.colorScheme.primary
        )
    ) {
        val text = if(isFollowed) "Unfollow" else "Follow"
        Text(text = text)
    }
}

@Composable
private fun UserStatistics(title: String, value : String){
    Column (modifier = Modifier
        .height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly){
        Text(text = value,
            fontSize = 24.sp,
            fontFamily = FontFamily.Cursive)
        Text(text = title,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Preview
//@Composable
//fun previewCardLight(){
//
//    TestComposeApplicationTheme(darkTheme = false) {
//        InsatgramProfileCard()
//    }
//}
//
//@Preview
//@Composable
//fun previewCardDark(){
//    TestComposeApplicationTheme(darkTheme = true) {
//        InsatgramProfileCard()
//    }
//}