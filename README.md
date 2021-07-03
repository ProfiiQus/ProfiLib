# ProfiLib ![Jitpack](https://jitpack.io/v/ProfiiQus/ProfiLib.svg) ![Spigot](https://img.shields.io/badge/Spigot-1.12%20--%201.17-orange)

ProfiLib is a simple library being created for easier development of Minecraft - Spigot and Bungee plugins. Currently, it houses only a simple messaging and coloring library.

## Installation

### Maven
```
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
```
<dependency>
    <groupId>com.github.ProfiiQus</groupId>
	<artifactId>ProfiLib</artifactId>
	<version>1.3.1</version>
</dependency>
```
### Gradle
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
```
dependencies {
	implementation 'com.github.ProfiiQus:ProfiLib:1.3.1'
}
```

## Usage

A simple example; the following codes grabs the message from configuration file, sets a list of map of provided placeholders and finally colorizes and sends the message to the player.
```java
Message msg = Message.fromPath(config, "MESSAGE_PATH");
msg.setPlaceholders(new HashMap<String, String>() {
        {
            put("%level%", Integer.toString(player.getLevel()));
        }
}).colorize().send(player);
```
