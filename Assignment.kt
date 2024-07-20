package com.example.statussaver

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.statussaver.presentation.ui.theme.playerCardColor
import com.example.statussaver.presentation.ui.theme.playerCardColor2

@Composable
fun PlayerCardScreen(
    name: String,
    imageUrl: String,
    position: String,
    stats: Stats,
) {
    val imageLoader = rememberAsyncImagePainter(model = imageUrl)

    ElevatedCard(
        modifier = Modifier
            .height(500.dp)
            .width(350.dp)
            .padding(20.dp),
        colors = CardColors(
            containerColor = playerCardColor2,
            contentColor = Color.White,
            disabledContentColor = Color.White,
            disabledContainerColor = Color.White
        ),
    ) {

        Image(
            painter = imageLoader,
            contentDescription = "Player Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
        )

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RectangleShape)) {
            Box(modifier = Modifier) {
                Column(modifier = Modifier.padding(10.dp)) {

                    Text(text = annotatedString(title = "Name", content = name))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = annotatedString(title = "Position", content = position))
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()


                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Stats",
                            fontSize = 20.sp,
                            color = Color.Red
                        )
                        HorizontalDivider()
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {

                            Text(
                                text = annotatedString(
                                    title = "Points Per Game",
                                    content = "${stats.pointsPerGame}",
                                    color = Color.Blue,
                                    fontSize = 16
                                )
                            )
                            Text(
                                text = annotatedString(
                                    title = "Assists Per Game",
                                    content = "${stats.assistsPerGame}",
                                    color = Color.Blue,
                                    fontSize = 16
                                )
                            )
                            Text(
                                text = annotatedString(
                                    title = "Rebounds Per Game",
                                    content = "${stats.reboundsPerGame}",
                                    color = Color.Blue,
                                    fontSize = 16
                                )
                            )
                        }
                    }

                }

            }
        }


    }

}


@Preview
@Composable
fun CardPreview() {
    PlayerCardScreen(
        name = "Lebron James",
        imageUrl = "https://th.bing.com/th/id/OIP.SyeJzHxrjOzqPYgqtmGPPgHaE7?w=272&h=181&c=7&r=0&o=5&dpr=1.3&pid=1.7",
        position = "Small Forward",
        stats = Stats(
            pointsPerGame = 25.0f,
            assistsPerGame = 7.0f,
            reboundsPerGame = 8.0f,
        )
    )

}

@Composable
fun annotatedString(
    title: String,
    content: String,
    color: Color = Color.Magenta,
    fontSize: Int = 20,
): AnnotatedString {
    return buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        ) {
            append("$title : ")
        }
        withStyle(
            style = SpanStyle(
                color = color,
                fontSize = fontSize.sp
            )
        ) {
            append(content)
        }
    }
}

data class Stats(
    val pointsPerGame: Float,
    val assistsPerGame: Float,
    val reboundsPerGame: Float,
)