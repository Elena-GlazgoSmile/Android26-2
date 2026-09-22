package ru.urfu.droidpractice1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ru.urfu.droidpractice1.content.MainActivityScreen

class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
        const val EXTRA_IS_READ = "extra_is_read"
        private const val KEY_SECOND_READ = "key_second_read"
        private const val KEY_LIKES = "key_likes"
        private const val KEY_DISLIKES = "key_dislikes"
    }
    private var isSecondArticleRead by mutableStateOf(false)
    private var likes by mutableIntStateOf(0)
    private var dislikes by mutableIntStateOf(0)

    private val secondLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            isSecondArticleRead = result.data?.getBooleanExtra(EXTRA_IS_READ, false) ?: false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        savedInstanceState?.let {
            isSecondArticleRead = it.getBoolean(KEY_SECOND_READ, false)
            likes = it.getInt(KEY_LIKES, 0)
            dislikes = it.getInt(KEY_DISLIKES, 0)
        }

        setContent {
            MainActivityScreen(
                isSecondArticleRead = isSecondArticleRead,
                initialLikes = likes,
                initialDislikes = dislikes,
                onShare = { text ->
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, text)
                    }
                    startActivity(Intent.createChooser(intent, getString(R.string.share_article)))
                },
                onOpenSecond = { l, d ->
                    likes = l
                    dislikes = d
                    val intent = Intent(this, SecondActivity::class.java).apply {
                        putExtra(EXTRA_IS_READ, isSecondArticleRead)
                    }
                    secondLauncher.launch(intent)
                }
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_SECOND_READ, isSecondArticleRead)
        outState.putInt(KEY_LIKES, likes)
        outState.putInt(KEY_DISLIKES, dislikes)
    }

    override fun onStart() { super.onStart(); Log.d(TAG, "onStart") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy") }
    override fun onRestart() { super.onRestart(); Log.d(TAG, "onRestart") }
}