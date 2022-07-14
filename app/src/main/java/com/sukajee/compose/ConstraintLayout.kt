package com.sukajee.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


const val RED_BOX = "RED_BOX"
const val GREEN_BOX = "GREEN_BOX"

@Composable
fun ConstraintLayoutComposable() {

    val constraintSet = ConstraintSet {
        val greenBox = createRefFor(GREEN_BOX)
        val redBox = createRefFor(RED_BOX)
        val guideLineTop = createGuidelineFromTop(0.25f)

        constrain(greenBox) {
            start.linkTo(parent.start)
            top.linkTo(guideLineTop)
            width = Dimension.value(100.dp)
            height = Dimension.ratio("1:1")
        }

        constrain(redBox) {
            start.linkTo(greenBox.end)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.ratio("1:1")
        }

        createHorizontalChain(
            greenBox, redBox
            /**, chainStyle = ChainStyle.Packed*/
        )
    }
    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .layoutId(GREEN_BOX)
                .background(Color.Green)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .layoutId(RED_BOX)
                .background(Color.Red)
        )
    }
}