@file:OptIn(ExperimentalMaterial3Api::class)

package ru.urfu.droidpractice1.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.urfu.droidpractice1.R
import ru.urfu.droidpractice1.ui.theme.DroidPractice1Theme
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.ThumbDown
const val ARTICLE1_IMAGE_URL =
    "https://rus.com-x.life/uploads/posts/2023-03/4.jpg"
@Composable
fun MainActivityScreen(isSecondArticleRead: Boolean = false,
                       initialLikes: Int = 0,
                       initialDislikes: Int = 0,
                       onShare: (String) -> Unit = {},
                       onOpenSecond: (likes: Int, dislikes: Int) -> Unit = { _, _ -> }) {
    var likes by rememberSaveable { mutableIntStateOf(initialLikes) }
    var dislikes by rememberSaveable { mutableIntStateOf(initialDislikes) }
    DroidPractice1Theme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.article_title)) },
                    actions = {
                        IconButton(onClick = {
                            val text = buildString {
                                append("Берсерк\n\n")
                                append("«Берсерк» Кэнтаро Миуры — одна из самых влиятельных манг в истории. ")
                                append("История Гатса — о мести, дружбе и предательстве.")
                            }
                            onShare(text)
                        }) {
                            Icon(Icons.Default.Share, contentDescription = stringResource(R.string.share_article))
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.article1_title),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                item {
                    AsyncImage(
                        model = ARTICLE1_IMAGE_URL,
                        contentDescription = stringResource(R.string.article1_image_desc),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Text(
                        text = stringResource(R.string.article1_lead),
                        style = MaterialTheme.typography.titleMedium,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                item {
                    val body = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Чёрный мечник")
                        }
                        append(", вынужденный носить проклятый знак и вечно преследуемый демонами, — так начинается путь Гатса. ")
                        append("Бывший наёмник, чьей силы хватало, чтобы разорвать небольшой отряд, теперь он одноглазый и однорукий калека, преследующий фантом по имени ")
                        withStyle(SpanStyle(fontWeight = FontWeight.SemiBold)) { append("Гриффит") }
                        append(".\n\n")
                        append("Мир «Берсерка» — это средневековое тёмное фэнтези, где нет однозначно хороших и плохих героев. ")
                        append("Каждый персонаж несёт свою боль, свою мотивацию и свою трагедию. Именно это сделало мангу культовой.\n\n")
                        append("Миура создал историю о мести, дружбе и предательстве, которая постепенно превращается в размышление о цене свободы.")
                    }
                    Text(
                        text = body,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                item {
                    Text(
                        text = stringResource(R.string.article1_quote),
                        style = MaterialTheme.typography.bodyLarge,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }

                item {
                    Text(
                        text = stringResource(
                            if (isSecondArticleRead) R.string.second_article_read
                            else R.string.second_article_not_read
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (isSecondArticleRead) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(onClick = { likes++ }) {
                            Icon(
                                imageVector = Icons.Default.ThumbUp,
                                contentDescription = "Лайк",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text("$likes", style = MaterialTheme.typography.bodyLarge)
                        }
                        OutlinedButton(onClick = { dislikes++ }) {
                            Icon(
                                imageVector = Icons.Default.ThumbDown,
                                contentDescription = "Дизлайк",
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text("$dislikes", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }

                item {
                    Button(onClick = { onOpenSecond(likes, dislikes) }) {
                        Text(stringResource(R.string.go_to_second_article))
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainActivityScreen()
}