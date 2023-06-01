package com.issart.talkingpets.data

import android.graphics.Bitmap
import com.issart.talkingpets.animation.blink.getBlinkEyesImages
import com.issart.talkingpets.animation.model.Face
import com.issart.talkingpets.animation.start
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
          rightFace: FacePoints
     ): List<Bitmap> {
          return start(bitmap)
//          val eye = leftEye.toAnimationEye()
//          val face = toFace(
//               topFace,
//               bottomFace,
//               leftFace,
//               rightFace
//          )
//          return getBlinkEyesImages(
//               eye = eye,
//               face = face,
//               photo = bitmap
//          )
     }

     private fun Eye.toAnimationEye() = AnimationEye(
          x = x.toDouble(),
          y = y.toDouble(),
          radius = (5 * zoom).toDouble()
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
