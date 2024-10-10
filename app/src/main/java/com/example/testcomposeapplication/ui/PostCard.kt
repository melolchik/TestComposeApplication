package com.example.testcomposeapplication.ui

import android.widget.AdapterView.OnItemClickListener
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testcomposeapplication.R
import com.example.testcomposeapplication.domain.FeedPost
import com.example.testcomposeapplication.domain.StatisticItem
import com.example.testcomposeapplication.domain.StatisticType
import com.example.testcomposeapplication.ui.theme.TestComposeApplicationTheme


@Composable
fun PostCard(modifier: Modifier = Modifier,
             feedPost: FeedPost,
             onViewClickListener: (StatisticItem) -> Unit,
             onLikeClickListener: (StatisticItem) -> Unit,
             onCommentClickListener: (StatisticItem) -> Unit,
             onShareClickListener: (StatisticItem) -> Unit) {

    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            PostHeader(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = feedPost.contentText)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = feedPost.contentImageResId),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(feedPost.statistics,
                onViewClickListener = onViewClickListener,
            onLikeClickListener = onLikeClickListener,
            onCommentClickListener = onCommentClickListener,
            onShareClickListener = onShareClickListener)
        }
    }

}

@Composable
private fun PostHeader(feedPost: FeedPost) {
    Card(shape = RectangleShape) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = painterResource(id = feedPost.avatarResId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = feedPost.communityName,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "14:00",
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Composable
private fun Statistics(statistics : List<StatisticItem>,
                       onViewClickListener: (StatisticItem) -> Unit,
                       onLikeClickListener: (StatisticItem) -> Unit,
                       onCommentClickListener: (StatisticItem) -> Unit,
                       onShareClickListener: (StatisticItem) -> Unit) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier.weight(1f)) {
            val viewItem = statistics.getItemByType(StatisticType.VIEWS)
            IconWithText(R.drawable.ic_views_count, viewItem.count.toString(),
                onItemClickListener = {
                    onViewClickListener(viewItem)
                })
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            val shareItem = statistics.getItemByType(StatisticType.SHARES)
            IconWithText(R.drawable.ic_share, shareItem.count.toString(),
                onItemClickListener = {
                    onShareClickListener(shareItem)
                })
            val commentItem = statistics.getItemByType(StatisticType.COMMENTS)
            IconWithText(R.drawable.ic_comment, commentItem.count.toString(),
                onItemClickListener = {
                    onCommentClickListener(commentItem)
                })
            val likeItem = statistics.getItemByType(StatisticType.LIKES)
            IconWithText(R.drawable.ic_like, likeItem.count.toString(),
                onItemClickListener = {
                    onLikeClickListener(likeItem)
                })
        }
    }
}
private fun List<StatisticItem>.getItemByType(type: StatisticType) : StatisticItem{
    return this.find { it.type == type } ?: throw IllegalArgumentException()
}

@Composable
private fun IconWithText(
    iconResId: Int,
    text: String,
    onItemClickListener : () -> Unit
) {
    Row(modifier = Modifier.clickable {
        onItemClickListener()
    },
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}

//@Preview
//@Composable
//fun previewPostCardLight() {
//
//    TestComposeApplicationTheme(darkTheme = false) {
//        PostCard()
//    }
//}
//
//@Preview
//@Composable
//fun previewPostCardDark() {
//    TestComposeApplicationTheme(darkTheme = true) {
//        PostCard()
//    }
//}