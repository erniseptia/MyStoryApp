package app.story.mystoryapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.story.mystoryapp.dataclass.RemoteKeys
import app.story.mystoryapp.dataclass.Story

@Database(
    entities = [Story::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class StoryDatabase : RoomDatabase() {
    abstract fun storyDao(): StoryDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}