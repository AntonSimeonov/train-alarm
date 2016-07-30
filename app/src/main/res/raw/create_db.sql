CREATE TABLE train_alarm (
id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
alarm_id INTEGER NOT NULL,
train_station TEXT NOT NULL,
train_number TEXT NOT NULL,
start_notification TEXT NOT NULL
);