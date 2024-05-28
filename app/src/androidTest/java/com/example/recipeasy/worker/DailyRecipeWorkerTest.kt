package com.example.recipeasy.worker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.testing.TestListenableWorkerBuilder
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DailyRecipeWorkerTest {
    private lateinit var context: Context
    private lateinit var mockNotificationManager: NotificationManager
    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        mockNotificationManager = mock(NotificationManager::class.java)
    }

    @Test
    fun dailyRecipeWorker_doWork_resultSucces() {
        val worker = TestListenableWorkerBuilder<DailyRecipeWorker>(context).build()
        runBlocking {
            val result = worker.doWork()
            assert(result == ListenableWorker.Result.success())
        }
    }

    @Test
    fun testNotificationTriggered_resultSucces() = runTest {
        // Create a custom NotificationManager
        val notificationManager = CustomNotificationManager(context)

        // Build the worker
        val worker = TestListenableWorkerBuilder<DailyRecipeWorker>(
            context = context
        ).build()

        // Execute the worker
        worker.doWork()

        // Get the notifications sent by the application
        val notifications = notificationManager.getNotifications()

        // Ensure that at least one notification was sent
        assertEquals(1, notifications.size)

        // Get the notification
        val notification = notifications[0]

        // Ensure that the notification has correct content
        assertEquals("New recipe of the day: ", notification.extras.getString(NotificationCompat.EXTRA_TITLE))

    }

    class CustomNotificationManager(context: Context) {
        private val notificationManager = NotificationManagerCompat.from(context)

        init {
            createNotificationChannel()
        }

        fun getNotifications(): List<Notification> {
            return notificationManager.activeNotifications.map { it.notification }
        }

        private fun createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "test_channel"
                val channelName = "Test Channel"
                val channel = NotificationChannel(channelId, channelName, NotificationManagerCompat.IMPORTANCE_DEFAULT)
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}