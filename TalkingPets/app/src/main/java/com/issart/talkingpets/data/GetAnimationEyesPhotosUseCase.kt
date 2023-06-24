package com.issart.talkingpets.data

import android.graphics.Bitmap
import com.issart.talkingpets.animation.blink.getBlinkEyesImages
import com.issart.talkingpets.animation.model.Face
import com.issart.talkingpets.ui.common.dragPoints.POINT_SIZE
import com.issart.talkingpets.ui.common.dragPoints.SCALE_BOX_SIZE
import com.issart.talkingpets.ui.detector.detectorPoints.model.face.FacePoints
import com.issart.talkingpets.ui.model.Eye
import javax.inject.Inject
import com.issart.talkingpets.animation.model.Eye as AnimationEye

class GetAnimationEyesPhotosUseCase @Inject constructor(
) {

     suspend fun getPhotos(
          bitmap: Bitmap,
          leftEye: Eye,
          topFace: FacePoints,
          bottomFace: FacePoints,
          leftFace: FacePoints,
          rightFace: FacePoints,
          density: Float,
          px: Float
     ): List<Bitmap> {
          val offsetBox = ((SCALE_BOX_SIZE /*- (POINT_SIZE * leftEye.zoom)*/) * density / 2)
          val eye = leftEye.toAnimationEye((bitmap.width / px), offsetBox)

          val face = Face(CENTER_FACE_X, CENTER_FACE_Y, MINOR_AXIS_FACE)
//          val face = toFace(
//               topFace,
//               bottomFace,
//               leftFace,
//               rightFace
//          )
          return getBlinkEyesImages(
               eye = eye,
               face = face,
               photo = bitmap
          )
     }

     private fun Eye.toAnimationEye(difference: Float, offsetBox: Float): AnimationEye = AnimationEye(
          x = (x * difference).toDouble() + offsetBox,
          y = (y * difference).toDouble() + offsetBox,
          radius = (POINT_SIZE * zoom * difference).toDouble()
     )

     private fun toFace(
          topPoint: FacePoints,
          bottomPoint: FacePoints,
          leftPoint: FacePoints,
          rightPoint: FacePoints
     ) = Face(
          x = (leftPoint.x + ((rightPoint.x - leftPoint.x) / 2)).toDouble(),
          y = (topPoint.y + ((bottomPoint.y - bottomPoint.y) / 2)).toDouble(),
          radius = ((bottomPoint.y - bottomPoint.y) / 2).toDouble()
     )
     //radius of face should be smaller than source
}

const val MINOR_AXIS_FACE = 198.0
const val CENTER_FACE_X = 30.0
const val CENTER_FACE_Y = 30.0
