# ServerLinks Plugin

ServerLinks is a Bukkit/Spigot plugin that allows server administrators to add custom commands that link to external websites such as Discord, a shop, or any other URL. The links are easily configurable via the `config.yml` file, where administrators can specify messages for each command. The plugin supports color formatting through the ForestMC ColorAPI, including gradients, RGB hex, and classic Minecraft color codes.

## Features

- Customizable commands for linking to external websites.
- Supports advanced color formatting (gradient, RGB, and classic Minecraft colors).
- Easy to use configuration via `config.yml`.
- Dynamic command generation based on the configuration.
- Reload configuration with `/slreload` command.

## Installation

1. Download the `ServerLinks.jar` file.
2. Place the `.jar` file in your server's `plugins` folder.
3. Restart your server to generate the `config.yml`.
4. Edit the `config.yml` file to add your custom links and messages.
5. Reload the plugin or restart the server to apply the changes.

## Configuration

The configuration is stored in `config.yml`. Here you can define custom commands and their associated messages. You can use advanced color formatting as follows:

- **Gradient:** `{#hex1>}Text{#hex2<}`
- **RGB Hex:** `{#hex}`
- **Classic Colors:** `&color-code`

Example configuration:

```yaml
commands:
  discord: "{#ff0000>}https://discord.gg/xxxxxxxxxx{#00ff00<}"
  website: "{#ff3333}https://example.com"
  shop: "&bhttps://shop.example.com"
