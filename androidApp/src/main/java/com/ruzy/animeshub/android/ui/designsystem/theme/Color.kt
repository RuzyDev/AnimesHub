package com.ruzy.animeshub.designsystem.theme

import androidx.compose.ui.graphics.Color

val light_primary = Color(0xFF006590)
val light_onPrimary = Color(0xFFFFFFFF)
val light_primaryContainer = Color(0xFFC8E6FF)
val light_onPrimaryContainer = Color(0xFF001E2F)
val light_secondary = Color(0xFF4F606E)
val light_onSecondary = Color(0xFFFFFFFF)
val light_secondaryContainer = Color(0xFFD3E5F5)
val light_onSecondaryContainer = Color(0xFF0B1D29)
val light_tertiary = Color(0xFF006494)
val light_onTertiary = Color(0xFFFFFFFF)
val light_tertiaryContainer = Color(0xFFCBE6FF)
val light_onTertiaryContainer = Color(0xFF001E30)
val light_error = Color(0xFFBA1A1A)
val light_errorContainer = Color(0xFFFFDAD6)
val light_onError = Color(0xFFFFFFFF)
val light_onErrorContainer = Color(0xFF410002)
val light_background = Color(0xFFFCFCFF)
val light_onBackground = Color(0xFF191C1E)
val light_surface = Color(0xFFFCFCFF)
val light_onSurface = Color(0xFF191C1E)
val light_surfaceVariant = Color(0xFFDDE3EA)
val light_onSurfaceVariant = Color(0xFF41474D)
val light_outline = Color(0xFF71787E)
val light_inverseOnSurface = Color(0xFFF0F0F3)
val light_inverseSurface = Color(0xFF2E3133)
val light_inversePrimary = Color(0xFF89CEFF)
val light_shadow = Color(0xFF000000)
val light_surfaceTint = Color(0xFF006590)
val light_outlineVariant = Color(0xFFC1C7CE)
val light_scrim = Color(0xFF000000)

val dark_primary = Color(0xFF89CEFF)
val dark_onPrimary = Color(0xFF00344D)
val dark_primaryContainer = Color(0xFF004C6E)
val dark_onPrimaryContainer = Color(0xFFC8E6FF)
val dark_secondary = Color(0xFFB7C9D9)
val dark_onSecondary = Color(0xFF21323F)
val dark_secondaryContainer = Color(0xFF384956)
val dark_onSecondaryContainer = Color(0xFFD3E5F5)
val dark_tertiary = Color(0xFF8ECDFF)
val dark_onTertiary = Color(0xFF00344F)
val dark_tertiaryContainer = Color(0xFF004B71)
val dark_onTertiaryContainer = Color(0xFFCBE6FF)
val dark_error = Color(0xFFFFB4AB)
val dark_errorContainer = Color(0xFF93000A)
val dark_onError = Color(0xFF690005)
val dark_onErrorContainer = Color(0xFFFFDAD6)
val dark_background = Color(0xFF191C1E)
val dark_onBackground = Color(0xFFE2E2E5)
val dark_surface = Color(0xFF191C1E)
val dark_onSurface = Color(0xFFE2E2E5)
val dark_surfaceVariant = Color(0xFF41474D)
val dark_onSurfaceVariant = Color(0xFFC1C7CE)
val dark_outline = Color(0xFF8B9198)
val dark_inverseOnSurface = Color(0xFF191C1E)
val dark_inverseSurface = Color(0xFFE2E2E5)
val dark_inversePrimary = Color(0xFF006590)
val dark_shadow = Color(0xFF000000)
val dark_surfaceTint = Color(0xFF89CEFF)
val dark_outlineVariant = Color(0xFF41474D)
val dark_scrim = Color(0xFF000000)

fun Color.lightColor() = this.copy(0.3f)
fun Color.secondaryColor() = this.copy(0.7f)
fun Color.divider() = this.copy(0.1f)