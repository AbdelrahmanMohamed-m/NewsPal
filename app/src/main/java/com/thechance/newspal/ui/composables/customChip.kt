
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.thechance.newspal.theme.dimens


@Composable
fun CustomChip(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit,
    enable: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    Card(
        modifier = modifier.clickable(
            enabled = enable,
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        },
        colors = if (isSelected) CardDefaults.cardColors(Color(0xFF1877F2))
        else CardDefaults.cardColors(Transparent),
        border = if (isSelected) BorderStroke(width = 0.dp, color = Transparent)
        else BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
        shape = CircleShape
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.dimens.space16, vertical = MaterialTheme.dimens.space6
            ).fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall,
            text = text,
            color = if (isSelected) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.secondary,
        )
    }
}
