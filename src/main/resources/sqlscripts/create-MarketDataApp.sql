CREATE DATABASE IF NOT EXISTS MarketDataApp;

CREATE TABLE MarketDataApp.market_data_snapshots (
    `market_data_snapshot_id` INT AUTO_INCREMENT,
    `instrument_id` INT,
    `exchange_id` INT,
    `bid_price` DOUBLE,
    `ask_price` DOUBLE,
    `last_price` DOUBLE,
    `captured_at` DATETIME,
PRIMARY KEY (market_data_snapshot_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE MarketDataApp.market_quotes (
    `market_quote_id` INT AUTO_INCREMENT,
    `instrument_id` INT,
    `bid_price` DOUBLE,
    `bid_size` DOUBLE,
    `ask_price` DOUBLE,
    `ask_size` DOUBLE,
    `quoted_at` DATETIME,
PRIMARY KEY (market_quote_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE MarketDataApp.market_bars (
    `market_bar_id` INT AUTO_INCREMENT,
    `instrument_id` INT,
    `open_price` DOUBLE,
    `high_price` DOUBLE,
    `low_price` DOUBLE,
    `close_price` DOUBLE,
    `volume` DOUBLE,
    `bar_time` DATETIME,
PRIMARY KEY (market_bar_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE MarketDataApp.market_statuss (
    `market_status_id` INT AUTO_INCREMENT,
    `exchange_id` INT,
    `status` VARCHAR(255),
    `changed_at` DATETIME,
PRIMARY KEY (market_status_id)) ENGINE=MyISAM DEFAULT CHARSET=latin1;

USE drcvelocity;
