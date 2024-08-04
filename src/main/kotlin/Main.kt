package org.example

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() {
    val rootDir ="ROOT_DIR"
    val rawDirectory = "$rootDir/RAW_INPUT_DIR"
    val imageName = "ruinedBuilding"
    val inputFileName = "$rawDirectory/$imageName.png"

    val outputDirectory = "$rootDir/"
    val inputImage: BufferedImage = ImageIO.read(File(inputFileName))

    val gridWidth = 20
    val gridHeight = 20

    val subImageNames = listOf(
        "${imageName}TopLeft", "${imageName}Top", "${imageName}TopRight",
        "${imageName}Left", "${imageName}Center", "${imageName}Right",
        "${imageName}BottomLeft", "${imageName}Bottom", "${imageName}BottomRight"
    )

    var index = 0

    for (y in 0 until 3) {
        for (x in 0 until 3) {
            val subImage: BufferedImage = inputImage.getSubimage(
                x * gridWidth, y * gridHeight, gridWidth, gridHeight
            )
            val outputFileName = "${outputDirectory}${subImageNames[index++]}.png"
            ImageIO.write(subImage, "png", File(outputFileName))
        }
    }

    println("Images split and saved successfully! Here's the lua for ya bud")
    println("$imageName = {")
    println("    name=\"$imageName\",")
    println("    tile9={")
    println("        topLeft=\"${imageName}TopLeft\",")
    println("        top=\"${imageName}Top\",")
    println("        topRight=\"${imageName}TopRight\",")
    println("        left=\"${imageName}Left\",")
    println("        center=\"${imageName}Center\",")
    println("        right=\"${imageName}Right\",")
    println("        bottomLeft=\"${imageName}BottomLeft\",")
    println("        bottom=\"${imageName}Bottom\",")
    println("        bottomRight=\"${imageName}BottomRight\"")
    println("    },")
    println("    tags={\"YOUR_TAG_HERE\"}")
    println("}")
}
