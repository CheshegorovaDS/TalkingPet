package com.issart.talkingpets.ui.common.gridLayout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.issart.talkingpets.ui.utils.onClickImageCallback

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ImageGridLayout(
    modifier: Modifier = Modifier,
    data: List<Int>,
    contentPadding: PaddingValues,
    onClickCallback: onClickImageCallback,
    contentDescription: String = DEFAULT_CONTENT_DESCRIPTION
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(COUNT_IMAGES_AT_ROW),
        contentPadding = contentPadding
    ) {
        items(data) { animalPhotoId ->
            Card(
                modifier = Modifier.padding(PADDING_IMAGE.dp),
                onClick = { onClickCallback(animalPhotoId) }
            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(id = animalPhotoId),
                    contentDescription = contentDescription,
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

const val COUNT_IMAGES_AT_ROW = 3
const val PADDING_IMAGE = 4
const val DEFAULT_CONTENT_DESCRIPTION = "Image of grid layout."

const val VERTICAL_PADDING_GRID_LAYOUT = 32
const val HORIZONTAL_PADDING_GRID_LAYOUT = 8
