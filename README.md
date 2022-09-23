# BlueMap Advanced Marker
<a title="Fabric Language Kotlin" href="https://minecraft.curseforge.com/projects/fabric-language-kotlin" target="_blank" rel="noopener noreferrer"><img style="display: block; margin-left: auto; margin-right: auto;" src="https://i.imgur.com/c1DH9VL.png" alt="" width="230" /></a>
<img src="https://i.imgur.com/iaETp3c.png" alt="" width="200" >
<img src="https://i.imgur.com/Ol1Tcf8.png" alt="" width="200" >

## Description
Server-side addon for BlueMap markers management

## Commands
* `/bam help` - All available commands
* `/bam icons` - List of available icons ID
* `/bam list [<owner>]` - List own markers
* `/bam listall` - List all players markers
* `/bam create <name> [<icon>]` - Create point to map
* `/bam remove <name> [<owner>]` - Remove point from map
* `/bam version` - Version command. Not require any permissions

## Permissions
* `bluemapadvancedmarker.help`
* `bluemapadvancedmarker.create`
* `bluemapadvancedmarker.remove`
  * `bluemapadvancedmarker.remove.other`
* `bluemapadvancedmarker.icons`
* `bluemapadvancedmarker.list`
  * `bluemapadvancedmarker.list.other`
* `bluemapadvancedmarker.listall`

## Custom icons
If you want to add your own icons, add the following line to `./config/bam/config.json`. New icons can be used same time after the file saved
```json5
{
    "icons": {
        "custom_icon_id": "custom_icon_url"     // URL endpoint must be image file
    }
}
```
