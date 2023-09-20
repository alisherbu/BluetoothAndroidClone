package com.glodanif.bluetoothchat.di

import com.glodanif.bluetoothchat.data.database.Database
import com.glodanif.bluetoothchat.data.model.*
import com.glodanif.bluetoothchat.ui.view.NotificationView
import com.glodanif.bluetoothchat.ui.view.NotificationViewImpl
import com.glodanif.bluetoothchat.ui.viewmodel.converter.ChatMessageConverter
import com.glodanif.bluetoothchat.ui.viewmodel.converter.ContactConverter
import com.glodanif.bluetoothchat.ui.viewmodel.converter.ConversationConverter
import com.glodanif.bluetoothchat.ui.widget.ShortcutManager
import com.glodanif.bluetoothchat.ui.widget.ShortcutManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val bluetoothConnectionModule = module {
    single { BluetoothConnectorImpl(androidContext()) } bind BluetoothConnector::class
    factory { BluetoothScannerImpl(androidContext()) } bind BluetoothScanner::class
}

val databaseModule = module {
    single { Database.getInstance(androidContext()) }
    single { MessagesStorageImpl(get()) } bind MessagesStorage::class
    single { ConversationsStorageImpl(get()) } bind ConversationsStorage::class
}

val localStorageModule = module {
    single { FileManagerImpl(androidContext()) } bind FileManager::class
    single { UserPreferencesImpl(androidContext()) } bind UserPreferences::class
    single { ProfileManagerImpl(androidContext()) } bind ProfileManager::class
}

val viewModule = module {
    single { NotificationViewImpl(androidContext()) } bind NotificationView::class
    single { ShortcutManagerImpl(androidContext()) } bind ShortcutManager::class
    single { ContactConverter() }
    single { ConversationConverter(androidContext()) }
    single { ChatMessageConverter(androidContext()) }
}
