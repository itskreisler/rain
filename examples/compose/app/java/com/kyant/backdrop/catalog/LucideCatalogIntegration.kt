package com.kyant.backdrop.catalog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Catálogo de los iconos animados importados de animateicons/Lucide. */internal val lucideIconItems: List<IconItem> =
    listOf(
        IconItem(id = "lucide_a-arrow-down", name = "AArrowDownIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_a-arrow-up", name = "AArrowUpIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_accessibility", name = "AccessibilityIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_activity", name = "ActivityIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_alarm-clock-check", name = "AlarmClockCheckIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_alarm-clock", name = "AlarmClockIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_alarm-clock-minus", name = "AlarmClockMinusIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_alarm-clock-off", name = "AlarmClockOffIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_alarm-clock-plus", name = "AlarmClockPlusIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_align-center", name = "AlignCenterIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_align-justify", name = "AlignJustifyIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_align-left", name = "AlignLeftIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_align-right", name = "AlignRightIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_anchor", name = "AnchorIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_angry", name = "AngryIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_annoyed", name = "AnnoyedIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_app-window", name = "AppWindowIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_archive", name = "ArchiveIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_arrow-big-down-dash", name = "ArrowBigDownDashIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_arrow-big-down", name = "ArrowBigDownIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_arrow-big-left-dash", name = "ArrowBigLeftDashIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_arrow-big-left", name = "ArrowBigLeftIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_arrow-big-right-dash", name = "ArrowBigRightDashIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_arrow-big-right", name = "ArrowBigRightIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_arrow-big-up-dash", name = "ArrowBigUpDashIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_arrow-big-up", name = "ArrowBigUpIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_arrow-down-0-1", name = "ArrowDown01Icon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_arrow-down-1-0", name = "ArrowDown10Icon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_arrow-down-a-z", name = "ArrowDownAZIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_arrow-down-from-line", name = "ArrowDownFromLineIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_arrow-down", name = "ArrowDownIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_arrow-down-left", name = "ArrowDownLeftIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_arrow-down-narrow-wide", name = "ArrowDownNarrowWideIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_arrow-down-right", name = "ArrowDownRightIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_arrow-down-to-dot", name = "ArrowDownToDotIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_arrow-down-to-line", name = "ArrowDownToLineIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_arrow-down-up", name = "ArrowDownUpIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_arrow-down-wide-narrow", name = "ArrowDownWideNarrowIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_arrow-down-z-a", name = "ArrowDownZAIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_arrow-left-from-line", name = "ArrowLeftFromLineIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_arrow-left", name = "ArrowLeftIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_arrow-left-right", name = "ArrowLeftRightIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_arrow-left-to-line", name = "ArrowLeftToLineIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_arrow-right-from-line", name = "ArrowRightFromLineIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_arrow-right", name = "ArrowRightIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_arrow-right-left", name = "ArrowRightLeftIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_arrow-right-to-line", name = "ArrowRightToLineIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_arrow-up-0-1", name = "ArrowUp01Icon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_arrow-up-1-0", name = "ArrowUp10Icon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_arrow-up-a-z", name = "ArrowUpAZIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_arrow-up-down", name = "ArrowUpDownIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_arrow-up-from-dot", name = "ArrowUpFromDotIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_arrow-up-from-line", name = "ArrowUpFromLineIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_arrow-up", name = "ArrowUpIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_arrow-up-left", name = "ArrowUpLeftIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_arrow-up-narrow-wide", name = "ArrowUpNarrowWideIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_arrow-up-right", name = "ArrowUpRightIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_arrow-up-to-line", name = "ArrowUpToLineIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_arrow-up-wide-narrow", name = "ArrowUpWideNarrowIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_arrow-up-z-a", name = "ArrowUpZAIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_at-sign", name = "AtSignIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_atom", name = "AtomIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_audio-lines", name = "AudioLinesIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_audio-lines-off", name = "AudioLinesOffIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_audio-lines-x", name = "AudioLinesXIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_audio-waveform", name = "AudioWaveformIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_badge-cent", name = "BadgeCentIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_badge-check", name = "BadgeCheckIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_badge-dollar-sign", name = "BadgeDollarSignIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_badge-info", name = "BadgeInfoIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_badge-percent", name = "BadgePercentIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_baggage-claim", name = "BaggageClaimIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_ban", name = "BanIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_banknote-arrow-down", name = "BanknoteArrowDownIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_banknote-arrow-up", name = "BanknoteArrowUpIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_banknote-check", name = "BanknoteCheckIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_banknote", name = "BanknoteIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_banknote-x", name = "BanknoteXIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_barcode", name = "BarcodeIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_battery-charging", name = "BatteryChargingIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_battery-full", name = "BatteryFullIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_battery", name = "BatteryIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_battery-low", name = "BatteryLowIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_battery-medium", name = "BatteryMediumIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_battery-plus", name = "BatteryPlusIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_battery-warning", name = "BatteryWarningIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_bell-dot", name = "BellDotIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_bell-electric", name = "BellElectricIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_bell", name = "BellIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_bell-minus", name = "BellMinusIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_bell-off", name = "BellOffIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_bell-plus", name = "BellPlusIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_bell-ring", name = "BellRingIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_biceps-flexed", name = "BicepsFlexedIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_bike", name = "BikeIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_bitcoin", name = "BitcoinIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_blend", name = "BlendIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_blocks", name = "BlocksIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_bluetooth", name = "BluetoothIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_bold", name = "BoldIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_bolt", name = "BoltIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_book-open-check", name = "BookOpenCheckIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_book-open", name = "BookOpenIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_book-open-text", name = "BookOpenTextIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_bookmark-check", name = "BookmarkCheckIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_bookmark", name = "BookmarkIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_bookmark-minus", name = "BookmarkMinusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_bookmark-off", name = "BookmarkOffIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_bookmark-plus", name = "BookmarkPlusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_bookmark-x", name = "BookmarkXIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_bot", name = "LucideBotIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_box", name = "BoxIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_boxes", name = "BoxesIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_brain", name = "BrainIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_briefcase", name = "BriefcaseIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_bring-to-front", name = "BringToFrontIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_bug", name = "BugIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_bug-off", name = "BugOffIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_bug-play", name = "BugPlayIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_building-2", name = "Building2Icon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_building-complex", name = "BuildingComplexIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_building-complex-plus", name = "BuildingComplexPlusIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_building", name = "BuildingIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_calculator", name = "CalculatorIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_calendar-arrow-down", name = "CalendarArrowDownIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_calendar-arrow-up", name = "CalendarArrowUpIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_calendar-check", name = "CalendarCheckIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_calendar-chevrons-right", name = "CalendarChevronsRightIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_calendar-clock", name = "CalendarClockIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_calendar-cog", name = "CalendarCogIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_calendar-days", name = "CalendarDaysIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_calendar-fold", name = "CalendarFoldIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_calendar-heart", name = "CalendarHeartIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_calendar", name = "CalendarIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_calendar-minus", name = "CalendarMinusIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_calendar-off", name = "CalendarOffIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_calendar-plus", name = "CalendarPlusIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_calendar-range", name = "CalendarRangeIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_calendar-search", name = "CalendarSearchIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_calendar-sync", name = "CalendarSyncIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_calendar-x", name = "CalendarXIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_calendars", name = "CalendarsIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_camera", name = "CameraIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_camera-off", name = "CameraOffIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_captions", name = "CaptionsIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_car", name = "CarIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_case-lower", name = "CaseLowerIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_case-sensitive", name = "CaseSensitiveIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_case-upper", name = "CaseUpperIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_cast", name = "CastIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_cat", name = "CatIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_cctv", name = "CctvIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_cctv-off", name = "CctvOffIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_chart-area", name = "ChartAreaIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_chart-bar-big", name = "ChartBarBigIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_chart-bar-decreasing", name = "ChartBarDecreasingIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_chart-bar", name = "ChartBarIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_chart-bar-increasing", name = "ChartBarIncreasingIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_chart-candlestick", name = "ChartCandlestickIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_chart-column-big", name = "ChartColumnBigIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_chart-column-decreasing", name = "ChartColumnDecreasingIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_chart-column", name = "ChartColumnIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_chart-column-increasing", name = "ChartColumnIncreasingIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_chart-gantt", name = "ChartGanttIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_chart-line", name = "ChartLineIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_chart-network", name = "ChartNetworkIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_chart-no-axes-column", name = "ChartNoAxesColumnIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_chart-no-axes-combined", name = "ChartNoAxesCombinedIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_chart-pie", name = "ChartPieIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_chart-scatter", name = "ChartScatterIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_chart-spline", name = "ChartSplineIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_check-check", name = "CheckCheckIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_check", name = "CheckIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_chevron-down", name = "ChevronDownIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_chevron-first", name = "ChevronFirstIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_chevron-last", name = "ChevronLastIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_chevron-left", name = "ChevronLeftIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_chevron-right", name = "ChevronRightIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_chevron-up", name = "ChevronUpIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_chevrons-down", name = "ChevronsDownIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_chevrons-down-up", name = "ChevronsDownUpIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_chevrons-left", name = "ChevronsLeftIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_chevrons-left-right-ellipsis", name = "ChevronsLeftRightEllipsisIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_chevrons-left-right", name = "ChevronsLeftRightIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_chevrons-right", name = "ChevronsRightIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_chevrons-right-left", name = "ChevronsRightLeftIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_chevrons-up-down", name = "ChevronsUpDownIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_chevrons-up", name = "ChevronsUpIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_chrome", name = "ChromeIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_circle-alert", name = "CircleAlertIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_circle-arrow-down", name = "CircleArrowDownIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_circle-arrow-left", name = "CircleArrowLeftIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_circle-arrow-out-down-left", name = "CircleArrowOutDownLeftIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_circle-arrow-out-down-right", name = "CircleArrowOutDownRightIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_circle-arrow-out-up-left", name = "CircleArrowOutUpLeftIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_circle-arrow-out-up-right", name = "CircleArrowOutUpRightIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_circle-arrow-right", name = "CircleArrowRightIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_circle-arrow-up", name = "CircleArrowUpIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_circle-check-big", name = "CircleCheckBigIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_circle-check", name = "CircleCheckIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_circle-chevron-down", name = "CircleChevronDownIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_circle-chevron-left", name = "CircleChevronLeftIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_circle-chevron-right", name = "CircleChevronRightIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_circle-chevron-up", name = "CircleChevronUpIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_circle-dashed", name = "CircleDashedIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_circle-dollar-sign", name = "CircleDollarSignIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_circle-dot", name = "CircleDotIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_circle-ellipsis", name = "CircleEllipsisIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_circle-euro", name = "CircleEuroIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_circle", name = "CircleIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_circle-minus", name = "CircleMinusIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_circle-off", name = "CircleOffIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_circle-pause", name = "CirclePauseIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_circle-percent", name = "CirclePercentIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_circle-play", name = "CirclePlayIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_circle-plus", name = "CirclePlusIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_circle-pound-sterling", name = "CirclePoundSterlingIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_circle-power", name = "CirclePowerIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_circle-question-mark", name = "CircleQuestionMarkIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_circle-slash-2", name = "CircleSlash2Icon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_circle-slash", name = "CircleSlashIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_circle-star", name = "CircleStarIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_circle-stop", name = "CircleStopIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_circle-user", name = "CircleUserIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_circle-user-round", name = "CircleUserRoundIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_circle-x", name = "CircleXIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_clapperboard", name = "ClapperboardIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_clipboard-check", name = "ClipboardCheckIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_clipboard-clock", name = "ClipboardClockIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_clipboard", name = "ClipboardIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_clipboard-list", name = "ClipboardListIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_clipboard-paste", name = "ClipboardPasteIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_clock-alert", name = "ClockAlertIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_clock-arrow-down", name = "ClockArrowDownIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_clock-arrow-left", name = "ClockArrowLeftIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_clock-arrow-right", name = "ClockArrowRightIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_clock-arrow-up", name = "ClockArrowUpIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_clock-check", name = "ClockCheckIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_clock-fading", name = "ClockFadingIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_clock", name = "ClockIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_clock-plus", name = "ClockPlusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_cloud-check", name = "CloudCheckIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_cloud-download", name = "CloudDownloadIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_cloud-drizzle", name = "CloudDrizzleIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_cloud-fog", name = "CloudFogIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_cloud-hail", name = "CloudHailIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_cloud", name = "CloudIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_cloud-lightning", name = "CloudLightningIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_cloud-moon", name = "CloudMoonIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_cloud-moon-rain", name = "CloudMoonRainIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_cloud-off", name = "CloudOffIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_cloud-rain", name = "CloudRainIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_cloud-rain-wind", name = "CloudRainWindIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_cloud-snow", name = "CloudSnowIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_cloud-sun", name = "CloudSunIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_cloud-sun-rain", name = "CloudSunRainIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_cloud-sync", name = "LucideCloudSyncIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_cloud-upload", name = "CloudUploadIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_cloudy", name = "CloudyIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_code", name = "CodeIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_code-xml", name = "CodeXmlIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_coffee", name = "CoffeeIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_coins", name = "CoinsIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_columns-2", name = "Columns2Icon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_columns-3", name = "Columns3Icon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_columns-4", name = "Columns4Icon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_command", name = "CommandIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_compass", name = "CompassIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_component", name = "ComponentIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_contact", name = "ContactIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_contact-round", name = "ContactRoundIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_contrast", name = "ContrastIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_cookie", name = "CookieIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_copy-check", name = "CopyCheckIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_copy", name = "CopyIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_copy-plus", name = "CopyPlusIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_corner-down-left", name = "CornerDownLeftIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_corner-down-right", name = "CornerDownRightIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_corner-left-down", name = "CornerLeftDownIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_corner-left-up", name = "CornerLeftUpIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_corner-right-down", name = "CornerRightDownIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_corner-right-up", name = "CornerRightUpIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_corner-up-left", name = "CornerUpLeftIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_corner-up-right", name = "CornerUpRightIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_cpu", name = "CpuIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_credit-card-check", name = "CreditCardCheckIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_credit-card", name = "CreditCardIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_credit-card-minus", name = "CreditCardMinusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_credit-card-plus", name = "CreditCardPlusIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_credit-card-reader", name = "CreditCardReaderIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_credit-card-x", name = "CreditCardXIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_crop", name = "CropIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_crown", name = "CrownIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_database-arrow-down", name = "DatabaseArrowDownIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_database-arrow-up", name = "DatabaseArrowUpIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_database-backup", name = "DatabaseBackupIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_database-check", name = "DatabaseCheckIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_database", name = "DatabaseIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_database-minus", name = "DatabaseMinusIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_database-plus", name = "DatabasePlusIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_database-search", name = "DatabaseSearchIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_database-x", name = "DatabaseXIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_database-zap", name = "DatabaseZapIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_delete", name = "DeleteIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_diamond", name = "DiamondIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_diamond-minus", name = "DiamondMinusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_diamond-percent", name = "DiamondPercentIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_diamond-plus", name = "DiamondPlusIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_dice-5", name = "Dice5Icon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_diff", name = "DiffIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_disc-2", name = "Disc2Icon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_disc-3", name = "Disc3Icon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_disc-album", name = "DiscAlbumIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_disc", name = "DiscIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_dog", name = "DogIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_dollar-sign", name = "DollarSignIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_door-open", name = "DoorOpenIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_download", name = "DownloadIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_droplet", name = "DropletIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_droplets", name = "DropletsIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_drum", name = "DrumIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_dumbbell", name = "DumbbellIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_ellipsis", name = "EllipsisIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_ellipsis-vertical", name = "EllipsisVerticalIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_eraser", name = "EraserIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_euro", name = "EuroIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_expand", name = "ExpandIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_external-link", name = "ExternalLinkIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_eye-closed", name = "EyeClosedIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_eye", name = "EyeIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_eye-off", name = "EyeOffIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_facebook", name = "FacebookIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_factory", name = "FactoryIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_fast-forward", name = "FastForwardIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_feather", name = "FeatherIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_figma", name = "FigmaIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_file-archive", name = "FileArchiveIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_file-check", name = "FileCheckIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_file-clock", name = "FileClockIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_file-code", name = "FileCodeIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_file-down", name = "FileDownIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_file-heart", name = "FileHeartIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_file", name = "FileIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_file-image", name = "FileImageIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_file-key", name = "FileKeyIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_file-lock", name = "FileLockIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_file-minus", name = "FileMinusIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_file-music", name = "FileMusicIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_file-pen", name = "FilePenIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_file-plus", name = "FilePlusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_file-scan", name = "FileScanIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_file-search", name = "FileSearchIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_file-spreadsheet", name = "FileSpreadsheetIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_file-terminal", name = "FileTerminalIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_file-text", name = "FileTextIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_file-type", name = "FileTypeIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_file-up", name = "FileUpIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_file-x", name = "FileXIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_files", name = "FilesIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_film", name = "FilmIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_filter", name = "FilterIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_fingerprint-pattern", name = "FingerprintPatternIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_fish", name = "FishIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_flag", name = "FlagIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_flag-off", name = "FlagOffIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_flag-triangle-right", name = "FlagTriangleRightIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_flame", name = "LucideFlameIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_flask-conical", name = "FlaskConicalIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_fold-vertical", name = "FoldVerticalIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_folder-check", name = "FolderCheckIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_folder-closed", name = "FolderClosedIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_folder-dot", name = "FolderDotIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_folder-heart", name = "FolderHeartIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_folder", name = "FolderIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_folder-key", name = "FolderKeyIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_folder-lock", name = "FolderLockIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_folder-minus", name = "FolderMinusIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_folder-open", name = "FolderOpenIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_folder-plus", name = "FolderPlusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_folder-search", name = "FolderSearchIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_folder-tree", name = "FolderTreeIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_folder-x", name = "FolderXIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_footprints", name = "FootprintsIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_framer", name = "FramerIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_fullscreen", name = "FullscreenIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_funnel-x", name = "FunnelXIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_gallery-horizontal", name = "GalleryHorizontalIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_gamepad", name = "GamepadIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_gauge", name = "GaugeIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_gem", name = "GemIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_georgian-lari", name = "GeorgianLariIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_ghost", name = "GhostIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_gift", name = "GiftIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_git-branch", name = "GitBranchIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_git-branch-minus", name = "GitBranchMinusIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_git-branch-plus", name = "GitBranchPlusIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_git-commit-horizontal", name = "GitCommitHorizontalIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_git-commit-vertical", name = "GitCommitVerticalIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_git-compare-arrows", name = "GitCompareArrowsIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_git-compare", name = "GitCompareIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_git-fork", name = "GitForkIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_git-graph", name = "GitGraphIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_git-merge-conflict", name = "GitMergeConflictIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_git-merge", name = "GitMergeIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_git-pull-request-arrow", name = "GitPullRequestArrowIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_git-pull-request-closed", name = "GitPullRequestClosedIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_git-pull-request-create-arrow", name = "GitPullRequestCreateArrowIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_git-pull-request-create", name = "GitPullRequestCreateIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_git-pull-request-draft", name = "GitPullRequestDraftIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_git-pull-request", name = "GitPullRequestIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_github", name = "GithubIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_gitlab", name = "GitlabIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_globe", name = "GlobeIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_globe-lock", name = "GlobeLockIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_graduation-cap", name = "GraduationCapIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_grid-2x2-check", name = "Grid2x2CheckIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_grid-2x2", name = "Grid2x2Icon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_grid-2x2-plus", name = "Grid2x2PlusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_grid-2x2-x", name = "Grid2x2XIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_grid-3x2", name = "Grid3x2Icon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_grid-3x3", name = "Grid3x3Icon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_grip-vertical", name = "GripVerticalIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_guitar", name = "GuitarIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_hammer", name = "HammerIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_hand-coins", name = "HandCoinsIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_hand-heart", name = "HandHeartIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_hand", name = "HandIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_handshake", name = "HandshakeIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_hard-drive-download", name = "HardDriveDownloadIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_hard-drive", name = "HardDriveIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_hard-drive-upload", name = "HardDriveUploadIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_hash", name = "HashIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_haze", name = "HazeIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_heading-1", name = "Heading1Icon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_heading-2", name = "Heading2Icon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_heading-3", name = "Heading3Icon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_heading-4", name = "Heading4Icon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_heading-5", name = "Heading5Icon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_heading-6", name = "Heading6Icon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_heading", name = "HeadingIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_headphone-off", name = "HeadphoneOffIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_headphones", name = "HeadphonesIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_headset", name = "HeadsetIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_heart-crack", name = "HeartCrackIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_heart-handshake", name = "HeartHandshakeIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_heart", name = "HeartIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_heart-minus", name = "HeartMinusIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_heart-off", name = "HeartOffIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_heart-plus", name = "HeartPlusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_heart-pulse", name = "HeartPulseIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_heart-x", name = "HeartXIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_hexagon", name = "HexagonIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_highlighter", name = "HighlighterIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_history", name = "HistoryIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_hospital", name = "HospitalIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_hotel", name = "HotelIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_hourglass", name = "HourglassIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_house-cog", name = "HouseCogIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_house-heart", name = "HouseHeartIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_house", name = "HouseIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_house-plug", name = "HousePlugIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_house-plus", name = "HousePlusIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_house-wifi", name = "HouseWifiIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_image", name = "ImageIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_image-off", name = "ImageOffIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_image-plus", name = "ImagePlusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_inbox", name = "InboxIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_indent-decrease", name = "IndentDecreaseIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_indent-increase", name = "IndentIncreaseIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_indian-rupee", name = "IndianRupeeIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_infinity", name = "InfinityIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_info", name = "InfoIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_instagram", name = "InstagramIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_italic", name = "ItalicIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_japanese-yen", name = "JapaneseYenIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_kanban", name = "KanbanIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_key", name = "KeyIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_key-round", name = "KeyRoundIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_key-square", name = "KeySquareIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_keyboard", name = "KeyboardIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_landmark", name = "LandmarkIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_languages", name = "LanguagesIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_laptop", name = "LaptopIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_laptop-minimal-check", name = "LaptopMinimalCheckIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_laptop-minimal", name = "LaptopMinimalIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_layers", name = "LayersIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_layout-dashboard", name = "LayoutDashboardIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_layout-grid", name = "LayoutGridIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_layout-list", name = "LayoutListIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_layout-panel-left", name = "LayoutPanelLeftIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_layout-panel-top", name = "LayoutPanelTopIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_layout-template", name = "LayoutTemplateIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_leaf", name = "LeafIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_life-buoy", name = "LifeBuoyIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_lightbulb", name = "LightbulbIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_link", name = "LinkIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_linkedin", name = "LinkedinIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_list-check", name = "ListCheckIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_list-checks", name = "ListChecksIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_list-chevrons-down-up", name = "ListChevronsDownUpIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_list-chevrons-up-down", name = "ListChevronsUpDownIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_list-collapse", name = "ListCollapseIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_list-end", name = "ListEndIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_list-filter", name = "ListFilterIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_list-filter-plus", name = "ListFilterPlusIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_list", name = "ListIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_list-minus", name = "ListMinusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_list-ordered", name = "ListOrderedIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_list-plus", name = "ListPlusIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_list-start", name = "ListStartIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_list-todo", name = "ListTodoIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_list-tree", name = "ListTreeIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_list-x", name = "ListXIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_loader-circle", name = "LoaderCircleIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_loader", name = "LoaderIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_locate-fixed", name = "LocateFixedIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_locate", name = "LocateIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_locate-off", name = "LocateOffIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_lock", name = "LockIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_lock-keyhole", name = "LockKeyholeIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_lock-keyhole-open", name = "LockKeyholeOpenIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_lock-open", name = "LockOpenIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_log-in", name = "LogInIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_log-out", name = "LogOutIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_magnet", name = "MagnetIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_mail-check", name = "MailCheckIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_mail-clock", name = "MailClockIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_mail", name = "MailIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_mail-minus", name = "MailMinusIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_mail-open", name = "MailOpenIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_mail-plus", name = "MailPlusIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_mail-search", name = "MailSearchIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_mail-warning", name = "MailWarningIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_mail-x", name = "MailXIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_mailbox", name = "MailboxIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_mails", name = "MailsIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_map", name = "MapIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_map-minus", name = "MapMinusIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_map-pin-check", name = "MapPinCheckIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_map-pin-check-inside", name = "MapPinCheckInsideIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_map-pin-house", name = "MapPinHouseIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_map-pin", name = "MapPinIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_map-pin-minus", name = "MapPinMinusIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_map-pin-minus-inside", name = "MapPinMinusInsideIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_map-pin-off", name = "MapPinOffIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_map-pin-pen", name = "MapPinPenIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_map-pin-plus", name = "MapPinPlusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_map-pin-plus-inside", name = "MapPinPlusInsideIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_map-pin-search", name = "MapPinSearchIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_map-pin-x", name = "MapPinXIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_map-pin-x-inside", name = "MapPinXInsideIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_map-pinned", name = "MapPinnedIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_map-plus", name = "MapPlusIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_mars", name = "MarsIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_maximize-2", name = "Maximize2Icon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_maximize", name = "MaximizeIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_medal", name = "MedalIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_megaphone", name = "MegaphoneIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_memory-stick", name = "MemoryStickIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_menu", name = "MenuIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_message-circle-check", name = "MessageCircleCheckIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_message-circle-code", name = "MessageCircleCodeIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_message-circle-dashed-check", name = "MessageCircleDashedCheckIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_message-circle-dashed", name = "MessageCircleDashedIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_message-circle-heart", name = "MessageCircleHeartIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_message-circle", name = "MessageCircleIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_message-circle-more", name = "MessageCircleMoreIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_message-circle-off", name = "MessageCircleOffIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_message-circle-plus", name = "MessageCirclePlusIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_message-circle-question-mark", name = "MessageCircleQuestionMarkIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_message-circle-reply", name = "MessageCircleReplyIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_message-circle-warning", name = "MessageCircleWarningIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_message-circle-x", name = "MessageCircleXIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_message-square-check", name = "MessageSquareCheckIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_message-square-code", name = "MessageSquareCodeIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_message-square-dashed", name = "MessageSquareDashedIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_message-square-diff", name = "MessageSquareDiffIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_message-square-dot", name = "MessageSquareDotIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_message-square-heart", name = "MessageSquareHeartIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_message-square", name = "MessageSquareIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_message-square-lock", name = "MessageSquareLockIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_message-square-more", name = "MessageSquareMoreIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_message-square-off", name = "MessageSquareOffIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_message-square-plus", name = "MessageSquarePlusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_message-square-quote", name = "MessageSquareQuoteIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_message-square-reply", name = "MessageSquareReplyIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_message-square-share", name = "MessageSquareShareIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_message-square-text", name = "MessageSquareTextIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_message-square-warning", name = "MessageSquareWarningIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_message-square-x", name = "MessageSquareXIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_messages-circle", name = "MessagesCircleIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_messages-square", name = "MessagesSquareIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_mic-audio-lines", name = "MicAudioLinesIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_mic", name = "MicIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_mic-off", name = "MicOffIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_mic-signal", name = "MicSignalIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_mic-vocal", name = "MicVocalIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_microchip", name = "MicrochipIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_milestone", name = "MilestoneIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_minimize-2", name = "Minimize2Icon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_minimize", name = "MinimizeIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_minus", name = "MinusIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_monitor-check", name = "MonitorCheckIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_monitor-cloud", name = "MonitorCloudIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_monitor-cog", name = "MonitorCogIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_monitor-dot", name = "MonitorDotIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_monitor-down", name = "MonitorDownIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_monitor", name = "MonitorIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_monitor-off", name = "MonitorOffIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_monitor-pause", name = "MonitorPauseIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_monitor-pc", name = "MonitorPcIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_monitor-play", name = "MonitorPlayIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_monitor-smartphone", name = "MonitorSmartphoneIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_monitor-speaker", name = "MonitorSpeakerIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_monitor-stop", name = "MonitorStopIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_monitor-up", name = "MonitorUpIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_monitor-x", name = "MonitorXIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_moon", name = "MoonIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_moon-star", name = "MoonStarIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_mountain", name = "MountainIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_mouse", name = "MouseIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_mouse-pointer-click", name = "MousePointerClickIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_mouse-pointer", name = "MousePointerIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_move-diagonal-2", name = "MoveDiagonal2Icon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_move-diagonal", name = "MoveDiagonalIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_move-down", name = "MoveDownIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_move-down-left", name = "MoveDownLeftIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_move-down-right", name = "MoveDownRightIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_move-horizontal", name = "MoveHorizontalIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_move", name = "MoveIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_move-left", name = "MoveLeftIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_move-right", name = "MoveRightIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_move-up", name = "MoveUpIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_move-up-left", name = "MoveUpLeftIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_move-up-right", name = "MoveUpRightIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_move-vertical", name = "MoveVerticalIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_music-2", name = "Music2Icon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_music-3", name = "Music3Icon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_music-4", name = "Music4Icon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_music", name = "MusicIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_navigation-2", name = "Navigation2Icon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_navigation", name = "NavigationIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_network", name = "NetworkIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_nfc", name = "NfcIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_notebook-pen", name = "NotebookPenIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_octagon-alert", name = "OctagonAlertIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_octagon", name = "OctagonIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_octagon-minus", name = "OctagonMinusIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_octagon-pause", name = "OctagonPauseIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_octagon-x", name = "OctagonXIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_orbit", name = "OrbitIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_package-check", name = "PackageCheckIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_package", name = "PackageIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_package-open", name = "PackageOpenIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_package-plus", name = "PackagePlusIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_package-search", name = "PackageSearchIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_package-x", name = "PackageXIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_paint-bucket", name = "PaintBucketIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_paintbrush", name = "PaintbrushIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_palette", name = "PaletteIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_panel-bottom-close", name = "PanelBottomCloseIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_panel-bottom-dashed", name = "PanelBottomDashedIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_panel-bottom", name = "PanelBottomIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_panel-bottom-open", name = "PanelBottomOpenIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_panel-left-close", name = "PanelLeftCloseIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_panel-left-dashed", name = "PanelLeftDashedIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_panel-left", name = "PanelLeftIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_panel-left-open", name = "PanelLeftOpenIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_panel-left-right-dashed", name = "PanelLeftRightDashedIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_panel-right-close", name = "PanelRightCloseIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_panel-right-dashed", name = "PanelRightDashedIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_panel-right", name = "PanelRightIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_panel-right-open", name = "PanelRightOpenIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_panel-top-bottom-dashed", name = "PanelTopBottomDashedIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_panel-top-close", name = "PanelTopCloseIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_panel-top-dashed", name = "PanelTopDashedIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_panel-top", name = "PanelTopIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_panel-top-open", name = "PanelTopOpenIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_paperclip", name = "PaperclipIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_party-popper", name = "PartyPopperIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_pause", name = "LucidePauseIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_paw-print", name = "PawPrintIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_pen-tool", name = "PenToolIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_pencil", name = "PencilIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_pentagon", name = "PentagonIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_percent", name = "PercentIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_philippine-peso", name = "PhilippinePesoIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_phone-call", name = "PhoneCallIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_phone-forwarded", name = "PhoneForwardedIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_phone", name = "PhoneIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_phone-incoming", name = "PhoneIncomingIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_phone-missed", name = "PhoneMissedIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_phone-off", name = "PhoneOffIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_piano", name = "PianoIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_picture-in-picture", name = "PictureInPictureIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_piggy-bank", name = "PiggyBankIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_pilcrow", name = "PilcrowIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_pilcrow-left", name = "PilcrowLeftIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_pilcrow-right", name = "PilcrowRightIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_pill", name = "PillIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_pin", name = "PinIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_pin-off", name = "PinOffIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_pipette", name = "PipetteIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_plane", name = "PlaneIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_play", name = "LucidePlayIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_play-off", name = "PlayOffIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_plug-2", name = "Plug2Icon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_plug", name = "PlugIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_plug-zap", name = "PlugZapIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_plus", name = "PlusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_podcast", name = "PodcastIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_pound-sterling", name = "PoundSterlingIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_power", name = "PowerIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_power-off", name = "PowerOffIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_presentation", name = "PresentationIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_printer", name = "PrinterIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_puzzle", name = "PuzzleIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_qr-code", name = "QrCodeIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_quote", name = "QuoteIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_rabbit", name = "RabbitIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_radar", name = "RadarIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_radio", name = "RadioIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_radio-off", name = "RadioOffIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_radio-receiver", name = "RadioReceiverIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_rainbow", name = "RainbowIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_receipt", name = "ReceiptIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_receipt-text", name = "ReceiptTextIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_recycle", name = "RecycleIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_redo", name = "RedoIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_refresh-ccw-dot", name = "RefreshCcwDotIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_refresh-ccw", name = "RefreshCcwIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_refresh-cw", name = "RefreshCwIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_refresh-cw-off", name = "RefreshCwOffIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_regex", name = "RegexIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_remove-formatting", name = "RemoveFormattingIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_repeat-1", name = "Repeat1Icon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_repeat-2", name = "Repeat2Icon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_repeat", name = "RepeatIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_reply-all", name = "ReplyAllIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_reply", name = "ReplyIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_rewind", name = "RewindIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_rocket", name = "RocketIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_rotate-ccw", name = "RotateCcwIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_rotate-cw", name = "RotateCwIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_route", name = "RouteIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_route-off", name = "RouteOffIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_router", name = "RouterIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_rows-2", name = "Rows2Icon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_rows-3", name = "Rows3Icon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_rows-4", name = "Rows4Icon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_rss", name = "RssIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_ruler", name = "RulerIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_russian-ruble", name = "RussianRubleIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_saudi-riyal", name = "SaudiRiyalIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_save", name = "SaveIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_scan", name = "ScanIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_scan-line", name = "ScanLineIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_scan-qr-code", name = "ScanQrCodeIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_school", name = "SchoolIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_scissors", name = "ScissorsIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_screen-share", name = "ScreenShareIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_scroll-text", name = "ScrollTextIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_search", name = "SearchIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_search-x", name = "SearchXIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_send-horizontal", name = "SendHorizontalIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_send", name = "SendIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_server-cog", name = "ServerCogIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_server-crash", name = "ServerCrashIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_server", name = "ServerIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_server-off", name = "ServerOffIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_server-plus", name = "ServerPlusIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_settings", name = "SettingsIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_shapes", name = "ShapesIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_share", name = "ShareIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_shield-alert", name = "ShieldAlertIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_shield-ban", name = "ShieldBanIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_shield-check", name = "ShieldCheckIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_shield-cog-corner", name = "ShieldCogCornerIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_shield-cog", name = "ShieldCogIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_shield-ellipsis", name = "ShieldEllipsisIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_shield-half", name = "ShieldHalfIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_shield", name = "ShieldIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_shield-keyhole", name = "ShieldKeyholeIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_shield-lock", name = "ShieldLockIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_shield-minus", name = "ShieldMinusIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_shield-off", name = "ShieldOffIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_shield-plus", name = "ShieldPlusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_shield-question-mark", name = "ShieldQuestionMarkIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_shield-user", name = "ShieldUserIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_shield-x", name = "ShieldXIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_shopping-bag", name = "ShoppingBagIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_shopping-basket", name = "ShoppingBasketIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_shopping-cart", name = "ShoppingCartIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_shopping-cart-minus", name = "ShoppingCartMinusIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_shopping-cart-plus", name = "ShoppingCartPlusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_shrink", name = "ShrinkIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_shuffle", name = "ShuffleIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_sigma", name = "SigmaIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_signal-high", name = "SignalHighIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_signal", name = "SignalIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_signal-low", name = "SignalLowIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_signal-medium", name = "SignalMediumIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_signal-zero", name = "SignalZeroIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_signature", name = "SignatureIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_signpost-big", name = "SignpostBigIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_signpost", name = "SignpostIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_siren", name = "SirenIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_skip-back", name = "SkipBackIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_skip-forward", name = "SkipForwardIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_sliders-horizontal", name = "SlidersHorizontalIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_smartphone-charging", name = "SmartphoneChargingIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_smartphone", name = "SmartphoneIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_smartphone-nfc", name = "SmartphoneNfcIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_snowflake", name = "SnowflakeIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_sparkles", name = "SparklesIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_speaker", name = "SpeakerIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_spell-check", name = "SpellCheckIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_spray-can", name = "SprayCanIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_sprout", name = "SproutIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_square-arrow-down", name = "SquareArrowDownIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_square-arrow-down-left", name = "SquareArrowDownLeftIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_square-arrow-down-right", name = "SquareArrowDownRightIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_square-arrow-left", name = "SquareArrowLeftIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_square-arrow-out-down-left", name = "SquareArrowOutDownLeftIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_square-arrow-out-down-right", name = "SquareArrowOutDownRightIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_square-arrow-out-up-left", name = "SquareArrowOutUpLeftIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_square-arrow-out-up-right", name = "SquareArrowOutUpRightIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_square-arrow-right-enter", name = "SquareArrowRightEnterIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_square-arrow-right-exit", name = "SquareArrowRightExitIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_square-arrow-right", name = "SquareArrowRightIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_square-arrow-up", name = "SquareArrowUpIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_square-arrow-up-left", name = "SquareArrowUpLeftIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_square-arrow-up-right", name = "SquareArrowUpRightIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_square-check-big", name = "SquareCheckBigIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_square-check", name = "SquareCheckIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_square-chevron-down", name = "SquareChevronDownIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_square-chevron-left", name = "SquareChevronLeftIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_square-chevron-right", name = "SquareChevronRightIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_square-chevron-up", name = "SquareChevronUpIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_square-code", name = "SquareCodeIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_square-dashed", name = "SquareDashedIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_square-dot", name = "SquareDotIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_square", name = "SquareIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_square-minus", name = "SquareMinusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_square-pause", name = "SquarePauseIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_square-pen", name = "SquarePenIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_square-play", name = "SquarePlayIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_square-plus", name = "SquarePlusIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_square-slash", name = "SquareSlashIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_square-stop", name = "SquareStopIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_square-terminal", name = "SquareTerminalIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_square-user", name = "SquareUserIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_square-user-round", name = "SquareUserRoundIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_square-x", name = "SquareXIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_stamp", name = "StampIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_star-check", name = "StarCheckIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_star-half", name = "StarHalfIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_star", name = "StarIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_star-minus", name = "StarMinusIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_star-off", name = "StarOffIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_star-plus", name = "StarPlusIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_star-x", name = "StarXIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_step-back", name = "StepBackIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_step-forward", name = "StepForwardIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_stethoscope", name = "StethoscopeIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_sticky-note", name = "StickyNoteIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_store", name = "StoreIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_strikethrough", name = "StrikethroughIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_subscript", name = "SubscriptIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_sun-dim", name = "SunDimIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_sun", name = "SunIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_sun-medium", name = "SunMediumIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_sun-moon", name = "SunMoonIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_sun-snow", name = "SunSnowIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_sunrise", name = "SunriseIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_sunset", name = "SunsetIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_superscript", name = "SuperscriptIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_swiss-franc", name = "SwissFrancIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_swords", name = "SwordsIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_table", name = "TableIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_tablet", name = "TabletIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_tag", name = "TagIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_tag-plus", name = "TagPlusIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_tag-x", name = "TagXIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_tags", name = "TagsIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_target", name = "TargetIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_telescope", name = "TelescopeIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_tent", name = "TentIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_terminal", name = "TerminalIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_text-cursor", name = "TextCursorIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_text-initial", name = "TextInitialIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_text-quote", name = "TextQuoteIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_text-search", name = "TextSearchIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_thermometer", name = "ThermometerIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_thermometer-sun", name = "ThermometerSunIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_thumbs-down", name = "ThumbsDownIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_thumbs-up", name = "ThumbsUpIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_ticket-check", name = "TicketCheckIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_ticket", name = "TicketIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_ticket-minus", name = "TicketMinusIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_ticket-percent", name = "TicketPercentIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_ticket-plus", name = "TicketPlusIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_ticket-slash", name = "TicketSlashIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_ticket-x", name = "TicketXIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_timer", name = "TimerIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_timer-off", name = "TimerOffIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_timer-reset", name = "TimerResetIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_toggle-left", name = "ToggleLeftIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_toggle-right", name = "ToggleRightIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_tornado", name = "TornadoIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_traffic-cone", name = "TrafficConeIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_trash-2", name = "Trash2Icon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_trash", name = "TrashIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_trending-down", name = "TrendingDownIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_trending-up-down", name = "TrendingUpDownIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_trending-up", name = "TrendingUpIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_triangle-alert", name = "TriangleAlertIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_triangle-dashed", name = "TriangleDashedIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_triangle", name = "TriangleIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_triangle-right", name = "TriangleRightIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_trophy", name = "TrophyIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_truck", name = "TruckIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_turkish-lira", name = "TurkishLiraIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_tv", name = "TvIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_tv-minimal", name = "TvMinimalIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_twitter", name = "TwitterIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_type", name = "TypeIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_umbrella", name = "UmbrellaIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_underline", name = "UnderlineIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_undo", name = "UndoIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_unfold-vertical", name = "UnfoldVerticalIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_university", name = "UniversityIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_unlink", name = "UnlinkIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_upload", name = "UploadIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_usb", name = "UsbIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_user-check", name = "UserCheckIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_user-cog", name = "UserCogIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_user-group", name = "UserGroupIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_user", name = "UserIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_user-key", name = "UserKeyIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_user-lock", name = "UserLockIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_user-minus", name = "UserMinusIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_user-pen", name = "UserPenIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_user-plus", name = "UserPlusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_user-round-arrow-left", name = "UserRoundArrowLeftIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_user-round-check", name = "UserRoundCheckIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_user-round-cog", name = "UserRoundCogIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_user-round-group", name = "UserRoundGroupIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_user-round", name = "UserRoundIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_user-round-key", name = "UserRoundKeyIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_user-round-minus", name = "UserRoundMinusIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_user-round-pen", name = "UserRoundPenIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_user-round-plus", name = "UserRoundPlusIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_user-round-search", name = "UserRoundSearchIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_user-round-x", name = "UserRoundXIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_user-search", name = "UserSearchIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_user-shield", name = "UserShieldIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_user-star", name = "UserStarIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_user-x", name = "UserXIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_users", name = "UsersIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_users-round", name = "UsersRoundIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_utensils", name = "UtensilsIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_variable", name = "VariableIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_vault", name = "VaultIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_venus", name = "VenusIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_video", name = "VideoIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_video-off", name = "VideoOffIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_volume-1", name = "Volume1Icon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_volume-2", name = "Volume2Icon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_volume", name = "VolumeIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_volume-off", name = "VolumeOffIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_volume-x", name = "VolumeXIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_wallet-cards", name = "WalletCardsIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_wallet", name = "WalletIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_wallet-minimal", name = "WalletMinimalIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_wand-sparkles", name = "WandSparklesIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_warehouse", name = "WarehouseIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_watch", name = "WatchIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_waypoints", name = "WaypointsIcon", color = Color(0xFF8BC34A)),
        IconItem(id = "lucide_webcam", name = "WebcamIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_webcam-off", name = "WebcamOffIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_webhook", name = "WebhookIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_webhook-off", name = "WebhookOffIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_wifi-cog", name = "WifiCogIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_wifi-high", name = "WifiHighIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_wifi", name = "WifiIcon", color = Color(0xFFFF9800)),
        IconItem(id = "lucide_wifi-low", name = "WifiLowIcon", color = Color(0xFF4CAF50)),
        IconItem(id = "lucide_wifi-off", name = "WifiOffIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_wifi-pen", name = "WifiPenIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_wifi-sync", name = "WifiSyncIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_wifi-zero", name = "WifiZeroIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_wind", name = "WindIcon", color = Color(0xFF00BCD4)),
        IconItem(id = "lucide_workflow", name = "WorkflowIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_wrap-text", name = "WrapTextIcon", color = Color(0xFFE91E63)),
        IconItem(id = "lucide_wrench", name = "WrenchIcon", color = Color(0xFF2196F3)),
        IconItem(id = "lucide_x", name = "XIcon", color = Color(0xFF9C27B0)),
        IconItem(id = "lucide_zap", name = "ZapIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_zoom-in", name = "ZoomInIcon", color = Color(0xFFFF5722)),
        IconItem(id = "lucide_zoom-out", name = "ZoomOutIcon", color = Color(0xFF00BCD4)),
    )

/**
 * Estados de todos los iconos de Lucide. Se crean en trozos para no superar
 * el tamaño máximo de método JVM en una sola función composable.
 */
internal class LucideIconStates internal constructor(
    internal val states: Map<String, Any>,
    internal val startActions: Map<String, () -> Unit>,
    internal val stopActions: Map<String, () -> Unit>,
)

@Composable
internal fun rememberLucideIconStates(): LucideIconStates {
    val chunk0 = rememberLucideStatesChunk0()
    val chunk1 = rememberLucideStatesChunk1()
    val chunk2 = rememberLucideStatesChunk2()
    val chunk3 = rememberLucideStatesChunk3()
    val chunk4 = rememberLucideStatesChunk4()
    val chunk5 = rememberLucideStatesChunk5()
    val chunk6 = rememberLucideStatesChunk6()
    val chunk7 = rememberLucideStatesChunk7()
    val chunk8 = rememberLucideStatesChunk8()
    val chunk9 = rememberLucideStatesChunk9()
    return LucideIconStates(
        chunk0.first +         chunk1.first +         chunk2.first +         chunk3.first +         chunk4.first +         chunk5.first +         chunk6.first +         chunk7.first +         chunk8.first +         chunk9.first +         emptyMap(),
        chunk0.second +         chunk1.second +         chunk2.second +         chunk3.second +         chunk4.second +         chunk5.second +         chunk6.second +         chunk7.second +         chunk8.second +         chunk9.second +         emptyMap(),
        chunk0.third +         chunk1.third +         chunk2.third +         chunk3.third +         chunk4.third +         chunk5.third +         chunk6.third +         chunk7.third +         chunk8.third +         chunk9.third +         emptyMap(),
    )
}

@Composable
private fun rememberLucideStatesChunk0(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val aArrowDownIconState = rememberAArrowDownIconState()
    val aArrowUpIconState = rememberAArrowUpIconState()
    val accessibilityIconState = rememberAccessibilityIconState()
    val activityIconState = rememberActivityIconState()
    val alarmClockCheckIconState = rememberAlarmClockCheckIconState()
    val alarmClockIconState = rememberAlarmClockIconState()
    val alarmClockMinusIconState = rememberAlarmClockMinusIconState()
    val alarmClockOffIconState = rememberAlarmClockOffIconState()
    val alarmClockPlusIconState = rememberAlarmClockPlusIconState()
    val alignCenterIconState = rememberAlignCenterIconState()
    val alignJustifyIconState = rememberAlignJustifyIconState()
    val alignLeftIconState = rememberAlignLeftIconState()
    val alignRightIconState = rememberAlignRightIconState()
    val anchorIconState = rememberAnchorIconState()
    val angryIconState = rememberAngryIconState()
    val annoyedIconState = rememberAnnoyedIconState()
    val appWindowIconState = rememberAppWindowIconState()
    val archiveIconState = rememberArchiveIconState()
    val arrowBigDownDashIconState = rememberArrowBigDownDashIconState()
    val arrowBigDownIconState = rememberArrowBigDownIconState()
    val arrowBigLeftDashIconState = rememberArrowBigLeftDashIconState()
    val arrowBigLeftIconState = rememberArrowBigLeftIconState()
    val arrowBigRightDashIconState = rememberArrowBigRightDashIconState()
    val arrowBigRightIconState = rememberArrowBigRightIconState()
    val arrowBigUpDashIconState = rememberArrowBigUpDashIconState()
    val arrowBigUpIconState = rememberArrowBigUpIconState()
    val arrowDown01IconState = rememberArrowDown01IconState()
    val arrowDown10IconState = rememberArrowDown10IconState()
    val arrowDownAZIconState = rememberArrowDownAZIconState()
    val arrowDownFromLineIconState = rememberArrowDownFromLineIconState()
    val arrowDownIconState = rememberArrowDownIconState()
    val arrowDownLeftIconState = rememberArrowDownLeftIconState()
    val arrowDownNarrowWideIconState = rememberArrowDownNarrowWideIconState()
    val arrowDownRightIconState = rememberArrowDownRightIconState()
    val arrowDownToDotIconState = rememberArrowDownToDotIconState()
    val arrowDownToLineIconState = rememberArrowDownToLineIconState()
    val arrowDownUpIconState = rememberArrowDownUpIconState()
    val arrowDownWideNarrowIconState = rememberArrowDownWideNarrowIconState()
    val arrowDownZAIconState = rememberArrowDownZAIconState()
    val arrowLeftFromLineIconState = rememberArrowLeftFromLineIconState()
    val arrowLeftIconState = rememberArrowLeftIconState()
    val arrowLeftRightIconState = rememberArrowLeftRightIconState()
    val arrowLeftToLineIconState = rememberArrowLeftToLineIconState()
    val arrowRightFromLineIconState = rememberArrowRightFromLineIconState()
    val arrowRightIconState = rememberArrowRightIconState()
    val arrowRightLeftIconState = rememberArrowRightLeftIconState()
    val arrowRightToLineIconState = rememberArrowRightToLineIconState()
    val arrowUp01IconState = rememberArrowUp01IconState()
    val arrowUp10IconState = rememberArrowUp10IconState()
    val arrowUpAZIconState = rememberArrowUpAZIconState()
    val arrowUpDownIconState = rememberArrowUpDownIconState()
    val arrowUpFromDotIconState = rememberArrowUpFromDotIconState()
    val arrowUpFromLineIconState = rememberArrowUpFromLineIconState()
    val arrowUpIconState = rememberArrowUpIconState()
    val arrowUpLeftIconState = rememberArrowUpLeftIconState()
    val arrowUpNarrowWideIconState = rememberArrowUpNarrowWideIconState()
    val arrowUpRightIconState = rememberArrowUpRightIconState()
    val arrowUpToLineIconState = rememberArrowUpToLineIconState()
    val arrowUpWideNarrowIconState = rememberArrowUpWideNarrowIconState()
    val arrowUpZAIconState = rememberArrowUpZAIconState()
    val atSignIconState = rememberAtSignIconState()
    val atomIconState = rememberAtomIconState()
    val audioLinesIconState = rememberAudioLinesIconState()
    val audioLinesOffIconState = rememberAudioLinesOffIconState()
    val audioLinesXIconState = rememberAudioLinesXIconState()
    val audioWaveformIconState = rememberAudioWaveformIconState()
    val badgeCentIconState = rememberBadgeCentIconState()
    val badgeCheckIconState = rememberBadgeCheckIconState()
    val badgeDollarSignIconState = rememberBadgeDollarSignIconState()
    val badgeInfoIconState = rememberBadgeInfoIconState()
    val badgePercentIconState = rememberBadgePercentIconState()
    val baggageClaimIconState = rememberBaggageClaimIconState()
    val banIconState = rememberBanIconState()
    val banknoteArrowDownIconState = rememberBanknoteArrowDownIconState()
    val banknoteArrowUpIconState = rememberBanknoteArrowUpIconState()
    val banknoteCheckIconState = rememberBanknoteCheckIconState()
    val banknoteIconState = rememberBanknoteIconState()
    val banknoteXIconState = rememberBanknoteXIconState()
    val barcodeIconState = rememberBarcodeIconState()
    val batteryChargingIconState = rememberBatteryChargingIconState()
    val batteryFullIconState = rememberBatteryFullIconState()
    val batteryIconState = rememberBatteryIconState()
    val batteryLowIconState = rememberBatteryLowIconState()
    val batteryMediumIconState = rememberBatteryMediumIconState()
    val batteryPlusIconState = rememberBatteryPlusIconState()
    val batteryWarningIconState = rememberBatteryWarningIconState()
    val bellDotIconState = rememberBellDotIconState()
    val bellElectricIconState = rememberBellElectricIconState()
    val bellIconState = rememberBellIconState()
    val bellMinusIconState = rememberBellMinusIconState()
    val bellOffIconState = rememberBellOffIconState()
    val bellPlusIconState = rememberBellPlusIconState()
    val bellRingIconState = rememberBellRingIconState()
    val bicepsFlexedIconState = rememberBicepsFlexedIconState()
    val bikeIconState = rememberBikeIconState()
    val bitcoinIconState = rememberBitcoinIconState()
    val blendIconState = rememberBlendIconState()
    val blocksIconState = rememberBlocksIconState()
    val bluetoothIconState = rememberBluetoothIconState()
    val boldIconState = rememberBoldIconState()
    return Triple(
        mapOf(
            "lucide_a-arrow-down" to aArrowDownIconState,
            "lucide_a-arrow-up" to aArrowUpIconState,
            "lucide_accessibility" to accessibilityIconState,
            "lucide_activity" to activityIconState,
            "lucide_alarm-clock-check" to alarmClockCheckIconState,
            "lucide_alarm-clock" to alarmClockIconState,
            "lucide_alarm-clock-minus" to alarmClockMinusIconState,
            "lucide_alarm-clock-off" to alarmClockOffIconState,
            "lucide_alarm-clock-plus" to alarmClockPlusIconState,
            "lucide_align-center" to alignCenterIconState,
            "lucide_align-justify" to alignJustifyIconState,
            "lucide_align-left" to alignLeftIconState,
            "lucide_align-right" to alignRightIconState,
            "lucide_anchor" to anchorIconState,
            "lucide_angry" to angryIconState,
            "lucide_annoyed" to annoyedIconState,
            "lucide_app-window" to appWindowIconState,
            "lucide_archive" to archiveIconState,
            "lucide_arrow-big-down-dash" to arrowBigDownDashIconState,
            "lucide_arrow-big-down" to arrowBigDownIconState,
            "lucide_arrow-big-left-dash" to arrowBigLeftDashIconState,
            "lucide_arrow-big-left" to arrowBigLeftIconState,
            "lucide_arrow-big-right-dash" to arrowBigRightDashIconState,
            "lucide_arrow-big-right" to arrowBigRightIconState,
            "lucide_arrow-big-up-dash" to arrowBigUpDashIconState,
            "lucide_arrow-big-up" to arrowBigUpIconState,
            "lucide_arrow-down-0-1" to arrowDown01IconState,
            "lucide_arrow-down-1-0" to arrowDown10IconState,
            "lucide_arrow-down-a-z" to arrowDownAZIconState,
            "lucide_arrow-down-from-line" to arrowDownFromLineIconState,
            "lucide_arrow-down" to arrowDownIconState,
            "lucide_arrow-down-left" to arrowDownLeftIconState,
            "lucide_arrow-down-narrow-wide" to arrowDownNarrowWideIconState,
            "lucide_arrow-down-right" to arrowDownRightIconState,
            "lucide_arrow-down-to-dot" to arrowDownToDotIconState,
            "lucide_arrow-down-to-line" to arrowDownToLineIconState,
            "lucide_arrow-down-up" to arrowDownUpIconState,
            "lucide_arrow-down-wide-narrow" to arrowDownWideNarrowIconState,
            "lucide_arrow-down-z-a" to arrowDownZAIconState,
            "lucide_arrow-left-from-line" to arrowLeftFromLineIconState,
            "lucide_arrow-left" to arrowLeftIconState,
            "lucide_arrow-left-right" to arrowLeftRightIconState,
            "lucide_arrow-left-to-line" to arrowLeftToLineIconState,
            "lucide_arrow-right-from-line" to arrowRightFromLineIconState,
            "lucide_arrow-right" to arrowRightIconState,
            "lucide_arrow-right-left" to arrowRightLeftIconState,
            "lucide_arrow-right-to-line" to arrowRightToLineIconState,
            "lucide_arrow-up-0-1" to arrowUp01IconState,
            "lucide_arrow-up-1-0" to arrowUp10IconState,
            "lucide_arrow-up-a-z" to arrowUpAZIconState,
            "lucide_arrow-up-down" to arrowUpDownIconState,
            "lucide_arrow-up-from-dot" to arrowUpFromDotIconState,
            "lucide_arrow-up-from-line" to arrowUpFromLineIconState,
            "lucide_arrow-up" to arrowUpIconState,
            "lucide_arrow-up-left" to arrowUpLeftIconState,
            "lucide_arrow-up-narrow-wide" to arrowUpNarrowWideIconState,
            "lucide_arrow-up-right" to arrowUpRightIconState,
            "lucide_arrow-up-to-line" to arrowUpToLineIconState,
            "lucide_arrow-up-wide-narrow" to arrowUpWideNarrowIconState,
            "lucide_arrow-up-z-a" to arrowUpZAIconState,
            "lucide_at-sign" to atSignIconState,
            "lucide_atom" to atomIconState,
            "lucide_audio-lines" to audioLinesIconState,
            "lucide_audio-lines-off" to audioLinesOffIconState,
            "lucide_audio-lines-x" to audioLinesXIconState,
            "lucide_audio-waveform" to audioWaveformIconState,
            "lucide_badge-cent" to badgeCentIconState,
            "lucide_badge-check" to badgeCheckIconState,
            "lucide_badge-dollar-sign" to badgeDollarSignIconState,
            "lucide_badge-info" to badgeInfoIconState,
            "lucide_badge-percent" to badgePercentIconState,
            "lucide_baggage-claim" to baggageClaimIconState,
            "lucide_ban" to banIconState,
            "lucide_banknote-arrow-down" to banknoteArrowDownIconState,
            "lucide_banknote-arrow-up" to banknoteArrowUpIconState,
            "lucide_banknote-check" to banknoteCheckIconState,
            "lucide_banknote" to banknoteIconState,
            "lucide_banknote-x" to banknoteXIconState,
            "lucide_barcode" to barcodeIconState,
            "lucide_battery-charging" to batteryChargingIconState,
            "lucide_battery-full" to batteryFullIconState,
            "lucide_battery" to batteryIconState,
            "lucide_battery-low" to batteryLowIconState,
            "lucide_battery-medium" to batteryMediumIconState,
            "lucide_battery-plus" to batteryPlusIconState,
            "lucide_battery-warning" to batteryWarningIconState,
            "lucide_bell-dot" to bellDotIconState,
            "lucide_bell-electric" to bellElectricIconState,
            "lucide_bell" to bellIconState,
            "lucide_bell-minus" to bellMinusIconState,
            "lucide_bell-off" to bellOffIconState,
            "lucide_bell-plus" to bellPlusIconState,
            "lucide_bell-ring" to bellRingIconState,
            "lucide_biceps-flexed" to bicepsFlexedIconState,
            "lucide_bike" to bikeIconState,
            "lucide_bitcoin" to bitcoinIconState,
            "lucide_blend" to blendIconState,
            "lucide_blocks" to blocksIconState,
            "lucide_bluetooth" to bluetoothIconState,
            "lucide_bold" to boldIconState,
        ),
        mapOf(
            "lucide_a-arrow-down" to { aArrowDownIconState.startAnimation() },
            "lucide_a-arrow-up" to { aArrowUpIconState.startAnimation() },
            "lucide_accessibility" to { accessibilityIconState.startAnimation() },
            "lucide_activity" to { activityIconState.startAnimation() },
            "lucide_alarm-clock-check" to { alarmClockCheckIconState.startAnimation() },
            "lucide_alarm-clock" to { alarmClockIconState.startAnimation() },
            "lucide_alarm-clock-minus" to { alarmClockMinusIconState.startAnimation() },
            "lucide_alarm-clock-off" to { alarmClockOffIconState.startAnimation() },
            "lucide_alarm-clock-plus" to { alarmClockPlusIconState.startAnimation() },
            "lucide_align-center" to { alignCenterIconState.startAnimation() },
            "lucide_align-justify" to { alignJustifyIconState.startAnimation() },
            "lucide_align-left" to { alignLeftIconState.startAnimation() },
            "lucide_align-right" to { alignRightIconState.startAnimation() },
            "lucide_anchor" to { anchorIconState.startAnimation() },
            "lucide_angry" to { angryIconState.startAnimation() },
            "lucide_annoyed" to { annoyedIconState.startAnimation() },
            "lucide_app-window" to { appWindowIconState.startAnimation() },
            "lucide_archive" to { archiveIconState.startAnimation() },
            "lucide_arrow-big-down-dash" to { arrowBigDownDashIconState.startAnimation() },
            "lucide_arrow-big-down" to { arrowBigDownIconState.startAnimation() },
            "lucide_arrow-big-left-dash" to { arrowBigLeftDashIconState.startAnimation() },
            "lucide_arrow-big-left" to { arrowBigLeftIconState.startAnimation() },
            "lucide_arrow-big-right-dash" to { arrowBigRightDashIconState.startAnimation() },
            "lucide_arrow-big-right" to { arrowBigRightIconState.startAnimation() },
            "lucide_arrow-big-up-dash" to { arrowBigUpDashIconState.startAnimation() },
            "lucide_arrow-big-up" to { arrowBigUpIconState.startAnimation() },
            "lucide_arrow-down-0-1" to { arrowDown01IconState.startAnimation() },
            "lucide_arrow-down-1-0" to { arrowDown10IconState.startAnimation() },
            "lucide_arrow-down-a-z" to { arrowDownAZIconState.startAnimation() },
            "lucide_arrow-down-from-line" to { arrowDownFromLineIconState.startAnimation() },
            "lucide_arrow-down" to { arrowDownIconState.startAnimation() },
            "lucide_arrow-down-left" to { arrowDownLeftIconState.startAnimation() },
            "lucide_arrow-down-narrow-wide" to { arrowDownNarrowWideIconState.startAnimation() },
            "lucide_arrow-down-right" to { arrowDownRightIconState.startAnimation() },
            "lucide_arrow-down-to-dot" to { arrowDownToDotIconState.startAnimation() },
            "lucide_arrow-down-to-line" to { arrowDownToLineIconState.startAnimation() },
            "lucide_arrow-down-up" to { arrowDownUpIconState.startAnimation() },
            "lucide_arrow-down-wide-narrow" to { arrowDownWideNarrowIconState.startAnimation() },
            "lucide_arrow-down-z-a" to { arrowDownZAIconState.startAnimation() },
            "lucide_arrow-left-from-line" to { arrowLeftFromLineIconState.startAnimation() },
            "lucide_arrow-left" to { arrowLeftIconState.startAnimation() },
            "lucide_arrow-left-right" to { arrowLeftRightIconState.startAnimation() },
            "lucide_arrow-left-to-line" to { arrowLeftToLineIconState.startAnimation() },
            "lucide_arrow-right-from-line" to { arrowRightFromLineIconState.startAnimation() },
            "lucide_arrow-right" to { arrowRightIconState.startAnimation() },
            "lucide_arrow-right-left" to { arrowRightLeftIconState.startAnimation() },
            "lucide_arrow-right-to-line" to { arrowRightToLineIconState.startAnimation() },
            "lucide_arrow-up-0-1" to { arrowUp01IconState.startAnimation() },
            "lucide_arrow-up-1-0" to { arrowUp10IconState.startAnimation() },
            "lucide_arrow-up-a-z" to { arrowUpAZIconState.startAnimation() },
            "lucide_arrow-up-down" to { arrowUpDownIconState.startAnimation() },
            "lucide_arrow-up-from-dot" to { arrowUpFromDotIconState.startAnimation() },
            "lucide_arrow-up-from-line" to { arrowUpFromLineIconState.startAnimation() },
            "lucide_arrow-up" to { arrowUpIconState.startAnimation() },
            "lucide_arrow-up-left" to { arrowUpLeftIconState.startAnimation() },
            "lucide_arrow-up-narrow-wide" to { arrowUpNarrowWideIconState.startAnimation() },
            "lucide_arrow-up-right" to { arrowUpRightIconState.startAnimation() },
            "lucide_arrow-up-to-line" to { arrowUpToLineIconState.startAnimation() },
            "lucide_arrow-up-wide-narrow" to { arrowUpWideNarrowIconState.startAnimation() },
            "lucide_arrow-up-z-a" to { arrowUpZAIconState.startAnimation() },
            "lucide_at-sign" to { atSignIconState.startAnimation() },
            "lucide_atom" to { atomIconState.startAnimation() },
            "lucide_audio-lines" to { audioLinesIconState.startAnimation() },
            "lucide_audio-lines-off" to { audioLinesOffIconState.startAnimation() },
            "lucide_audio-lines-x" to { audioLinesXIconState.startAnimation() },
            "lucide_audio-waveform" to { audioWaveformIconState.startAnimation() },
            "lucide_badge-cent" to { badgeCentIconState.startAnimation() },
            "lucide_badge-check" to { badgeCheckIconState.startAnimation() },
            "lucide_badge-dollar-sign" to { badgeDollarSignIconState.startAnimation() },
            "lucide_badge-info" to { badgeInfoIconState.startAnimation() },
            "lucide_badge-percent" to { badgePercentIconState.startAnimation() },
            "lucide_baggage-claim" to { baggageClaimIconState.startAnimation() },
            "lucide_ban" to { banIconState.startAnimation() },
            "lucide_banknote-arrow-down" to { banknoteArrowDownIconState.startAnimation() },
            "lucide_banknote-arrow-up" to { banknoteArrowUpIconState.startAnimation() },
            "lucide_banknote-check" to { banknoteCheckIconState.startAnimation() },
            "lucide_banknote" to { banknoteIconState.startAnimation() },
            "lucide_banknote-x" to { banknoteXIconState.startAnimation() },
            "lucide_barcode" to { barcodeIconState.startAnimation() },
            "lucide_battery-charging" to { batteryChargingIconState.startAnimation() },
            "lucide_battery-full" to { batteryFullIconState.startAnimation() },
            "lucide_battery" to { batteryIconState.startAnimation() },
            "lucide_battery-low" to { batteryLowIconState.startAnimation() },
            "lucide_battery-medium" to { batteryMediumIconState.startAnimation() },
            "lucide_battery-plus" to { batteryPlusIconState.startAnimation() },
            "lucide_battery-warning" to { batteryWarningIconState.startAnimation() },
            "lucide_bell-dot" to { bellDotIconState.startAnimation() },
            "lucide_bell-electric" to { bellElectricIconState.startAnimation() },
            "lucide_bell" to { bellIconState.startAnimation() },
            "lucide_bell-minus" to { bellMinusIconState.startAnimation() },
            "lucide_bell-off" to { bellOffIconState.startAnimation() },
            "lucide_bell-plus" to { bellPlusIconState.startAnimation() },
            "lucide_bell-ring" to { bellRingIconState.startAnimation() },
            "lucide_biceps-flexed" to { bicepsFlexedIconState.startAnimation() },
            "lucide_bike" to { bikeIconState.startAnimation() },
            "lucide_bitcoin" to { bitcoinIconState.startAnimation() },
            "lucide_blend" to { blendIconState.startAnimation() },
            "lucide_blocks" to { blocksIconState.startAnimation() },
            "lucide_bluetooth" to { bluetoothIconState.startAnimation() },
            "lucide_bold" to { boldIconState.startAnimation() },
        ),
        mapOf(
            "lucide_a-arrow-down" to { aArrowDownIconState.stopAnimation() },
            "lucide_a-arrow-up" to { aArrowUpIconState.stopAnimation() },
            "lucide_accessibility" to { accessibilityIconState.stopAnimation() },
            "lucide_activity" to { activityIconState.stopAnimation() },
            "lucide_alarm-clock-check" to { alarmClockCheckIconState.stopAnimation() },
            "lucide_alarm-clock" to { alarmClockIconState.stopAnimation() },
            "lucide_alarm-clock-minus" to { alarmClockMinusIconState.stopAnimation() },
            "lucide_alarm-clock-off" to { alarmClockOffIconState.stopAnimation() },
            "lucide_alarm-clock-plus" to { alarmClockPlusIconState.stopAnimation() },
            "lucide_align-center" to { alignCenterIconState.stopAnimation() },
            "lucide_align-justify" to { alignJustifyIconState.stopAnimation() },
            "lucide_align-left" to { alignLeftIconState.stopAnimation() },
            "lucide_align-right" to { alignRightIconState.stopAnimation() },
            "lucide_anchor" to { anchorIconState.stopAnimation() },
            "lucide_angry" to { angryIconState.stopAnimation() },
            "lucide_annoyed" to { annoyedIconState.stopAnimation() },
            "lucide_app-window" to { appWindowIconState.stopAnimation() },
            "lucide_archive" to { archiveIconState.stopAnimation() },
            "lucide_arrow-big-down-dash" to { arrowBigDownDashIconState.stopAnimation() },
            "lucide_arrow-big-down" to { arrowBigDownIconState.stopAnimation() },
            "lucide_arrow-big-left-dash" to { arrowBigLeftDashIconState.stopAnimation() },
            "lucide_arrow-big-left" to { arrowBigLeftIconState.stopAnimation() },
            "lucide_arrow-big-right-dash" to { arrowBigRightDashIconState.stopAnimation() },
            "lucide_arrow-big-right" to { arrowBigRightIconState.stopAnimation() },
            "lucide_arrow-big-up-dash" to { arrowBigUpDashIconState.stopAnimation() },
            "lucide_arrow-big-up" to { arrowBigUpIconState.stopAnimation() },
            "lucide_arrow-down-0-1" to { arrowDown01IconState.stopAnimation() },
            "lucide_arrow-down-1-0" to { arrowDown10IconState.stopAnimation() },
            "lucide_arrow-down-a-z" to { arrowDownAZIconState.stopAnimation() },
            "lucide_arrow-down-from-line" to { arrowDownFromLineIconState.stopAnimation() },
            "lucide_arrow-down" to { arrowDownIconState.stopAnimation() },
            "lucide_arrow-down-left" to { arrowDownLeftIconState.stopAnimation() },
            "lucide_arrow-down-narrow-wide" to { arrowDownNarrowWideIconState.stopAnimation() },
            "lucide_arrow-down-right" to { arrowDownRightIconState.stopAnimation() },
            "lucide_arrow-down-to-dot" to { arrowDownToDotIconState.stopAnimation() },
            "lucide_arrow-down-to-line" to { arrowDownToLineIconState.stopAnimation() },
            "lucide_arrow-down-up" to { arrowDownUpIconState.stopAnimation() },
            "lucide_arrow-down-wide-narrow" to { arrowDownWideNarrowIconState.stopAnimation() },
            "lucide_arrow-down-z-a" to { arrowDownZAIconState.stopAnimation() },
            "lucide_arrow-left-from-line" to { arrowLeftFromLineIconState.stopAnimation() },
            "lucide_arrow-left" to { arrowLeftIconState.stopAnimation() },
            "lucide_arrow-left-right" to { arrowLeftRightIconState.stopAnimation() },
            "lucide_arrow-left-to-line" to { arrowLeftToLineIconState.stopAnimation() },
            "lucide_arrow-right-from-line" to { arrowRightFromLineIconState.stopAnimation() },
            "lucide_arrow-right" to { arrowRightIconState.stopAnimation() },
            "lucide_arrow-right-left" to { arrowRightLeftIconState.stopAnimation() },
            "lucide_arrow-right-to-line" to { arrowRightToLineIconState.stopAnimation() },
            "lucide_arrow-up-0-1" to { arrowUp01IconState.stopAnimation() },
            "lucide_arrow-up-1-0" to { arrowUp10IconState.stopAnimation() },
            "lucide_arrow-up-a-z" to { arrowUpAZIconState.stopAnimation() },
            "lucide_arrow-up-down" to { arrowUpDownIconState.stopAnimation() },
            "lucide_arrow-up-from-dot" to { arrowUpFromDotIconState.stopAnimation() },
            "lucide_arrow-up-from-line" to { arrowUpFromLineIconState.stopAnimation() },
            "lucide_arrow-up" to { arrowUpIconState.stopAnimation() },
            "lucide_arrow-up-left" to { arrowUpLeftIconState.stopAnimation() },
            "lucide_arrow-up-narrow-wide" to { arrowUpNarrowWideIconState.stopAnimation() },
            "lucide_arrow-up-right" to { arrowUpRightIconState.stopAnimation() },
            "lucide_arrow-up-to-line" to { arrowUpToLineIconState.stopAnimation() },
            "lucide_arrow-up-wide-narrow" to { arrowUpWideNarrowIconState.stopAnimation() },
            "lucide_arrow-up-z-a" to { arrowUpZAIconState.stopAnimation() },
            "lucide_at-sign" to { atSignIconState.stopAnimation() },
            "lucide_atom" to { atomIconState.stopAnimation() },
            "lucide_audio-lines" to { audioLinesIconState.stopAnimation() },
            "lucide_audio-lines-off" to { audioLinesOffIconState.stopAnimation() },
            "lucide_audio-lines-x" to { audioLinesXIconState.stopAnimation() },
            "lucide_audio-waveform" to { audioWaveformIconState.stopAnimation() },
            "lucide_badge-cent" to { badgeCentIconState.stopAnimation() },
            "lucide_badge-check" to { badgeCheckIconState.stopAnimation() },
            "lucide_badge-dollar-sign" to { badgeDollarSignIconState.stopAnimation() },
            "lucide_badge-info" to { badgeInfoIconState.stopAnimation() },
            "lucide_badge-percent" to { badgePercentIconState.stopAnimation() },
            "lucide_baggage-claim" to { baggageClaimIconState.stopAnimation() },
            "lucide_ban" to { banIconState.stopAnimation() },
            "lucide_banknote-arrow-down" to { banknoteArrowDownIconState.stopAnimation() },
            "lucide_banknote-arrow-up" to { banknoteArrowUpIconState.stopAnimation() },
            "lucide_banknote-check" to { banknoteCheckIconState.stopAnimation() },
            "lucide_banknote" to { banknoteIconState.stopAnimation() },
            "lucide_banknote-x" to { banknoteXIconState.stopAnimation() },
            "lucide_barcode" to { barcodeIconState.stopAnimation() },
            "lucide_battery-charging" to { batteryChargingIconState.stopAnimation() },
            "lucide_battery-full" to { batteryFullIconState.stopAnimation() },
            "lucide_battery" to { batteryIconState.stopAnimation() },
            "lucide_battery-low" to { batteryLowIconState.stopAnimation() },
            "lucide_battery-medium" to { batteryMediumIconState.stopAnimation() },
            "lucide_battery-plus" to { batteryPlusIconState.stopAnimation() },
            "lucide_battery-warning" to { batteryWarningIconState.stopAnimation() },
            "lucide_bell-dot" to { bellDotIconState.stopAnimation() },
            "lucide_bell-electric" to { bellElectricIconState.stopAnimation() },
            "lucide_bell" to { bellIconState.stopAnimation() },
            "lucide_bell-minus" to { bellMinusIconState.stopAnimation() },
            "lucide_bell-off" to { bellOffIconState.stopAnimation() },
            "lucide_bell-plus" to { bellPlusIconState.stopAnimation() },
            "lucide_bell-ring" to { bellRingIconState.stopAnimation() },
            "lucide_biceps-flexed" to { bicepsFlexedIconState.stopAnimation() },
            "lucide_bike" to { bikeIconState.stopAnimation() },
            "lucide_bitcoin" to { bitcoinIconState.stopAnimation() },
            "lucide_blend" to { blendIconState.stopAnimation() },
            "lucide_blocks" to { blocksIconState.stopAnimation() },
            "lucide_bluetooth" to { bluetoothIconState.stopAnimation() },
            "lucide_bold" to { boldIconState.stopAnimation() },
        ),
    )
}

@Composable
private fun rememberLucideStatesChunk1(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val boltIconState = rememberBoltIconState()
    val bookOpenCheckIconState = rememberBookOpenCheckIconState()
    val bookOpenIconState = rememberBookOpenIconState()
    val bookOpenTextIconState = rememberBookOpenTextIconState()
    val bookmarkCheckIconState = rememberBookmarkCheckIconState()
    val bookmarkIconState = rememberBookmarkIconState()
    val bookmarkMinusIconState = rememberBookmarkMinusIconState()
    val bookmarkOffIconState = rememberBookmarkOffIconState()
    val bookmarkPlusIconState = rememberBookmarkPlusIconState()
    val bookmarkXIconState = rememberBookmarkXIconState()
    val lucideBotIconState = rememberLucideBotIconState()
    val boxIconState = rememberBoxIconState()
    val boxesIconState = rememberBoxesIconState()
    val brainIconState = rememberBrainIconState()
    val briefcaseIconState = rememberBriefcaseIconState()
    val bringToFrontIconState = rememberBringToFrontIconState()
    val bugIconState = rememberBugIconState()
    val bugOffIconState = rememberBugOffIconState()
    val bugPlayIconState = rememberBugPlayIconState()
    val building2IconState = rememberBuilding2IconState()
    val buildingComplexIconState = rememberBuildingComplexIconState()
    val buildingComplexPlusIconState = rememberBuildingComplexPlusIconState()
    val buildingIconState = rememberBuildingIconState()
    val calculatorIconState = rememberCalculatorIconState()
    val calendarArrowDownIconState = rememberCalendarArrowDownIconState()
    val calendarArrowUpIconState = rememberCalendarArrowUpIconState()
    val calendarCheckIconState = rememberCalendarCheckIconState()
    val calendarChevronsRightIconState = rememberCalendarChevronsRightIconState()
    val calendarClockIconState = rememberCalendarClockIconState()
    val calendarCogIconState = rememberCalendarCogIconState()
    val calendarDaysIconState = rememberCalendarDaysIconState()
    val calendarFoldIconState = rememberCalendarFoldIconState()
    val calendarHeartIconState = rememberCalendarHeartIconState()
    val calendarIconState = rememberCalendarIconState()
    val calendarMinusIconState = rememberCalendarMinusIconState()
    val calendarOffIconState = rememberCalendarOffIconState()
    val calendarPlusIconState = rememberCalendarPlusIconState()
    val calendarRangeIconState = rememberCalendarRangeIconState()
    val calendarSearchIconState = rememberCalendarSearchIconState()
    val calendarSyncIconState = rememberCalendarSyncIconState()
    val calendarXIconState = rememberCalendarXIconState()
    val calendarsIconState = rememberCalendarsIconState()
    val cameraIconState = rememberCameraIconState()
    val cameraOffIconState = rememberCameraOffIconState()
    val captionsIconState = rememberCaptionsIconState()
    val carIconState = rememberCarIconState()
    val caseLowerIconState = rememberCaseLowerIconState()
    val caseSensitiveIconState = rememberCaseSensitiveIconState()
    val caseUpperIconState = rememberCaseUpperIconState()
    val castIconState = rememberCastIconState()
    val catIconState = rememberCatIconState()
    val cctvIconState = rememberCctvIconState()
    val cctvOffIconState = rememberCctvOffIconState()
    val chartAreaIconState = rememberChartAreaIconState()
    val chartBarBigIconState = rememberChartBarBigIconState()
    val chartBarDecreasingIconState = rememberChartBarDecreasingIconState()
    val chartBarIconState = rememberChartBarIconState()
    val chartBarIncreasingIconState = rememberChartBarIncreasingIconState()
    val chartCandlestickIconState = rememberChartCandlestickIconState()
    val chartColumnBigIconState = rememberChartColumnBigIconState()
    val chartColumnDecreasingIconState = rememberChartColumnDecreasingIconState()
    val chartColumnIconState = rememberChartColumnIconState()
    val chartColumnIncreasingIconState = rememberChartColumnIncreasingIconState()
    val chartGanttIconState = rememberChartGanttIconState()
    val chartLineIconState = rememberChartLineIconState()
    val chartNetworkIconState = rememberChartNetworkIconState()
    val chartNoAxesColumnIconState = rememberChartNoAxesColumnIconState()
    val chartNoAxesCombinedIconState = rememberChartNoAxesCombinedIconState()
    val chartPieIconState = rememberChartPieIconState()
    val chartScatterIconState = rememberChartScatterIconState()
    val chartSplineIconState = rememberChartSplineIconState()
    val checkCheckIconState = rememberCheckCheckIconState()
    val checkIconState = rememberCheckIconState()
    val chevronDownIconState = rememberChevronDownIconState()
    val chevronFirstIconState = rememberChevronFirstIconState()
    val chevronLastIconState = rememberChevronLastIconState()
    val chevronLeftIconState = rememberChevronLeftIconState()
    val chevronRightIconState = rememberChevronRightIconState()
    val chevronUpIconState = rememberChevronUpIconState()
    val chevronsDownIconState = rememberChevronsDownIconState()
    val chevronsDownUpIconState = rememberChevronsDownUpIconState()
    val chevronsLeftIconState = rememberChevronsLeftIconState()
    val chevronsLeftRightEllipsisIconState = rememberChevronsLeftRightEllipsisIconState()
    val chevronsLeftRightIconState = rememberChevronsLeftRightIconState()
    val chevronsRightIconState = rememberChevronsRightIconState()
    val chevronsRightLeftIconState = rememberChevronsRightLeftIconState()
    val chevronsUpDownIconState = rememberChevronsUpDownIconState()
    val chevronsUpIconState = rememberChevronsUpIconState()
    val chromeIconState = rememberChromeIconState()
    val circleAlertIconState = rememberCircleAlertIconState()
    val circleArrowDownIconState = rememberCircleArrowDownIconState()
    val circleArrowLeftIconState = rememberCircleArrowLeftIconState()
    val circleArrowOutDownLeftIconState = rememberCircleArrowOutDownLeftIconState()
    val circleArrowOutDownRightIconState = rememberCircleArrowOutDownRightIconState()
    val circleArrowOutUpLeftIconState = rememberCircleArrowOutUpLeftIconState()
    val circleArrowOutUpRightIconState = rememberCircleArrowOutUpRightIconState()
    val circleArrowRightIconState = rememberCircleArrowRightIconState()
    val circleArrowUpIconState = rememberCircleArrowUpIconState()
    val circleCheckBigIconState = rememberCircleCheckBigIconState()
    val circleCheckIconState = rememberCircleCheckIconState()
    return Triple(
        mapOf(
            "lucide_bolt" to boltIconState,
            "lucide_book-open-check" to bookOpenCheckIconState,
            "lucide_book-open" to bookOpenIconState,
            "lucide_book-open-text" to bookOpenTextIconState,
            "lucide_bookmark-check" to bookmarkCheckIconState,
            "lucide_bookmark" to bookmarkIconState,
            "lucide_bookmark-minus" to bookmarkMinusIconState,
            "lucide_bookmark-off" to bookmarkOffIconState,
            "lucide_bookmark-plus" to bookmarkPlusIconState,
            "lucide_bookmark-x" to bookmarkXIconState,
            "lucide_bot" to lucideBotIconState,
            "lucide_box" to boxIconState,
            "lucide_boxes" to boxesIconState,
            "lucide_brain" to brainIconState,
            "lucide_briefcase" to briefcaseIconState,
            "lucide_bring-to-front" to bringToFrontIconState,
            "lucide_bug" to bugIconState,
            "lucide_bug-off" to bugOffIconState,
            "lucide_bug-play" to bugPlayIconState,
            "lucide_building-2" to building2IconState,
            "lucide_building-complex" to buildingComplexIconState,
            "lucide_building-complex-plus" to buildingComplexPlusIconState,
            "lucide_building" to buildingIconState,
            "lucide_calculator" to calculatorIconState,
            "lucide_calendar-arrow-down" to calendarArrowDownIconState,
            "lucide_calendar-arrow-up" to calendarArrowUpIconState,
            "lucide_calendar-check" to calendarCheckIconState,
            "lucide_calendar-chevrons-right" to calendarChevronsRightIconState,
            "lucide_calendar-clock" to calendarClockIconState,
            "lucide_calendar-cog" to calendarCogIconState,
            "lucide_calendar-days" to calendarDaysIconState,
            "lucide_calendar-fold" to calendarFoldIconState,
            "lucide_calendar-heart" to calendarHeartIconState,
            "lucide_calendar" to calendarIconState,
            "lucide_calendar-minus" to calendarMinusIconState,
            "lucide_calendar-off" to calendarOffIconState,
            "lucide_calendar-plus" to calendarPlusIconState,
            "lucide_calendar-range" to calendarRangeIconState,
            "lucide_calendar-search" to calendarSearchIconState,
            "lucide_calendar-sync" to calendarSyncIconState,
            "lucide_calendar-x" to calendarXIconState,
            "lucide_calendars" to calendarsIconState,
            "lucide_camera" to cameraIconState,
            "lucide_camera-off" to cameraOffIconState,
            "lucide_captions" to captionsIconState,
            "lucide_car" to carIconState,
            "lucide_case-lower" to caseLowerIconState,
            "lucide_case-sensitive" to caseSensitiveIconState,
            "lucide_case-upper" to caseUpperIconState,
            "lucide_cast" to castIconState,
            "lucide_cat" to catIconState,
            "lucide_cctv" to cctvIconState,
            "lucide_cctv-off" to cctvOffIconState,
            "lucide_chart-area" to chartAreaIconState,
            "lucide_chart-bar-big" to chartBarBigIconState,
            "lucide_chart-bar-decreasing" to chartBarDecreasingIconState,
            "lucide_chart-bar" to chartBarIconState,
            "lucide_chart-bar-increasing" to chartBarIncreasingIconState,
            "lucide_chart-candlestick" to chartCandlestickIconState,
            "lucide_chart-column-big" to chartColumnBigIconState,
            "lucide_chart-column-decreasing" to chartColumnDecreasingIconState,
            "lucide_chart-column" to chartColumnIconState,
            "lucide_chart-column-increasing" to chartColumnIncreasingIconState,
            "lucide_chart-gantt" to chartGanttIconState,
            "lucide_chart-line" to chartLineIconState,
            "lucide_chart-network" to chartNetworkIconState,
            "lucide_chart-no-axes-column" to chartNoAxesColumnIconState,
            "lucide_chart-no-axes-combined" to chartNoAxesCombinedIconState,
            "lucide_chart-pie" to chartPieIconState,
            "lucide_chart-scatter" to chartScatterIconState,
            "lucide_chart-spline" to chartSplineIconState,
            "lucide_check-check" to checkCheckIconState,
            "lucide_check" to checkIconState,
            "lucide_chevron-down" to chevronDownIconState,
            "lucide_chevron-first" to chevronFirstIconState,
            "lucide_chevron-last" to chevronLastIconState,
            "lucide_chevron-left" to chevronLeftIconState,
            "lucide_chevron-right" to chevronRightIconState,
            "lucide_chevron-up" to chevronUpIconState,
            "lucide_chevrons-down" to chevronsDownIconState,
            "lucide_chevrons-down-up" to chevronsDownUpIconState,
            "lucide_chevrons-left" to chevronsLeftIconState,
            "lucide_chevrons-left-right-ellipsis" to chevronsLeftRightEllipsisIconState,
            "lucide_chevrons-left-right" to chevronsLeftRightIconState,
            "lucide_chevrons-right" to chevronsRightIconState,
            "lucide_chevrons-right-left" to chevronsRightLeftIconState,
            "lucide_chevrons-up-down" to chevronsUpDownIconState,
            "lucide_chevrons-up" to chevronsUpIconState,
            "lucide_chrome" to chromeIconState,
            "lucide_circle-alert" to circleAlertIconState,
            "lucide_circle-arrow-down" to circleArrowDownIconState,
            "lucide_circle-arrow-left" to circleArrowLeftIconState,
            "lucide_circle-arrow-out-down-left" to circleArrowOutDownLeftIconState,
            "lucide_circle-arrow-out-down-right" to circleArrowOutDownRightIconState,
            "lucide_circle-arrow-out-up-left" to circleArrowOutUpLeftIconState,
            "lucide_circle-arrow-out-up-right" to circleArrowOutUpRightIconState,
            "lucide_circle-arrow-right" to circleArrowRightIconState,
            "lucide_circle-arrow-up" to circleArrowUpIconState,
            "lucide_circle-check-big" to circleCheckBigIconState,
            "lucide_circle-check" to circleCheckIconState,
        ),
        mapOf(
            "lucide_bolt" to { boltIconState.startAnimation() },
            "lucide_book-open-check" to { bookOpenCheckIconState.startAnimation() },
            "lucide_book-open" to { bookOpenIconState.startAnimation() },
            "lucide_book-open-text" to { bookOpenTextIconState.startAnimation() },
            "lucide_bookmark-check" to { bookmarkCheckIconState.startAnimation() },
            "lucide_bookmark" to { bookmarkIconState.startAnimation() },
            "lucide_bookmark-minus" to { bookmarkMinusIconState.startAnimation() },
            "lucide_bookmark-off" to { bookmarkOffIconState.startAnimation() },
            "lucide_bookmark-plus" to { bookmarkPlusIconState.startAnimation() },
            "lucide_bookmark-x" to { bookmarkXIconState.startAnimation() },
            "lucide_bot" to { lucideBotIconState.startAnimation() },
            "lucide_box" to { boxIconState.startAnimation() },
            "lucide_boxes" to { boxesIconState.startAnimation() },
            "lucide_brain" to { brainIconState.startAnimation() },
            "lucide_briefcase" to { briefcaseIconState.startAnimation() },
            "lucide_bring-to-front" to { bringToFrontIconState.startAnimation() },
            "lucide_bug" to { bugIconState.startAnimation() },
            "lucide_bug-off" to { bugOffIconState.startAnimation() },
            "lucide_bug-play" to { bugPlayIconState.startAnimation() },
            "lucide_building-2" to { building2IconState.startAnimation() },
            "lucide_building-complex" to { buildingComplexIconState.startAnimation() },
            "lucide_building-complex-plus" to { buildingComplexPlusIconState.startAnimation() },
            "lucide_building" to { buildingIconState.startAnimation() },
            "lucide_calculator" to { calculatorIconState.startAnimation() },
            "lucide_calendar-arrow-down" to { calendarArrowDownIconState.startAnimation() },
            "lucide_calendar-arrow-up" to { calendarArrowUpIconState.startAnimation() },
            "lucide_calendar-check" to { calendarCheckIconState.startAnimation() },
            "lucide_calendar-chevrons-right" to { calendarChevronsRightIconState.startAnimation() },
            "lucide_calendar-clock" to { calendarClockIconState.startAnimation() },
            "lucide_calendar-cog" to { calendarCogIconState.startAnimation() },
            "lucide_calendar-days" to { calendarDaysIconState.startAnimation() },
            "lucide_calendar-fold" to { calendarFoldIconState.startAnimation() },
            "lucide_calendar-heart" to { calendarHeartIconState.startAnimation() },
            "lucide_calendar" to { calendarIconState.startAnimation() },
            "lucide_calendar-minus" to { calendarMinusIconState.startAnimation() },
            "lucide_calendar-off" to { calendarOffIconState.startAnimation() },
            "lucide_calendar-plus" to { calendarPlusIconState.startAnimation() },
            "lucide_calendar-range" to { calendarRangeIconState.startAnimation() },
            "lucide_calendar-search" to { calendarSearchIconState.startAnimation() },
            "lucide_calendar-sync" to { calendarSyncIconState.startAnimation() },
            "lucide_calendar-x" to { calendarXIconState.startAnimation() },
            "lucide_calendars" to { calendarsIconState.startAnimation() },
            "lucide_camera" to { cameraIconState.startAnimation() },
            "lucide_camera-off" to { cameraOffIconState.startAnimation() },
            "lucide_captions" to { captionsIconState.startAnimation() },
            "lucide_car" to { carIconState.startAnimation() },
            "lucide_case-lower" to { caseLowerIconState.startAnimation() },
            "lucide_case-sensitive" to { caseSensitiveIconState.startAnimation() },
            "lucide_case-upper" to { caseUpperIconState.startAnimation() },
            "lucide_cast" to { castIconState.startAnimation() },
            "lucide_cat" to { catIconState.startAnimation() },
            "lucide_cctv" to { cctvIconState.startAnimation() },
            "lucide_cctv-off" to { cctvOffIconState.startAnimation() },
            "lucide_chart-area" to { chartAreaIconState.startAnimation() },
            "lucide_chart-bar-big" to { chartBarBigIconState.startAnimation() },
            "lucide_chart-bar-decreasing" to { chartBarDecreasingIconState.startAnimation() },
            "lucide_chart-bar" to { chartBarIconState.startAnimation() },
            "lucide_chart-bar-increasing" to { chartBarIncreasingIconState.startAnimation() },
            "lucide_chart-candlestick" to { chartCandlestickIconState.startAnimation() },
            "lucide_chart-column-big" to { chartColumnBigIconState.startAnimation() },
            "lucide_chart-column-decreasing" to { chartColumnDecreasingIconState.startAnimation() },
            "lucide_chart-column" to { chartColumnIconState.startAnimation() },
            "lucide_chart-column-increasing" to { chartColumnIncreasingIconState.startAnimation() },
            "lucide_chart-gantt" to { chartGanttIconState.startAnimation() },
            "lucide_chart-line" to { chartLineIconState.startAnimation() },
            "lucide_chart-network" to { chartNetworkIconState.startAnimation() },
            "lucide_chart-no-axes-column" to { chartNoAxesColumnIconState.startAnimation() },
            "lucide_chart-no-axes-combined" to { chartNoAxesCombinedIconState.startAnimation() },
            "lucide_chart-pie" to { chartPieIconState.startAnimation() },
            "lucide_chart-scatter" to { chartScatterIconState.startAnimation() },
            "lucide_chart-spline" to { chartSplineIconState.startAnimation() },
            "lucide_check-check" to { checkCheckIconState.startAnimation() },
            "lucide_check" to { checkIconState.startAnimation() },
            "lucide_chevron-down" to { chevronDownIconState.startAnimation() },
            "lucide_chevron-first" to { chevronFirstIconState.startAnimation() },
            "lucide_chevron-last" to { chevronLastIconState.startAnimation() },
            "lucide_chevron-left" to { chevronLeftIconState.startAnimation() },
            "lucide_chevron-right" to { chevronRightIconState.startAnimation() },
            "lucide_chevron-up" to { chevronUpIconState.startAnimation() },
            "lucide_chevrons-down" to { chevronsDownIconState.startAnimation() },
            "lucide_chevrons-down-up" to { chevronsDownUpIconState.startAnimation() },
            "lucide_chevrons-left" to { chevronsLeftIconState.startAnimation() },
            "lucide_chevrons-left-right-ellipsis" to { chevronsLeftRightEllipsisIconState.startAnimation() },
            "lucide_chevrons-left-right" to { chevronsLeftRightIconState.startAnimation() },
            "lucide_chevrons-right" to { chevronsRightIconState.startAnimation() },
            "lucide_chevrons-right-left" to { chevronsRightLeftIconState.startAnimation() },
            "lucide_chevrons-up-down" to { chevronsUpDownIconState.startAnimation() },
            "lucide_chevrons-up" to { chevronsUpIconState.startAnimation() },
            "lucide_chrome" to { chromeIconState.startAnimation() },
            "lucide_circle-alert" to { circleAlertIconState.startAnimation() },
            "lucide_circle-arrow-down" to { circleArrowDownIconState.startAnimation() },
            "lucide_circle-arrow-left" to { circleArrowLeftIconState.startAnimation() },
            "lucide_circle-arrow-out-down-left" to { circleArrowOutDownLeftIconState.startAnimation() },
            "lucide_circle-arrow-out-down-right" to { circleArrowOutDownRightIconState.startAnimation() },
            "lucide_circle-arrow-out-up-left" to { circleArrowOutUpLeftIconState.startAnimation() },
            "lucide_circle-arrow-out-up-right" to { circleArrowOutUpRightIconState.startAnimation() },
            "lucide_circle-arrow-right" to { circleArrowRightIconState.startAnimation() },
            "lucide_circle-arrow-up" to { circleArrowUpIconState.startAnimation() },
            "lucide_circle-check-big" to { circleCheckBigIconState.startAnimation() },
            "lucide_circle-check" to { circleCheckIconState.startAnimation() },
        ),
        mapOf(
            "lucide_bolt" to { boltIconState.stopAnimation() },
            "lucide_book-open-check" to { bookOpenCheckIconState.stopAnimation() },
            "lucide_book-open" to { bookOpenIconState.stopAnimation() },
            "lucide_book-open-text" to { bookOpenTextIconState.stopAnimation() },
            "lucide_bookmark-check" to { bookmarkCheckIconState.stopAnimation() },
            "lucide_bookmark" to { bookmarkIconState.stopAnimation() },
            "lucide_bookmark-minus" to { bookmarkMinusIconState.stopAnimation() },
            "lucide_bookmark-off" to { bookmarkOffIconState.stopAnimation() },
            "lucide_bookmark-plus" to { bookmarkPlusIconState.stopAnimation() },
            "lucide_bookmark-x" to { bookmarkXIconState.stopAnimation() },
            "lucide_bot" to { lucideBotIconState.stopAnimation() },
            "lucide_box" to { boxIconState.stopAnimation() },
            "lucide_boxes" to { boxesIconState.stopAnimation() },
            "lucide_brain" to { brainIconState.stopAnimation() },
            "lucide_briefcase" to { briefcaseIconState.stopAnimation() },
            "lucide_bring-to-front" to { bringToFrontIconState.stopAnimation() },
            "lucide_bug" to { bugIconState.stopAnimation() },
            "lucide_bug-off" to { bugOffIconState.stopAnimation() },
            "lucide_bug-play" to { bugPlayIconState.stopAnimation() },
            "lucide_building-2" to { building2IconState.stopAnimation() },
            "lucide_building-complex" to { buildingComplexIconState.stopAnimation() },
            "lucide_building-complex-plus" to { buildingComplexPlusIconState.stopAnimation() },
            "lucide_building" to { buildingIconState.stopAnimation() },
            "lucide_calculator" to { calculatorIconState.stopAnimation() },
            "lucide_calendar-arrow-down" to { calendarArrowDownIconState.stopAnimation() },
            "lucide_calendar-arrow-up" to { calendarArrowUpIconState.stopAnimation() },
            "lucide_calendar-check" to { calendarCheckIconState.stopAnimation() },
            "lucide_calendar-chevrons-right" to { calendarChevronsRightIconState.stopAnimation() },
            "lucide_calendar-clock" to { calendarClockIconState.stopAnimation() },
            "lucide_calendar-cog" to { calendarCogIconState.stopAnimation() },
            "lucide_calendar-days" to { calendarDaysIconState.stopAnimation() },
            "lucide_calendar-fold" to { calendarFoldIconState.stopAnimation() },
            "lucide_calendar-heart" to { calendarHeartIconState.stopAnimation() },
            "lucide_calendar" to { calendarIconState.stopAnimation() },
            "lucide_calendar-minus" to { calendarMinusIconState.stopAnimation() },
            "lucide_calendar-off" to { calendarOffIconState.stopAnimation() },
            "lucide_calendar-plus" to { calendarPlusIconState.stopAnimation() },
            "lucide_calendar-range" to { calendarRangeIconState.stopAnimation() },
            "lucide_calendar-search" to { calendarSearchIconState.stopAnimation() },
            "lucide_calendar-sync" to { calendarSyncIconState.stopAnimation() },
            "lucide_calendar-x" to { calendarXIconState.stopAnimation() },
            "lucide_calendars" to { calendarsIconState.stopAnimation() },
            "lucide_camera" to { cameraIconState.stopAnimation() },
            "lucide_camera-off" to { cameraOffIconState.stopAnimation() },
            "lucide_captions" to { captionsIconState.stopAnimation() },
            "lucide_car" to { carIconState.stopAnimation() },
            "lucide_case-lower" to { caseLowerIconState.stopAnimation() },
            "lucide_case-sensitive" to { caseSensitiveIconState.stopAnimation() },
            "lucide_case-upper" to { caseUpperIconState.stopAnimation() },
            "lucide_cast" to { castIconState.stopAnimation() },
            "lucide_cat" to { catIconState.stopAnimation() },
            "lucide_cctv" to { cctvIconState.stopAnimation() },
            "lucide_cctv-off" to { cctvOffIconState.stopAnimation() },
            "lucide_chart-area" to { chartAreaIconState.stopAnimation() },
            "lucide_chart-bar-big" to { chartBarBigIconState.stopAnimation() },
            "lucide_chart-bar-decreasing" to { chartBarDecreasingIconState.stopAnimation() },
            "lucide_chart-bar" to { chartBarIconState.stopAnimation() },
            "lucide_chart-bar-increasing" to { chartBarIncreasingIconState.stopAnimation() },
            "lucide_chart-candlestick" to { chartCandlestickIconState.stopAnimation() },
            "lucide_chart-column-big" to { chartColumnBigIconState.stopAnimation() },
            "lucide_chart-column-decreasing" to { chartColumnDecreasingIconState.stopAnimation() },
            "lucide_chart-column" to { chartColumnIconState.stopAnimation() },
            "lucide_chart-column-increasing" to { chartColumnIncreasingIconState.stopAnimation() },
            "lucide_chart-gantt" to { chartGanttIconState.stopAnimation() },
            "lucide_chart-line" to { chartLineIconState.stopAnimation() },
            "lucide_chart-network" to { chartNetworkIconState.stopAnimation() },
            "lucide_chart-no-axes-column" to { chartNoAxesColumnIconState.stopAnimation() },
            "lucide_chart-no-axes-combined" to { chartNoAxesCombinedIconState.stopAnimation() },
            "lucide_chart-pie" to { chartPieIconState.stopAnimation() },
            "lucide_chart-scatter" to { chartScatterIconState.stopAnimation() },
            "lucide_chart-spline" to { chartSplineIconState.stopAnimation() },
            "lucide_check-check" to { checkCheckIconState.stopAnimation() },
            "lucide_check" to { checkIconState.stopAnimation() },
            "lucide_chevron-down" to { chevronDownIconState.stopAnimation() },
            "lucide_chevron-first" to { chevronFirstIconState.stopAnimation() },
            "lucide_chevron-last" to { chevronLastIconState.stopAnimation() },
            "lucide_chevron-left" to { chevronLeftIconState.stopAnimation() },
            "lucide_chevron-right" to { chevronRightIconState.stopAnimation() },
            "lucide_chevron-up" to { chevronUpIconState.stopAnimation() },
            "lucide_chevrons-down" to { chevronsDownIconState.stopAnimation() },
            "lucide_chevrons-down-up" to { chevronsDownUpIconState.stopAnimation() },
            "lucide_chevrons-left" to { chevronsLeftIconState.stopAnimation() },
            "lucide_chevrons-left-right-ellipsis" to { chevronsLeftRightEllipsisIconState.stopAnimation() },
            "lucide_chevrons-left-right" to { chevronsLeftRightIconState.stopAnimation() },
            "lucide_chevrons-right" to { chevronsRightIconState.stopAnimation() },
            "lucide_chevrons-right-left" to { chevronsRightLeftIconState.stopAnimation() },
            "lucide_chevrons-up-down" to { chevronsUpDownIconState.stopAnimation() },
            "lucide_chevrons-up" to { chevronsUpIconState.stopAnimation() },
            "lucide_chrome" to { chromeIconState.stopAnimation() },
            "lucide_circle-alert" to { circleAlertIconState.stopAnimation() },
            "lucide_circle-arrow-down" to { circleArrowDownIconState.stopAnimation() },
            "lucide_circle-arrow-left" to { circleArrowLeftIconState.stopAnimation() },
            "lucide_circle-arrow-out-down-left" to { circleArrowOutDownLeftIconState.stopAnimation() },
            "lucide_circle-arrow-out-down-right" to { circleArrowOutDownRightIconState.stopAnimation() },
            "lucide_circle-arrow-out-up-left" to { circleArrowOutUpLeftIconState.stopAnimation() },
            "lucide_circle-arrow-out-up-right" to { circleArrowOutUpRightIconState.stopAnimation() },
            "lucide_circle-arrow-right" to { circleArrowRightIconState.stopAnimation() },
            "lucide_circle-arrow-up" to { circleArrowUpIconState.stopAnimation() },
            "lucide_circle-check-big" to { circleCheckBigIconState.stopAnimation() },
            "lucide_circle-check" to { circleCheckIconState.stopAnimation() },
        ),
    )
}

@Composable
private fun rememberLucideStatesChunk2(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val circleChevronDownIconState = rememberCircleChevronDownIconState()
    val circleChevronLeftIconState = rememberCircleChevronLeftIconState()
    val circleChevronRightIconState = rememberCircleChevronRightIconState()
    val circleChevronUpIconState = rememberCircleChevronUpIconState()
    val circleDashedIconState = rememberCircleDashedIconState()
    val circleDollarSignIconState = rememberCircleDollarSignIconState()
    val circleDotIconState = rememberCircleDotIconState()
    val circleEllipsisIconState = rememberCircleEllipsisIconState()
    val circleEuroIconState = rememberCircleEuroIconState()
    val circleIconState = rememberCircleIconState()
    val circleMinusIconState = rememberCircleMinusIconState()
    val circleOffIconState = rememberCircleOffIconState()
    val circlePauseIconState = rememberCirclePauseIconState()
    val circlePercentIconState = rememberCirclePercentIconState()
    val circlePlayIconState = rememberCirclePlayIconState()
    val circlePlusIconState = rememberCirclePlusIconState()
    val circlePoundSterlingIconState = rememberCirclePoundSterlingIconState()
    val circlePowerIconState = rememberCirclePowerIconState()
    val circleQuestionMarkIconState = rememberCircleQuestionMarkIconState()
    val circleSlash2IconState = rememberCircleSlash2IconState()
    val circleSlashIconState = rememberCircleSlashIconState()
    val circleStarIconState = rememberCircleStarIconState()
    val circleStopIconState = rememberCircleStopIconState()
    val circleUserIconState = rememberCircleUserIconState()
    val circleUserRoundIconState = rememberCircleUserRoundIconState()
    val circleXIconState = rememberCircleXIconState()
    val clapperboardIconState = rememberClapperboardIconState()
    val clipboardCheckIconState = rememberClipboardCheckIconState()
    val clipboardClockIconState = rememberClipboardClockIconState()
    val clipboardIconState = rememberClipboardIconState()
    val clipboardListIconState = rememberClipboardListIconState()
    val clipboardPasteIconState = rememberClipboardPasteIconState()
    val clockAlertIconState = rememberClockAlertIconState()
    val clockArrowDownIconState = rememberClockArrowDownIconState()
    val clockArrowLeftIconState = rememberClockArrowLeftIconState()
    val clockArrowRightIconState = rememberClockArrowRightIconState()
    val clockArrowUpIconState = rememberClockArrowUpIconState()
    val clockCheckIconState = rememberClockCheckIconState()
    val clockFadingIconState = rememberClockFadingIconState()
    val clockIconState = rememberClockIconState()
    val clockPlusIconState = rememberClockPlusIconState()
    val cloudCheckIconState = rememberCloudCheckIconState()
    val cloudDownloadIconState = rememberCloudDownloadIconState()
    val cloudDrizzleIconState = rememberCloudDrizzleIconState()
    val cloudFogIconState = rememberCloudFogIconState()
    val cloudHailIconState = rememberCloudHailIconState()
    val cloudIconState = rememberCloudIconState()
    val cloudLightningIconState = rememberCloudLightningIconState()
    val cloudMoonIconState = rememberCloudMoonIconState()
    val cloudMoonRainIconState = rememberCloudMoonRainIconState()
    val cloudOffIconState = rememberCloudOffIconState()
    val cloudRainIconState = rememberCloudRainIconState()
    val cloudRainWindIconState = rememberCloudRainWindIconState()
    val cloudSnowIconState = rememberCloudSnowIconState()
    val cloudSunIconState = rememberCloudSunIconState()
    val cloudSunRainIconState = rememberCloudSunRainIconState()
    val lucideCloudSyncIconState = rememberLucideCloudSyncIconState()
    val cloudUploadIconState = rememberCloudUploadIconState()
    val cloudyIconState = rememberCloudyIconState()
    val codeIconState = rememberCodeIconState()
    val codeXmlIconState = rememberCodeXmlIconState()
    val coffeeIconState = rememberCoffeeIconState()
    val coinsIconState = rememberCoinsIconState()
    val columns2IconState = rememberColumns2IconState()
    val columns3IconState = rememberColumns3IconState()
    val columns4IconState = rememberColumns4IconState()
    val commandIconState = rememberCommandIconState()
    val compassIconState = rememberCompassIconState()
    val componentIconState = rememberComponentIconState()
    val contactIconState = rememberContactIconState()
    val contactRoundIconState = rememberContactRoundIconState()
    val contrastIconState = rememberContrastIconState()
    val cookieIconState = rememberCookieIconState()
    val copyCheckIconState = rememberCopyCheckIconState()
    val copyIconState = rememberCopyIconState()
    val copyPlusIconState = rememberCopyPlusIconState()
    val cornerDownLeftIconState = rememberCornerDownLeftIconState()
    val cornerDownRightIconState = rememberCornerDownRightIconState()
    val cornerLeftDownIconState = rememberCornerLeftDownIconState()
    val cornerLeftUpIconState = rememberCornerLeftUpIconState()
    val cornerRightDownIconState = rememberCornerRightDownIconState()
    val cornerRightUpIconState = rememberCornerRightUpIconState()
    val cornerUpLeftIconState = rememberCornerUpLeftIconState()
    val cornerUpRightIconState = rememberCornerUpRightIconState()
    val cpuIconState = rememberCpuIconState()
    val creditCardCheckIconState = rememberCreditCardCheckIconState()
    val creditCardIconState = rememberCreditCardIconState()
    val creditCardMinusIconState = rememberCreditCardMinusIconState()
    val creditCardPlusIconState = rememberCreditCardPlusIconState()
    val creditCardReaderIconState = rememberCreditCardReaderIconState()
    val creditCardXIconState = rememberCreditCardXIconState()
    val cropIconState = rememberCropIconState()
    val crownIconState = rememberCrownIconState()
    val databaseArrowDownIconState = rememberDatabaseArrowDownIconState()
    val databaseArrowUpIconState = rememberDatabaseArrowUpIconState()
    val databaseBackupIconState = rememberDatabaseBackupIconState()
    val databaseCheckIconState = rememberDatabaseCheckIconState()
    val databaseIconState = rememberDatabaseIconState()
    val databaseMinusIconState = rememberDatabaseMinusIconState()
    val databasePlusIconState = rememberDatabasePlusIconState()
    return Triple(
        mapOf(
            "lucide_circle-chevron-down" to circleChevronDownIconState,
            "lucide_circle-chevron-left" to circleChevronLeftIconState,
            "lucide_circle-chevron-right" to circleChevronRightIconState,
            "lucide_circle-chevron-up" to circleChevronUpIconState,
            "lucide_circle-dashed" to circleDashedIconState,
            "lucide_circle-dollar-sign" to circleDollarSignIconState,
            "lucide_circle-dot" to circleDotIconState,
            "lucide_circle-ellipsis" to circleEllipsisIconState,
            "lucide_circle-euro" to circleEuroIconState,
            "lucide_circle" to circleIconState,
            "lucide_circle-minus" to circleMinusIconState,
            "lucide_circle-off" to circleOffIconState,
            "lucide_circle-pause" to circlePauseIconState,
            "lucide_circle-percent" to circlePercentIconState,
            "lucide_circle-play" to circlePlayIconState,
            "lucide_circle-plus" to circlePlusIconState,
            "lucide_circle-pound-sterling" to circlePoundSterlingIconState,
            "lucide_circle-power" to circlePowerIconState,
            "lucide_circle-question-mark" to circleQuestionMarkIconState,
            "lucide_circle-slash-2" to circleSlash2IconState,
            "lucide_circle-slash" to circleSlashIconState,
            "lucide_circle-star" to circleStarIconState,
            "lucide_circle-stop" to circleStopIconState,
            "lucide_circle-user" to circleUserIconState,
            "lucide_circle-user-round" to circleUserRoundIconState,
            "lucide_circle-x" to circleXIconState,
            "lucide_clapperboard" to clapperboardIconState,
            "lucide_clipboard-check" to clipboardCheckIconState,
            "lucide_clipboard-clock" to clipboardClockIconState,
            "lucide_clipboard" to clipboardIconState,
            "lucide_clipboard-list" to clipboardListIconState,
            "lucide_clipboard-paste" to clipboardPasteIconState,
            "lucide_clock-alert" to clockAlertIconState,
            "lucide_clock-arrow-down" to clockArrowDownIconState,
            "lucide_clock-arrow-left" to clockArrowLeftIconState,
            "lucide_clock-arrow-right" to clockArrowRightIconState,
            "lucide_clock-arrow-up" to clockArrowUpIconState,
            "lucide_clock-check" to clockCheckIconState,
            "lucide_clock-fading" to clockFadingIconState,
            "lucide_clock" to clockIconState,
            "lucide_clock-plus" to clockPlusIconState,
            "lucide_cloud-check" to cloudCheckIconState,
            "lucide_cloud-download" to cloudDownloadIconState,
            "lucide_cloud-drizzle" to cloudDrizzleIconState,
            "lucide_cloud-fog" to cloudFogIconState,
            "lucide_cloud-hail" to cloudHailIconState,
            "lucide_cloud" to cloudIconState,
            "lucide_cloud-lightning" to cloudLightningIconState,
            "lucide_cloud-moon" to cloudMoonIconState,
            "lucide_cloud-moon-rain" to cloudMoonRainIconState,
            "lucide_cloud-off" to cloudOffIconState,
            "lucide_cloud-rain" to cloudRainIconState,
            "lucide_cloud-rain-wind" to cloudRainWindIconState,
            "lucide_cloud-snow" to cloudSnowIconState,
            "lucide_cloud-sun" to cloudSunIconState,
            "lucide_cloud-sun-rain" to cloudSunRainIconState,
            "lucide_cloud-sync" to lucideCloudSyncIconState,
            "lucide_cloud-upload" to cloudUploadIconState,
            "lucide_cloudy" to cloudyIconState,
            "lucide_code" to codeIconState,
            "lucide_code-xml" to codeXmlIconState,
            "lucide_coffee" to coffeeIconState,
            "lucide_coins" to coinsIconState,
            "lucide_columns-2" to columns2IconState,
            "lucide_columns-3" to columns3IconState,
            "lucide_columns-4" to columns4IconState,
            "lucide_command" to commandIconState,
            "lucide_compass" to compassIconState,
            "lucide_component" to componentIconState,
            "lucide_contact" to contactIconState,
            "lucide_contact-round" to contactRoundIconState,
            "lucide_contrast" to contrastIconState,
            "lucide_cookie" to cookieIconState,
            "lucide_copy-check" to copyCheckIconState,
            "lucide_copy" to copyIconState,
            "lucide_copy-plus" to copyPlusIconState,
            "lucide_corner-down-left" to cornerDownLeftIconState,
            "lucide_corner-down-right" to cornerDownRightIconState,
            "lucide_corner-left-down" to cornerLeftDownIconState,
            "lucide_corner-left-up" to cornerLeftUpIconState,
            "lucide_corner-right-down" to cornerRightDownIconState,
            "lucide_corner-right-up" to cornerRightUpIconState,
            "lucide_corner-up-left" to cornerUpLeftIconState,
            "lucide_corner-up-right" to cornerUpRightIconState,
            "lucide_cpu" to cpuIconState,
            "lucide_credit-card-check" to creditCardCheckIconState,
            "lucide_credit-card" to creditCardIconState,
            "lucide_credit-card-minus" to creditCardMinusIconState,
            "lucide_credit-card-plus" to creditCardPlusIconState,
            "lucide_credit-card-reader" to creditCardReaderIconState,
            "lucide_credit-card-x" to creditCardXIconState,
            "lucide_crop" to cropIconState,
            "lucide_crown" to crownIconState,
            "lucide_database-arrow-down" to databaseArrowDownIconState,
            "lucide_database-arrow-up" to databaseArrowUpIconState,
            "lucide_database-backup" to databaseBackupIconState,
            "lucide_database-check" to databaseCheckIconState,
            "lucide_database" to databaseIconState,
            "lucide_database-minus" to databaseMinusIconState,
            "lucide_database-plus" to databasePlusIconState,
        ),
        mapOf(
            "lucide_circle-chevron-down" to { circleChevronDownIconState.startAnimation() },
            "lucide_circle-chevron-left" to { circleChevronLeftIconState.startAnimation() },
            "lucide_circle-chevron-right" to { circleChevronRightIconState.startAnimation() },
            "lucide_circle-chevron-up" to { circleChevronUpIconState.startAnimation() },
            "lucide_circle-dashed" to { circleDashedIconState.startAnimation() },
            "lucide_circle-dollar-sign" to { circleDollarSignIconState.startAnimation() },
            "lucide_circle-dot" to { circleDotIconState.startAnimation() },
            "lucide_circle-ellipsis" to { circleEllipsisIconState.startAnimation() },
            "lucide_circle-euro" to { circleEuroIconState.startAnimation() },
            "lucide_circle" to { circleIconState.startAnimation() },
            "lucide_circle-minus" to { circleMinusIconState.startAnimation() },
            "lucide_circle-off" to { circleOffIconState.startAnimation() },
            "lucide_circle-pause" to { circlePauseIconState.startAnimation() },
            "lucide_circle-percent" to { circlePercentIconState.startAnimation() },
            "lucide_circle-play" to { circlePlayIconState.startAnimation() },
            "lucide_circle-plus" to { circlePlusIconState.startAnimation() },
            "lucide_circle-pound-sterling" to { circlePoundSterlingIconState.startAnimation() },
            "lucide_circle-power" to { circlePowerIconState.startAnimation() },
            "lucide_circle-question-mark" to { circleQuestionMarkIconState.startAnimation() },
            "lucide_circle-slash-2" to { circleSlash2IconState.startAnimation() },
            "lucide_circle-slash" to { circleSlashIconState.startAnimation() },
            "lucide_circle-star" to { circleStarIconState.startAnimation() },
            "lucide_circle-stop" to { circleStopIconState.startAnimation() },
            "lucide_circle-user" to { circleUserIconState.startAnimation() },
            "lucide_circle-user-round" to { circleUserRoundIconState.startAnimation() },
            "lucide_circle-x" to { circleXIconState.startAnimation() },
            "lucide_clapperboard" to { clapperboardIconState.startAnimation() },
            "lucide_clipboard-check" to { clipboardCheckIconState.startAnimation() },
            "lucide_clipboard-clock" to { clipboardClockIconState.startAnimation() },
            "lucide_clipboard" to { clipboardIconState.startAnimation() },
            "lucide_clipboard-list" to { clipboardListIconState.startAnimation() },
            "lucide_clipboard-paste" to { clipboardPasteIconState.startAnimation() },
            "lucide_clock-alert" to { clockAlertIconState.startAnimation() },
            "lucide_clock-arrow-down" to { clockArrowDownIconState.startAnimation() },
            "lucide_clock-arrow-left" to { clockArrowLeftIconState.startAnimation() },
            "lucide_clock-arrow-right" to { clockArrowRightIconState.startAnimation() },
            "lucide_clock-arrow-up" to { clockArrowUpIconState.startAnimation() },
            "lucide_clock-check" to { clockCheckIconState.startAnimation() },
            "lucide_clock-fading" to { clockFadingIconState.startAnimation() },
            "lucide_clock" to { clockIconState.startAnimation() },
            "lucide_clock-plus" to { clockPlusIconState.startAnimation() },
            "lucide_cloud-check" to { cloudCheckIconState.startAnimation() },
            "lucide_cloud-download" to { cloudDownloadIconState.startAnimation() },
            "lucide_cloud-drizzle" to { cloudDrizzleIconState.startAnimation() },
            "lucide_cloud-fog" to { cloudFogIconState.startAnimation() },
            "lucide_cloud-hail" to { cloudHailIconState.startAnimation() },
            "lucide_cloud" to { cloudIconState.startAnimation() },
            "lucide_cloud-lightning" to { cloudLightningIconState.startAnimation() },
            "lucide_cloud-moon" to { cloudMoonIconState.startAnimation() },
            "lucide_cloud-moon-rain" to { cloudMoonRainIconState.startAnimation() },
            "lucide_cloud-off" to { cloudOffIconState.startAnimation() },
            "lucide_cloud-rain" to { cloudRainIconState.startAnimation() },
            "lucide_cloud-rain-wind" to { cloudRainWindIconState.startAnimation() },
            "lucide_cloud-snow" to { cloudSnowIconState.startAnimation() },
            "lucide_cloud-sun" to { cloudSunIconState.startAnimation() },
            "lucide_cloud-sun-rain" to { cloudSunRainIconState.startAnimation() },
            "lucide_cloud-sync" to { lucideCloudSyncIconState.startAnimation() },
            "lucide_cloud-upload" to { cloudUploadIconState.startAnimation() },
            "lucide_cloudy" to { cloudyIconState.startAnimation() },
            "lucide_code" to { codeIconState.startAnimation() },
            "lucide_code-xml" to { codeXmlIconState.startAnimation() },
            "lucide_coffee" to { coffeeIconState.startAnimation() },
            "lucide_coins" to { coinsIconState.startAnimation() },
            "lucide_columns-2" to { columns2IconState.startAnimation() },
            "lucide_columns-3" to { columns3IconState.startAnimation() },
            "lucide_columns-4" to { columns4IconState.startAnimation() },
            "lucide_command" to { commandIconState.startAnimation() },
            "lucide_compass" to { compassIconState.startAnimation() },
            "lucide_component" to { componentIconState.startAnimation() },
            "lucide_contact" to { contactIconState.startAnimation() },
            "lucide_contact-round" to { contactRoundIconState.startAnimation() },
            "lucide_contrast" to { contrastIconState.startAnimation() },
            "lucide_cookie" to { cookieIconState.startAnimation() },
            "lucide_copy-check" to { copyCheckIconState.startAnimation() },
            "lucide_copy" to { copyIconState.startAnimation() },
            "lucide_copy-plus" to { copyPlusIconState.startAnimation() },
            "lucide_corner-down-left" to { cornerDownLeftIconState.startAnimation() },
            "lucide_corner-down-right" to { cornerDownRightIconState.startAnimation() },
            "lucide_corner-left-down" to { cornerLeftDownIconState.startAnimation() },
            "lucide_corner-left-up" to { cornerLeftUpIconState.startAnimation() },
            "lucide_corner-right-down" to { cornerRightDownIconState.startAnimation() },
            "lucide_corner-right-up" to { cornerRightUpIconState.startAnimation() },
            "lucide_corner-up-left" to { cornerUpLeftIconState.startAnimation() },
            "lucide_corner-up-right" to { cornerUpRightIconState.startAnimation() },
            "lucide_cpu" to { cpuIconState.startAnimation() },
            "lucide_credit-card-check" to { creditCardCheckIconState.startAnimation() },
            "lucide_credit-card" to { creditCardIconState.startAnimation() },
            "lucide_credit-card-minus" to { creditCardMinusIconState.startAnimation() },
            "lucide_credit-card-plus" to { creditCardPlusIconState.startAnimation() },
            "lucide_credit-card-reader" to { creditCardReaderIconState.startAnimation() },
            "lucide_credit-card-x" to { creditCardXIconState.startAnimation() },
            "lucide_crop" to { cropIconState.startAnimation() },
            "lucide_crown" to { crownIconState.startAnimation() },
            "lucide_database-arrow-down" to { databaseArrowDownIconState.startAnimation() },
            "lucide_database-arrow-up" to { databaseArrowUpIconState.startAnimation() },
            "lucide_database-backup" to { databaseBackupIconState.startAnimation() },
            "lucide_database-check" to { databaseCheckIconState.startAnimation() },
            "lucide_database" to { databaseIconState.startAnimation() },
            "lucide_database-minus" to { databaseMinusIconState.startAnimation() },
            "lucide_database-plus" to { databasePlusIconState.startAnimation() },
        ),
        mapOf(
            "lucide_circle-chevron-down" to { circleChevronDownIconState.stopAnimation() },
            "lucide_circle-chevron-left" to { circleChevronLeftIconState.stopAnimation() },
            "lucide_circle-chevron-right" to { circleChevronRightIconState.stopAnimation() },
            "lucide_circle-chevron-up" to { circleChevronUpIconState.stopAnimation() },
            "lucide_circle-dashed" to { circleDashedIconState.stopAnimation() },
            "lucide_circle-dollar-sign" to { circleDollarSignIconState.stopAnimation() },
            "lucide_circle-dot" to { circleDotIconState.stopAnimation() },
            "lucide_circle-ellipsis" to { circleEllipsisIconState.stopAnimation() },
            "lucide_circle-euro" to { circleEuroIconState.stopAnimation() },
            "lucide_circle" to { circleIconState.stopAnimation() },
            "lucide_circle-minus" to { circleMinusIconState.stopAnimation() },
            "lucide_circle-off" to { circleOffIconState.stopAnimation() },
            "lucide_circle-pause" to { circlePauseIconState.stopAnimation() },
            "lucide_circle-percent" to { circlePercentIconState.stopAnimation() },
            "lucide_circle-play" to { circlePlayIconState.stopAnimation() },
            "lucide_circle-plus" to { circlePlusIconState.stopAnimation() },
            "lucide_circle-pound-sterling" to { circlePoundSterlingIconState.stopAnimation() },
            "lucide_circle-power" to { circlePowerIconState.stopAnimation() },
            "lucide_circle-question-mark" to { circleQuestionMarkIconState.stopAnimation() },
            "lucide_circle-slash-2" to { circleSlash2IconState.stopAnimation() },
            "lucide_circle-slash" to { circleSlashIconState.stopAnimation() },
            "lucide_circle-star" to { circleStarIconState.stopAnimation() },
            "lucide_circle-stop" to { circleStopIconState.stopAnimation() },
            "lucide_circle-user" to { circleUserIconState.stopAnimation() },
            "lucide_circle-user-round" to { circleUserRoundIconState.stopAnimation() },
            "lucide_circle-x" to { circleXIconState.stopAnimation() },
            "lucide_clapperboard" to { clapperboardIconState.stopAnimation() },
            "lucide_clipboard-check" to { clipboardCheckIconState.stopAnimation() },
            "lucide_clipboard-clock" to { clipboardClockIconState.stopAnimation() },
            "lucide_clipboard" to { clipboardIconState.stopAnimation() },
            "lucide_clipboard-list" to { clipboardListIconState.stopAnimation() },
            "lucide_clipboard-paste" to { clipboardPasteIconState.stopAnimation() },
            "lucide_clock-alert" to { clockAlertIconState.stopAnimation() },
            "lucide_clock-arrow-down" to { clockArrowDownIconState.stopAnimation() },
            "lucide_clock-arrow-left" to { clockArrowLeftIconState.stopAnimation() },
            "lucide_clock-arrow-right" to { clockArrowRightIconState.stopAnimation() },
            "lucide_clock-arrow-up" to { clockArrowUpIconState.stopAnimation() },
            "lucide_clock-check" to { clockCheckIconState.stopAnimation() },
            "lucide_clock-fading" to { clockFadingIconState.stopAnimation() },
            "lucide_clock" to { clockIconState.stopAnimation() },
            "lucide_clock-plus" to { clockPlusIconState.stopAnimation() },
            "lucide_cloud-check" to { cloudCheckIconState.stopAnimation() },
            "lucide_cloud-download" to { cloudDownloadIconState.stopAnimation() },
            "lucide_cloud-drizzle" to { cloudDrizzleIconState.stopAnimation() },
            "lucide_cloud-fog" to { cloudFogIconState.stopAnimation() },
            "lucide_cloud-hail" to { cloudHailIconState.stopAnimation() },
            "lucide_cloud" to { cloudIconState.stopAnimation() },
            "lucide_cloud-lightning" to { cloudLightningIconState.stopAnimation() },
            "lucide_cloud-moon" to { cloudMoonIconState.stopAnimation() },
            "lucide_cloud-moon-rain" to { cloudMoonRainIconState.stopAnimation() },
            "lucide_cloud-off" to { cloudOffIconState.stopAnimation() },
            "lucide_cloud-rain" to { cloudRainIconState.stopAnimation() },
            "lucide_cloud-rain-wind" to { cloudRainWindIconState.stopAnimation() },
            "lucide_cloud-snow" to { cloudSnowIconState.stopAnimation() },
            "lucide_cloud-sun" to { cloudSunIconState.stopAnimation() },
            "lucide_cloud-sun-rain" to { cloudSunRainIconState.stopAnimation() },
            "lucide_cloud-sync" to { lucideCloudSyncIconState.stopAnimation() },
            "lucide_cloud-upload" to { cloudUploadIconState.stopAnimation() },
            "lucide_cloudy" to { cloudyIconState.stopAnimation() },
            "lucide_code" to { codeIconState.stopAnimation() },
            "lucide_code-xml" to { codeXmlIconState.stopAnimation() },
            "lucide_coffee" to { coffeeIconState.stopAnimation() },
            "lucide_coins" to { coinsIconState.stopAnimation() },
            "lucide_columns-2" to { columns2IconState.stopAnimation() },
            "lucide_columns-3" to { columns3IconState.stopAnimation() },
            "lucide_columns-4" to { columns4IconState.stopAnimation() },
            "lucide_command" to { commandIconState.stopAnimation() },
            "lucide_compass" to { compassIconState.stopAnimation() },
            "lucide_component" to { componentIconState.stopAnimation() },
            "lucide_contact" to { contactIconState.stopAnimation() },
            "lucide_contact-round" to { contactRoundIconState.stopAnimation() },
            "lucide_contrast" to { contrastIconState.stopAnimation() },
            "lucide_cookie" to { cookieIconState.stopAnimation() },
            "lucide_copy-check" to { copyCheckIconState.stopAnimation() },
            "lucide_copy" to { copyIconState.stopAnimation() },
            "lucide_copy-plus" to { copyPlusIconState.stopAnimation() },
            "lucide_corner-down-left" to { cornerDownLeftIconState.stopAnimation() },
            "lucide_corner-down-right" to { cornerDownRightIconState.stopAnimation() },
            "lucide_corner-left-down" to { cornerLeftDownIconState.stopAnimation() },
            "lucide_corner-left-up" to { cornerLeftUpIconState.stopAnimation() },
            "lucide_corner-right-down" to { cornerRightDownIconState.stopAnimation() },
            "lucide_corner-right-up" to { cornerRightUpIconState.stopAnimation() },
            "lucide_corner-up-left" to { cornerUpLeftIconState.stopAnimation() },
            "lucide_corner-up-right" to { cornerUpRightIconState.stopAnimation() },
            "lucide_cpu" to { cpuIconState.stopAnimation() },
            "lucide_credit-card-check" to { creditCardCheckIconState.stopAnimation() },
            "lucide_credit-card" to { creditCardIconState.stopAnimation() },
            "lucide_credit-card-minus" to { creditCardMinusIconState.stopAnimation() },
            "lucide_credit-card-plus" to { creditCardPlusIconState.stopAnimation() },
            "lucide_credit-card-reader" to { creditCardReaderIconState.stopAnimation() },
            "lucide_credit-card-x" to { creditCardXIconState.stopAnimation() },
            "lucide_crop" to { cropIconState.stopAnimation() },
            "lucide_crown" to { crownIconState.stopAnimation() },
            "lucide_database-arrow-down" to { databaseArrowDownIconState.stopAnimation() },
            "lucide_database-arrow-up" to { databaseArrowUpIconState.stopAnimation() },
            "lucide_database-backup" to { databaseBackupIconState.stopAnimation() },
            "lucide_database-check" to { databaseCheckIconState.stopAnimation() },
            "lucide_database" to { databaseIconState.stopAnimation() },
            "lucide_database-minus" to { databaseMinusIconState.stopAnimation() },
            "lucide_database-plus" to { databasePlusIconState.stopAnimation() },
        ),
    )
}

@Composable
private fun rememberLucideStatesChunk3(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val databaseSearchIconState = rememberDatabaseSearchIconState()
    val databaseXIconState = rememberDatabaseXIconState()
    val databaseZapIconState = rememberDatabaseZapIconState()
    val deleteIconState = rememberDeleteIconState()
    val diamondIconState = rememberDiamondIconState()
    val diamondMinusIconState = rememberDiamondMinusIconState()
    val diamondPercentIconState = rememberDiamondPercentIconState()
    val diamondPlusIconState = rememberDiamondPlusIconState()
    val dice5IconState = rememberDice5IconState()
    val diffIconState = rememberDiffIconState()
    val disc2IconState = rememberDisc2IconState()
    val disc3IconState = rememberDisc3IconState()
    val discAlbumIconState = rememberDiscAlbumIconState()
    val discIconState = rememberDiscIconState()
    val dogIconState = rememberDogIconState()
    val dollarSignIconState = rememberDollarSignIconState()
    val doorOpenIconState = rememberDoorOpenIconState()
    val downloadIconState = rememberDownloadIconState()
    val dropletIconState = rememberDropletIconState()
    val dropletsIconState = rememberDropletsIconState()
    val drumIconState = rememberDrumIconState()
    val dumbbellIconState = rememberDumbbellIconState()
    val ellipsisIconState = rememberEllipsisIconState()
    val ellipsisVerticalIconState = rememberEllipsisVerticalIconState()
    val eraserIconState = rememberEraserIconState()
    val euroIconState = rememberEuroIconState()
    val expandIconState = rememberExpandIconState()
    val externalLinkIconState = rememberExternalLinkIconState()
    val eyeClosedIconState = rememberEyeClosedIconState()
    val eyeIconState = rememberEyeIconState()
    val eyeOffIconState = rememberEyeOffIconState()
    val facebookIconState = rememberFacebookIconState()
    val factoryIconState = rememberFactoryIconState()
    val fastForwardIconState = rememberFastForwardIconState()
    val featherIconState = rememberFeatherIconState()
    val figmaIconState = rememberFigmaIconState()
    val fileArchiveIconState = rememberFileArchiveIconState()
    val fileCheckIconState = rememberFileCheckIconState()
    val fileClockIconState = rememberFileClockIconState()
    val fileCodeIconState = rememberFileCodeIconState()
    val fileDownIconState = rememberFileDownIconState()
    val fileHeartIconState = rememberFileHeartIconState()
    val fileIconState = rememberFileIconState()
    val fileImageIconState = rememberFileImageIconState()
    val fileKeyIconState = rememberFileKeyIconState()
    val fileLockIconState = rememberFileLockIconState()
    val fileMinusIconState = rememberFileMinusIconState()
    val fileMusicIconState = rememberFileMusicIconState()
    val filePenIconState = rememberFilePenIconState()
    val filePlusIconState = rememberFilePlusIconState()
    val fileScanIconState = rememberFileScanIconState()
    val fileSearchIconState = rememberFileSearchIconState()
    val fileSpreadsheetIconState = rememberFileSpreadsheetIconState()
    val fileTerminalIconState = rememberFileTerminalIconState()
    val fileTextIconState = rememberFileTextIconState()
    val fileTypeIconState = rememberFileTypeIconState()
    val fileUpIconState = rememberFileUpIconState()
    val fileXIconState = rememberFileXIconState()
    val filesIconState = rememberFilesIconState()
    val filmIconState = rememberFilmIconState()
    val filterIconState = rememberFilterIconState()
    val fingerprintPatternIconState = rememberFingerprintPatternIconState()
    val fishIconState = rememberFishIconState()
    val flagIconState = rememberFlagIconState()
    val flagOffIconState = rememberFlagOffIconState()
    val flagTriangleRightIconState = rememberFlagTriangleRightIconState()
    val lucideFlameIconState = rememberLucideFlameIconState()
    val flaskConicalIconState = rememberFlaskConicalIconState()
    val foldVerticalIconState = rememberFoldVerticalIconState()
    val folderCheckIconState = rememberFolderCheckIconState()
    val folderClosedIconState = rememberFolderClosedIconState()
    val folderDotIconState = rememberFolderDotIconState()
    val folderHeartIconState = rememberFolderHeartIconState()
    val folderIconState = rememberFolderIconState()
    val folderKeyIconState = rememberFolderKeyIconState()
    val folderLockIconState = rememberFolderLockIconState()
    val folderMinusIconState = rememberFolderMinusIconState()
    val folderOpenIconState = rememberFolderOpenIconState()
    val folderPlusIconState = rememberFolderPlusIconState()
    val folderSearchIconState = rememberFolderSearchIconState()
    val folderTreeIconState = rememberFolderTreeIconState()
    val folderXIconState = rememberFolderXIconState()
    val footprintsIconState = rememberFootprintsIconState()
    val framerIconState = rememberFramerIconState()
    val fullscreenIconState = rememberFullscreenIconState()
    val funnelXIconState = rememberFunnelXIconState()
    val galleryHorizontalIconState = rememberGalleryHorizontalIconState()
    val gamepadIconState = rememberGamepadIconState()
    val gaugeIconState = rememberGaugeIconState()
    val gemIconState = rememberGemIconState()
    val georgianLariIconState = rememberGeorgianLariIconState()
    val ghostIconState = rememberGhostIconState()
    val giftIconState = rememberGiftIconState()
    val gitBranchIconState = rememberGitBranchIconState()
    val gitBranchMinusIconState = rememberGitBranchMinusIconState()
    val gitBranchPlusIconState = rememberGitBranchPlusIconState()
    val gitCommitHorizontalIconState = rememberGitCommitHorizontalIconState()
    val gitCommitVerticalIconState = rememberGitCommitVerticalIconState()
    val gitCompareArrowsIconState = rememberGitCompareArrowsIconState()
    val gitCompareIconState = rememberGitCompareIconState()
    return Triple(
        mapOf(
            "lucide_database-search" to databaseSearchIconState,
            "lucide_database-x" to databaseXIconState,
            "lucide_database-zap" to databaseZapIconState,
            "lucide_delete" to deleteIconState,
            "lucide_diamond" to diamondIconState,
            "lucide_diamond-minus" to diamondMinusIconState,
            "lucide_diamond-percent" to diamondPercentIconState,
            "lucide_diamond-plus" to diamondPlusIconState,
            "lucide_dice-5" to dice5IconState,
            "lucide_diff" to diffIconState,
            "lucide_disc-2" to disc2IconState,
            "lucide_disc-3" to disc3IconState,
            "lucide_disc-album" to discAlbumIconState,
            "lucide_disc" to discIconState,
            "lucide_dog" to dogIconState,
            "lucide_dollar-sign" to dollarSignIconState,
            "lucide_door-open" to doorOpenIconState,
            "lucide_download" to downloadIconState,
            "lucide_droplet" to dropletIconState,
            "lucide_droplets" to dropletsIconState,
            "lucide_drum" to drumIconState,
            "lucide_dumbbell" to dumbbellIconState,
            "lucide_ellipsis" to ellipsisIconState,
            "lucide_ellipsis-vertical" to ellipsisVerticalIconState,
            "lucide_eraser" to eraserIconState,
            "lucide_euro" to euroIconState,
            "lucide_expand" to expandIconState,
            "lucide_external-link" to externalLinkIconState,
            "lucide_eye-closed" to eyeClosedIconState,
            "lucide_eye" to eyeIconState,
            "lucide_eye-off" to eyeOffIconState,
            "lucide_facebook" to facebookIconState,
            "lucide_factory" to factoryIconState,
            "lucide_fast-forward" to fastForwardIconState,
            "lucide_feather" to featherIconState,
            "lucide_figma" to figmaIconState,
            "lucide_file-archive" to fileArchiveIconState,
            "lucide_file-check" to fileCheckIconState,
            "lucide_file-clock" to fileClockIconState,
            "lucide_file-code" to fileCodeIconState,
            "lucide_file-down" to fileDownIconState,
            "lucide_file-heart" to fileHeartIconState,
            "lucide_file" to fileIconState,
            "lucide_file-image" to fileImageIconState,
            "lucide_file-key" to fileKeyIconState,
            "lucide_file-lock" to fileLockIconState,
            "lucide_file-minus" to fileMinusIconState,
            "lucide_file-music" to fileMusicIconState,
            "lucide_file-pen" to filePenIconState,
            "lucide_file-plus" to filePlusIconState,
            "lucide_file-scan" to fileScanIconState,
            "lucide_file-search" to fileSearchIconState,
            "lucide_file-spreadsheet" to fileSpreadsheetIconState,
            "lucide_file-terminal" to fileTerminalIconState,
            "lucide_file-text" to fileTextIconState,
            "lucide_file-type" to fileTypeIconState,
            "lucide_file-up" to fileUpIconState,
            "lucide_file-x" to fileXIconState,
            "lucide_files" to filesIconState,
            "lucide_film" to filmIconState,
            "lucide_filter" to filterIconState,
            "lucide_fingerprint-pattern" to fingerprintPatternIconState,
            "lucide_fish" to fishIconState,
            "lucide_flag" to flagIconState,
            "lucide_flag-off" to flagOffIconState,
            "lucide_flag-triangle-right" to flagTriangleRightIconState,
            "lucide_flame" to lucideFlameIconState,
            "lucide_flask-conical" to flaskConicalIconState,
            "lucide_fold-vertical" to foldVerticalIconState,
            "lucide_folder-check" to folderCheckIconState,
            "lucide_folder-closed" to folderClosedIconState,
            "lucide_folder-dot" to folderDotIconState,
            "lucide_folder-heart" to folderHeartIconState,
            "lucide_folder" to folderIconState,
            "lucide_folder-key" to folderKeyIconState,
            "lucide_folder-lock" to folderLockIconState,
            "lucide_folder-minus" to folderMinusIconState,
            "lucide_folder-open" to folderOpenIconState,
            "lucide_folder-plus" to folderPlusIconState,
            "lucide_folder-search" to folderSearchIconState,
            "lucide_folder-tree" to folderTreeIconState,
            "lucide_folder-x" to folderXIconState,
            "lucide_footprints" to footprintsIconState,
            "lucide_framer" to framerIconState,
            "lucide_fullscreen" to fullscreenIconState,
            "lucide_funnel-x" to funnelXIconState,
            "lucide_gallery-horizontal" to galleryHorizontalIconState,
            "lucide_gamepad" to gamepadIconState,
            "lucide_gauge" to gaugeIconState,
            "lucide_gem" to gemIconState,
            "lucide_georgian-lari" to georgianLariIconState,
            "lucide_ghost" to ghostIconState,
            "lucide_gift" to giftIconState,
            "lucide_git-branch" to gitBranchIconState,
            "lucide_git-branch-minus" to gitBranchMinusIconState,
            "lucide_git-branch-plus" to gitBranchPlusIconState,
            "lucide_git-commit-horizontal" to gitCommitHorizontalIconState,
            "lucide_git-commit-vertical" to gitCommitVerticalIconState,
            "lucide_git-compare-arrows" to gitCompareArrowsIconState,
            "lucide_git-compare" to gitCompareIconState,
        ),
        mapOf(
            "lucide_database-search" to { databaseSearchIconState.startAnimation() },
            "lucide_database-x" to { databaseXIconState.startAnimation() },
            "lucide_database-zap" to { databaseZapIconState.startAnimation() },
            "lucide_delete" to { deleteIconState.startAnimation() },
            "lucide_diamond" to { diamondIconState.startAnimation() },
            "lucide_diamond-minus" to { diamondMinusIconState.startAnimation() },
            "lucide_diamond-percent" to { diamondPercentIconState.startAnimation() },
            "lucide_diamond-plus" to { diamondPlusIconState.startAnimation() },
            "lucide_dice-5" to { dice5IconState.startAnimation() },
            "lucide_diff" to { diffIconState.startAnimation() },
            "lucide_disc-2" to { disc2IconState.startAnimation() },
            "lucide_disc-3" to { disc3IconState.startAnimation() },
            "lucide_disc-album" to { discAlbumIconState.startAnimation() },
            "lucide_disc" to { discIconState.startAnimation() },
            "lucide_dog" to { dogIconState.startAnimation() },
            "lucide_dollar-sign" to { dollarSignIconState.startAnimation() },
            "lucide_door-open" to { doorOpenIconState.startAnimation() },
            "lucide_download" to { downloadIconState.startAnimation() },
            "lucide_droplet" to { dropletIconState.startAnimation() },
            "lucide_droplets" to { dropletsIconState.startAnimation() },
            "lucide_drum" to { drumIconState.startAnimation() },
            "lucide_dumbbell" to { dumbbellIconState.startAnimation() },
            "lucide_ellipsis" to { ellipsisIconState.startAnimation() },
            "lucide_ellipsis-vertical" to { ellipsisVerticalIconState.startAnimation() },
            "lucide_eraser" to { eraserIconState.startAnimation() },
            "lucide_euro" to { euroIconState.startAnimation() },
            "lucide_expand" to { expandIconState.startAnimation() },
            "lucide_external-link" to { externalLinkIconState.startAnimation() },
            "lucide_eye-closed" to { eyeClosedIconState.startAnimation() },
            "lucide_eye" to { eyeIconState.startAnimation() },
            "lucide_eye-off" to { eyeOffIconState.startAnimation() },
            "lucide_facebook" to { facebookIconState.startAnimation() },
            "lucide_factory" to { factoryIconState.startAnimation() },
            "lucide_fast-forward" to { fastForwardIconState.startAnimation() },
            "lucide_feather" to { featherIconState.startAnimation() },
            "lucide_figma" to { figmaIconState.startAnimation() },
            "lucide_file-archive" to { fileArchiveIconState.startAnimation() },
            "lucide_file-check" to { fileCheckIconState.startAnimation() },
            "lucide_file-clock" to { fileClockIconState.startAnimation() },
            "lucide_file-code" to { fileCodeIconState.startAnimation() },
            "lucide_file-down" to { fileDownIconState.startAnimation() },
            "lucide_file-heart" to { fileHeartIconState.startAnimation() },
            "lucide_file" to { fileIconState.startAnimation() },
            "lucide_file-image" to { fileImageIconState.startAnimation() },
            "lucide_file-key" to { fileKeyIconState.startAnimation() },
            "lucide_file-lock" to { fileLockIconState.startAnimation() },
            "lucide_file-minus" to { fileMinusIconState.startAnimation() },
            "lucide_file-music" to { fileMusicIconState.startAnimation() },
            "lucide_file-pen" to { filePenIconState.startAnimation() },
            "lucide_file-plus" to { filePlusIconState.startAnimation() },
            "lucide_file-scan" to { fileScanIconState.startAnimation() },
            "lucide_file-search" to { fileSearchIconState.startAnimation() },
            "lucide_file-spreadsheet" to { fileSpreadsheetIconState.startAnimation() },
            "lucide_file-terminal" to { fileTerminalIconState.startAnimation() },
            "lucide_file-text" to { fileTextIconState.startAnimation() },
            "lucide_file-type" to { fileTypeIconState.startAnimation() },
            "lucide_file-up" to { fileUpIconState.startAnimation() },
            "lucide_file-x" to { fileXIconState.startAnimation() },
            "lucide_files" to { filesIconState.startAnimation() },
            "lucide_film" to { filmIconState.startAnimation() },
            "lucide_filter" to { filterIconState.startAnimation() },
            "lucide_fingerprint-pattern" to { fingerprintPatternIconState.startAnimation() },
            "lucide_fish" to { fishIconState.startAnimation() },
            "lucide_flag" to { flagIconState.startAnimation() },
            "lucide_flag-off" to { flagOffIconState.startAnimation() },
            "lucide_flag-triangle-right" to { flagTriangleRightIconState.startAnimation() },
            "lucide_flame" to { lucideFlameIconState.startAnimation() },
            "lucide_flask-conical" to { flaskConicalIconState.startAnimation() },
            "lucide_fold-vertical" to { foldVerticalIconState.startAnimation() },
            "lucide_folder-check" to { folderCheckIconState.startAnimation() },
            "lucide_folder-closed" to { folderClosedIconState.startAnimation() },
            "lucide_folder-dot" to { folderDotIconState.startAnimation() },
            "lucide_folder-heart" to { folderHeartIconState.startAnimation() },
            "lucide_folder" to { folderIconState.startAnimation() },
            "lucide_folder-key" to { folderKeyIconState.startAnimation() },
            "lucide_folder-lock" to { folderLockIconState.startAnimation() },
            "lucide_folder-minus" to { folderMinusIconState.startAnimation() },
            "lucide_folder-open" to { folderOpenIconState.startAnimation() },
            "lucide_folder-plus" to { folderPlusIconState.startAnimation() },
            "lucide_folder-search" to { folderSearchIconState.startAnimation() },
            "lucide_folder-tree" to { folderTreeIconState.startAnimation() },
            "lucide_folder-x" to { folderXIconState.startAnimation() },
            "lucide_footprints" to { footprintsIconState.startAnimation() },
            "lucide_framer" to { framerIconState.startAnimation() },
            "lucide_fullscreen" to { fullscreenIconState.startAnimation() },
            "lucide_funnel-x" to { funnelXIconState.startAnimation() },
            "lucide_gallery-horizontal" to { galleryHorizontalIconState.startAnimation() },
            "lucide_gamepad" to { gamepadIconState.startAnimation() },
            "lucide_gauge" to { gaugeIconState.startAnimation() },
            "lucide_gem" to { gemIconState.startAnimation() },
            "lucide_georgian-lari" to { georgianLariIconState.startAnimation() },
            "lucide_ghost" to { ghostIconState.startAnimation() },
            "lucide_gift" to { giftIconState.startAnimation() },
            "lucide_git-branch" to { gitBranchIconState.startAnimation() },
            "lucide_git-branch-minus" to { gitBranchMinusIconState.startAnimation() },
            "lucide_git-branch-plus" to { gitBranchPlusIconState.startAnimation() },
            "lucide_git-commit-horizontal" to { gitCommitHorizontalIconState.startAnimation() },
            "lucide_git-commit-vertical" to { gitCommitVerticalIconState.startAnimation() },
            "lucide_git-compare-arrows" to { gitCompareArrowsIconState.startAnimation() },
            "lucide_git-compare" to { gitCompareIconState.startAnimation() },
        ),
        mapOf(
            "lucide_database-search" to { databaseSearchIconState.stopAnimation() },
            "lucide_database-x" to { databaseXIconState.stopAnimation() },
            "lucide_database-zap" to { databaseZapIconState.stopAnimation() },
            "lucide_delete" to { deleteIconState.stopAnimation() },
            "lucide_diamond" to { diamondIconState.stopAnimation() },
            "lucide_diamond-minus" to { diamondMinusIconState.stopAnimation() },
            "lucide_diamond-percent" to { diamondPercentIconState.stopAnimation() },
            "lucide_diamond-plus" to { diamondPlusIconState.stopAnimation() },
            "lucide_dice-5" to { dice5IconState.stopAnimation() },
            "lucide_diff" to { diffIconState.stopAnimation() },
            "lucide_disc-2" to { disc2IconState.stopAnimation() },
            "lucide_disc-3" to { disc3IconState.stopAnimation() },
            "lucide_disc-album" to { discAlbumIconState.stopAnimation() },
            "lucide_disc" to { discIconState.stopAnimation() },
            "lucide_dog" to { dogIconState.stopAnimation() },
            "lucide_dollar-sign" to { dollarSignIconState.stopAnimation() },
            "lucide_door-open" to { doorOpenIconState.stopAnimation() },
            "lucide_download" to { downloadIconState.stopAnimation() },
            "lucide_droplet" to { dropletIconState.stopAnimation() },
            "lucide_droplets" to { dropletsIconState.stopAnimation() },
            "lucide_drum" to { drumIconState.stopAnimation() },
            "lucide_dumbbell" to { dumbbellIconState.stopAnimation() },
            "lucide_ellipsis" to { ellipsisIconState.stopAnimation() },
            "lucide_ellipsis-vertical" to { ellipsisVerticalIconState.stopAnimation() },
            "lucide_eraser" to { eraserIconState.stopAnimation() },
            "lucide_euro" to { euroIconState.stopAnimation() },
            "lucide_expand" to { expandIconState.stopAnimation() },
            "lucide_external-link" to { externalLinkIconState.stopAnimation() },
            "lucide_eye-closed" to { eyeClosedIconState.stopAnimation() },
            "lucide_eye" to { eyeIconState.stopAnimation() },
            "lucide_eye-off" to { eyeOffIconState.stopAnimation() },
            "lucide_facebook" to { facebookIconState.stopAnimation() },
            "lucide_factory" to { factoryIconState.stopAnimation() },
            "lucide_fast-forward" to { fastForwardIconState.stopAnimation() },
            "lucide_feather" to { featherIconState.stopAnimation() },
            "lucide_figma" to { figmaIconState.stopAnimation() },
            "lucide_file-archive" to { fileArchiveIconState.stopAnimation() },
            "lucide_file-check" to { fileCheckIconState.stopAnimation() },
            "lucide_file-clock" to { fileClockIconState.stopAnimation() },
            "lucide_file-code" to { fileCodeIconState.stopAnimation() },
            "lucide_file-down" to { fileDownIconState.stopAnimation() },
            "lucide_file-heart" to { fileHeartIconState.stopAnimation() },
            "lucide_file" to { fileIconState.stopAnimation() },
            "lucide_file-image" to { fileImageIconState.stopAnimation() },
            "lucide_file-key" to { fileKeyIconState.stopAnimation() },
            "lucide_file-lock" to { fileLockIconState.stopAnimation() },
            "lucide_file-minus" to { fileMinusIconState.stopAnimation() },
            "lucide_file-music" to { fileMusicIconState.stopAnimation() },
            "lucide_file-pen" to { filePenIconState.stopAnimation() },
            "lucide_file-plus" to { filePlusIconState.stopAnimation() },
            "lucide_file-scan" to { fileScanIconState.stopAnimation() },
            "lucide_file-search" to { fileSearchIconState.stopAnimation() },
            "lucide_file-spreadsheet" to { fileSpreadsheetIconState.stopAnimation() },
            "lucide_file-terminal" to { fileTerminalIconState.stopAnimation() },
            "lucide_file-text" to { fileTextIconState.stopAnimation() },
            "lucide_file-type" to { fileTypeIconState.stopAnimation() },
            "lucide_file-up" to { fileUpIconState.stopAnimation() },
            "lucide_file-x" to { fileXIconState.stopAnimation() },
            "lucide_files" to { filesIconState.stopAnimation() },
            "lucide_film" to { filmIconState.stopAnimation() },
            "lucide_filter" to { filterIconState.stopAnimation() },
            "lucide_fingerprint-pattern" to { fingerprintPatternIconState.stopAnimation() },
            "lucide_fish" to { fishIconState.stopAnimation() },
            "lucide_flag" to { flagIconState.stopAnimation() },
            "lucide_flag-off" to { flagOffIconState.stopAnimation() },
            "lucide_flag-triangle-right" to { flagTriangleRightIconState.stopAnimation() },
            "lucide_flame" to { lucideFlameIconState.stopAnimation() },
            "lucide_flask-conical" to { flaskConicalIconState.stopAnimation() },
            "lucide_fold-vertical" to { foldVerticalIconState.stopAnimation() },
            "lucide_folder-check" to { folderCheckIconState.stopAnimation() },
            "lucide_folder-closed" to { folderClosedIconState.stopAnimation() },
            "lucide_folder-dot" to { folderDotIconState.stopAnimation() },
            "lucide_folder-heart" to { folderHeartIconState.stopAnimation() },
            "lucide_folder" to { folderIconState.stopAnimation() },
            "lucide_folder-key" to { folderKeyIconState.stopAnimation() },
            "lucide_folder-lock" to { folderLockIconState.stopAnimation() },
            "lucide_folder-minus" to { folderMinusIconState.stopAnimation() },
            "lucide_folder-open" to { folderOpenIconState.stopAnimation() },
            "lucide_folder-plus" to { folderPlusIconState.stopAnimation() },
            "lucide_folder-search" to { folderSearchIconState.stopAnimation() },
            "lucide_folder-tree" to { folderTreeIconState.stopAnimation() },
            "lucide_folder-x" to { folderXIconState.stopAnimation() },
            "lucide_footprints" to { footprintsIconState.stopAnimation() },
            "lucide_framer" to { framerIconState.stopAnimation() },
            "lucide_fullscreen" to { fullscreenIconState.stopAnimation() },
            "lucide_funnel-x" to { funnelXIconState.stopAnimation() },
            "lucide_gallery-horizontal" to { galleryHorizontalIconState.stopAnimation() },
            "lucide_gamepad" to { gamepadIconState.stopAnimation() },
            "lucide_gauge" to { gaugeIconState.stopAnimation() },
            "lucide_gem" to { gemIconState.stopAnimation() },
            "lucide_georgian-lari" to { georgianLariIconState.stopAnimation() },
            "lucide_ghost" to { ghostIconState.stopAnimation() },
            "lucide_gift" to { giftIconState.stopAnimation() },
            "lucide_git-branch" to { gitBranchIconState.stopAnimation() },
            "lucide_git-branch-minus" to { gitBranchMinusIconState.stopAnimation() },
            "lucide_git-branch-plus" to { gitBranchPlusIconState.stopAnimation() },
            "lucide_git-commit-horizontal" to { gitCommitHorizontalIconState.stopAnimation() },
            "lucide_git-commit-vertical" to { gitCommitVerticalIconState.stopAnimation() },
            "lucide_git-compare-arrows" to { gitCompareArrowsIconState.stopAnimation() },
            "lucide_git-compare" to { gitCompareIconState.stopAnimation() },
        ),
    )
}

@Composable
private fun rememberLucideStatesChunk4(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val gitForkIconState = rememberGitForkIconState()
    val gitGraphIconState = rememberGitGraphIconState()
    val gitMergeConflictIconState = rememberGitMergeConflictIconState()
    val gitMergeIconState = rememberGitMergeIconState()
    val gitPullRequestArrowIconState = rememberGitPullRequestArrowIconState()
    val gitPullRequestClosedIconState = rememberGitPullRequestClosedIconState()
    val gitPullRequestCreateArrowIconState = rememberGitPullRequestCreateArrowIconState()
    val gitPullRequestCreateIconState = rememberGitPullRequestCreateIconState()
    val gitPullRequestDraftIconState = rememberGitPullRequestDraftIconState()
    val gitPullRequestIconState = rememberGitPullRequestIconState()
    val githubIconState = rememberGithubIconState()
    val gitlabIconState = rememberGitlabIconState()
    val globeIconState = rememberGlobeIconState()
    val globeLockIconState = rememberGlobeLockIconState()
    val graduationCapIconState = rememberGraduationCapIconState()
    val grid2x2CheckIconState = rememberGrid2x2CheckIconState()
    val grid2x2IconState = rememberGrid2x2IconState()
    val grid2x2PlusIconState = rememberGrid2x2PlusIconState()
    val grid2x2XIconState = rememberGrid2x2XIconState()
    val grid3x2IconState = rememberGrid3x2IconState()
    val grid3x3IconState = rememberGrid3x3IconState()
    val gripVerticalIconState = rememberGripVerticalIconState()
    val guitarIconState = rememberGuitarIconState()
    val hammerIconState = rememberHammerIconState()
    val handCoinsIconState = rememberHandCoinsIconState()
    val handHeartIconState = rememberHandHeartIconState()
    val handIconState = rememberHandIconState()
    val handshakeIconState = rememberHandshakeIconState()
    val hardDriveDownloadIconState = rememberHardDriveDownloadIconState()
    val hardDriveIconState = rememberHardDriveIconState()
    val hardDriveUploadIconState = rememberHardDriveUploadIconState()
    val hashIconState = rememberHashIconState()
    val hazeIconState = rememberHazeIconState()
    val heading1IconState = rememberHeading1IconState()
    val heading2IconState = rememberHeading2IconState()
    val heading3IconState = rememberHeading3IconState()
    val heading4IconState = rememberHeading4IconState()
    val heading5IconState = rememberHeading5IconState()
    val heading6IconState = rememberHeading6IconState()
    val headingIconState = rememberHeadingIconState()
    val headphoneOffIconState = rememberHeadphoneOffIconState()
    val headphonesIconState = rememberHeadphonesIconState()
    val headsetIconState = rememberHeadsetIconState()
    val heartCrackIconState = rememberHeartCrackIconState()
    val heartHandshakeIconState = rememberHeartHandshakeIconState()
    val heartIconState = rememberHeartIconState()
    val heartMinusIconState = rememberHeartMinusIconState()
    val heartOffIconState = rememberHeartOffIconState()
    val heartPlusIconState = rememberHeartPlusIconState()
    val heartPulseIconState = rememberHeartPulseIconState()
    val heartXIconState = rememberHeartXIconState()
    val hexagonIconState = rememberHexagonIconState()
    val highlighterIconState = rememberHighlighterIconState()
    val historyIconState = rememberHistoryIconState()
    val hospitalIconState = rememberHospitalIconState()
    val hotelIconState = rememberHotelIconState()
    val hourglassIconState = rememberHourglassIconState()
    val houseCogIconState = rememberHouseCogIconState()
    val houseHeartIconState = rememberHouseHeartIconState()
    val houseIconState = rememberHouseIconState()
    val housePlugIconState = rememberHousePlugIconState()
    val housePlusIconState = rememberHousePlusIconState()
    val houseWifiIconState = rememberHouseWifiIconState()
    val imageIconState = rememberImageIconState()
    val imageOffIconState = rememberImageOffIconState()
    val imagePlusIconState = rememberImagePlusIconState()
    val inboxIconState = rememberInboxIconState()
    val indentDecreaseIconState = rememberIndentDecreaseIconState()
    val indentIncreaseIconState = rememberIndentIncreaseIconState()
    val indianRupeeIconState = rememberIndianRupeeIconState()
    val infinityIconState = rememberInfinityIconState()
    val infoIconState = rememberInfoIconState()
    val instagramIconState = rememberInstagramIconState()
    val italicIconState = rememberItalicIconState()
    val japaneseYenIconState = rememberJapaneseYenIconState()
    val kanbanIconState = rememberKanbanIconState()
    val keyIconState = rememberKeyIconState()
    val keyRoundIconState = rememberKeyRoundIconState()
    val keySquareIconState = rememberKeySquareIconState()
    val keyboardIconState = rememberKeyboardIconState()
    val landmarkIconState = rememberLandmarkIconState()
    val languagesIconState = rememberLanguagesIconState()
    val laptopIconState = rememberLaptopIconState()
    val laptopMinimalCheckIconState = rememberLaptopMinimalCheckIconState()
    val laptopMinimalIconState = rememberLaptopMinimalIconState()
    val layersIconState = rememberLayersIconState()
    val layoutDashboardIconState = rememberLayoutDashboardIconState()
    val layoutGridIconState = rememberLayoutGridIconState()
    val layoutListIconState = rememberLayoutListIconState()
    val layoutPanelLeftIconState = rememberLayoutPanelLeftIconState()
    val layoutPanelTopIconState = rememberLayoutPanelTopIconState()
    val layoutTemplateIconState = rememberLayoutTemplateIconState()
    val leafIconState = rememberLeafIconState()
    val lifeBuoyIconState = rememberLifeBuoyIconState()
    val lightbulbIconState = rememberLightbulbIconState()
    val linkIconState = rememberLinkIconState()
    val linkedinIconState = rememberLinkedinIconState()
    val listCheckIconState = rememberListCheckIconState()
    val listChecksIconState = rememberListChecksIconState()
    val listChevronsDownUpIconState = rememberListChevronsDownUpIconState()
    return Triple(
        mapOf(
            "lucide_git-fork" to gitForkIconState,
            "lucide_git-graph" to gitGraphIconState,
            "lucide_git-merge-conflict" to gitMergeConflictIconState,
            "lucide_git-merge" to gitMergeIconState,
            "lucide_git-pull-request-arrow" to gitPullRequestArrowIconState,
            "lucide_git-pull-request-closed" to gitPullRequestClosedIconState,
            "lucide_git-pull-request-create-arrow" to gitPullRequestCreateArrowIconState,
            "lucide_git-pull-request-create" to gitPullRequestCreateIconState,
            "lucide_git-pull-request-draft" to gitPullRequestDraftIconState,
            "lucide_git-pull-request" to gitPullRequestIconState,
            "lucide_github" to githubIconState,
            "lucide_gitlab" to gitlabIconState,
            "lucide_globe" to globeIconState,
            "lucide_globe-lock" to globeLockIconState,
            "lucide_graduation-cap" to graduationCapIconState,
            "lucide_grid-2x2-check" to grid2x2CheckIconState,
            "lucide_grid-2x2" to grid2x2IconState,
            "lucide_grid-2x2-plus" to grid2x2PlusIconState,
            "lucide_grid-2x2-x" to grid2x2XIconState,
            "lucide_grid-3x2" to grid3x2IconState,
            "lucide_grid-3x3" to grid3x3IconState,
            "lucide_grip-vertical" to gripVerticalIconState,
            "lucide_guitar" to guitarIconState,
            "lucide_hammer" to hammerIconState,
            "lucide_hand-coins" to handCoinsIconState,
            "lucide_hand-heart" to handHeartIconState,
            "lucide_hand" to handIconState,
            "lucide_handshake" to handshakeIconState,
            "lucide_hard-drive-download" to hardDriveDownloadIconState,
            "lucide_hard-drive" to hardDriveIconState,
            "lucide_hard-drive-upload" to hardDriveUploadIconState,
            "lucide_hash" to hashIconState,
            "lucide_haze" to hazeIconState,
            "lucide_heading-1" to heading1IconState,
            "lucide_heading-2" to heading2IconState,
            "lucide_heading-3" to heading3IconState,
            "lucide_heading-4" to heading4IconState,
            "lucide_heading-5" to heading5IconState,
            "lucide_heading-6" to heading6IconState,
            "lucide_heading" to headingIconState,
            "lucide_headphone-off" to headphoneOffIconState,
            "lucide_headphones" to headphonesIconState,
            "lucide_headset" to headsetIconState,
            "lucide_heart-crack" to heartCrackIconState,
            "lucide_heart-handshake" to heartHandshakeIconState,
            "lucide_heart" to heartIconState,
            "lucide_heart-minus" to heartMinusIconState,
            "lucide_heart-off" to heartOffIconState,
            "lucide_heart-plus" to heartPlusIconState,
            "lucide_heart-pulse" to heartPulseIconState,
            "lucide_heart-x" to heartXIconState,
            "lucide_hexagon" to hexagonIconState,
            "lucide_highlighter" to highlighterIconState,
            "lucide_history" to historyIconState,
            "lucide_hospital" to hospitalIconState,
            "lucide_hotel" to hotelIconState,
            "lucide_hourglass" to hourglassIconState,
            "lucide_house-cog" to houseCogIconState,
            "lucide_house-heart" to houseHeartIconState,
            "lucide_house" to houseIconState,
            "lucide_house-plug" to housePlugIconState,
            "lucide_house-plus" to housePlusIconState,
            "lucide_house-wifi" to houseWifiIconState,
            "lucide_image" to imageIconState,
            "lucide_image-off" to imageOffIconState,
            "lucide_image-plus" to imagePlusIconState,
            "lucide_inbox" to inboxIconState,
            "lucide_indent-decrease" to indentDecreaseIconState,
            "lucide_indent-increase" to indentIncreaseIconState,
            "lucide_indian-rupee" to indianRupeeIconState,
            "lucide_infinity" to infinityIconState,
            "lucide_info" to infoIconState,
            "lucide_instagram" to instagramIconState,
            "lucide_italic" to italicIconState,
            "lucide_japanese-yen" to japaneseYenIconState,
            "lucide_kanban" to kanbanIconState,
            "lucide_key" to keyIconState,
            "lucide_key-round" to keyRoundIconState,
            "lucide_key-square" to keySquareIconState,
            "lucide_keyboard" to keyboardIconState,
            "lucide_landmark" to landmarkIconState,
            "lucide_languages" to languagesIconState,
            "lucide_laptop" to laptopIconState,
            "lucide_laptop-minimal-check" to laptopMinimalCheckIconState,
            "lucide_laptop-minimal" to laptopMinimalIconState,
            "lucide_layers" to layersIconState,
            "lucide_layout-dashboard" to layoutDashboardIconState,
            "lucide_layout-grid" to layoutGridIconState,
            "lucide_layout-list" to layoutListIconState,
            "lucide_layout-panel-left" to layoutPanelLeftIconState,
            "lucide_layout-panel-top" to layoutPanelTopIconState,
            "lucide_layout-template" to layoutTemplateIconState,
            "lucide_leaf" to leafIconState,
            "lucide_life-buoy" to lifeBuoyIconState,
            "lucide_lightbulb" to lightbulbIconState,
            "lucide_link" to linkIconState,
            "lucide_linkedin" to linkedinIconState,
            "lucide_list-check" to listCheckIconState,
            "lucide_list-checks" to listChecksIconState,
            "lucide_list-chevrons-down-up" to listChevronsDownUpIconState,
        ),
        mapOf(
            "lucide_git-fork" to { gitForkIconState.startAnimation() },
            "lucide_git-graph" to { gitGraphIconState.startAnimation() },
            "lucide_git-merge-conflict" to { gitMergeConflictIconState.startAnimation() },
            "lucide_git-merge" to { gitMergeIconState.startAnimation() },
            "lucide_git-pull-request-arrow" to { gitPullRequestArrowIconState.startAnimation() },
            "lucide_git-pull-request-closed" to { gitPullRequestClosedIconState.startAnimation() },
            "lucide_git-pull-request-create-arrow" to { gitPullRequestCreateArrowIconState.startAnimation() },
            "lucide_git-pull-request-create" to { gitPullRequestCreateIconState.startAnimation() },
            "lucide_git-pull-request-draft" to { gitPullRequestDraftIconState.startAnimation() },
            "lucide_git-pull-request" to { gitPullRequestIconState.startAnimation() },
            "lucide_github" to { githubIconState.startAnimation() },
            "lucide_gitlab" to { gitlabIconState.startAnimation() },
            "lucide_globe" to { globeIconState.startAnimation() },
            "lucide_globe-lock" to { globeLockIconState.startAnimation() },
            "lucide_graduation-cap" to { graduationCapIconState.startAnimation() },
            "lucide_grid-2x2-check" to { grid2x2CheckIconState.startAnimation() },
            "lucide_grid-2x2" to { grid2x2IconState.startAnimation() },
            "lucide_grid-2x2-plus" to { grid2x2PlusIconState.startAnimation() },
            "lucide_grid-2x2-x" to { grid2x2XIconState.startAnimation() },
            "lucide_grid-3x2" to { grid3x2IconState.startAnimation() },
            "lucide_grid-3x3" to { grid3x3IconState.startAnimation() },
            "lucide_grip-vertical" to { gripVerticalIconState.startAnimation() },
            "lucide_guitar" to { guitarIconState.startAnimation() },
            "lucide_hammer" to { hammerIconState.startAnimation() },
            "lucide_hand-coins" to { handCoinsIconState.startAnimation() },
            "lucide_hand-heart" to { handHeartIconState.startAnimation() },
            "lucide_hand" to { handIconState.startAnimation() },
            "lucide_handshake" to { handshakeIconState.startAnimation() },
            "lucide_hard-drive-download" to { hardDriveDownloadIconState.startAnimation() },
            "lucide_hard-drive" to { hardDriveIconState.startAnimation() },
            "lucide_hard-drive-upload" to { hardDriveUploadIconState.startAnimation() },
            "lucide_hash" to { hashIconState.startAnimation() },
            "lucide_haze" to { hazeIconState.startAnimation() },
            "lucide_heading-1" to { heading1IconState.startAnimation() },
            "lucide_heading-2" to { heading2IconState.startAnimation() },
            "lucide_heading-3" to { heading3IconState.startAnimation() },
            "lucide_heading-4" to { heading4IconState.startAnimation() },
            "lucide_heading-5" to { heading5IconState.startAnimation() },
            "lucide_heading-6" to { heading6IconState.startAnimation() },
            "lucide_heading" to { headingIconState.startAnimation() },
            "lucide_headphone-off" to { headphoneOffIconState.startAnimation() },
            "lucide_headphones" to { headphonesIconState.startAnimation() },
            "lucide_headset" to { headsetIconState.startAnimation() },
            "lucide_heart-crack" to { heartCrackIconState.startAnimation() },
            "lucide_heart-handshake" to { heartHandshakeIconState.startAnimation() },
            "lucide_heart" to { heartIconState.startAnimation() },
            "lucide_heart-minus" to { heartMinusIconState.startAnimation() },
            "lucide_heart-off" to { heartOffIconState.startAnimation() },
            "lucide_heart-plus" to { heartPlusIconState.startAnimation() },
            "lucide_heart-pulse" to { heartPulseIconState.startAnimation() },
            "lucide_heart-x" to { heartXIconState.startAnimation() },
            "lucide_hexagon" to { hexagonIconState.startAnimation() },
            "lucide_highlighter" to { highlighterIconState.startAnimation() },
            "lucide_history" to { historyIconState.startAnimation() },
            "lucide_hospital" to { hospitalIconState.startAnimation() },
            "lucide_hotel" to { hotelIconState.startAnimation() },
            "lucide_hourglass" to { hourglassIconState.startAnimation() },
            "lucide_house-cog" to { houseCogIconState.startAnimation() },
            "lucide_house-heart" to { houseHeartIconState.startAnimation() },
            "lucide_house" to { houseIconState.startAnimation() },
            "lucide_house-plug" to { housePlugIconState.startAnimation() },
            "lucide_house-plus" to { housePlusIconState.startAnimation() },
            "lucide_house-wifi" to { houseWifiIconState.startAnimation() },
            "lucide_image" to { imageIconState.startAnimation() },
            "lucide_image-off" to { imageOffIconState.startAnimation() },
            "lucide_image-plus" to { imagePlusIconState.startAnimation() },
            "lucide_inbox" to { inboxIconState.startAnimation() },
            "lucide_indent-decrease" to { indentDecreaseIconState.startAnimation() },
            "lucide_indent-increase" to { indentIncreaseIconState.startAnimation() },
            "lucide_indian-rupee" to { indianRupeeIconState.startAnimation() },
            "lucide_infinity" to { infinityIconState.startAnimation() },
            "lucide_info" to { infoIconState.startAnimation() },
            "lucide_instagram" to { instagramIconState.startAnimation() },
            "lucide_italic" to { italicIconState.startAnimation() },
            "lucide_japanese-yen" to { japaneseYenIconState.startAnimation() },
            "lucide_kanban" to { kanbanIconState.startAnimation() },
            "lucide_key" to { keyIconState.startAnimation() },
            "lucide_key-round" to { keyRoundIconState.startAnimation() },
            "lucide_key-square" to { keySquareIconState.startAnimation() },
            "lucide_keyboard" to { keyboardIconState.startAnimation() },
            "lucide_landmark" to { landmarkIconState.startAnimation() },
            "lucide_languages" to { languagesIconState.startAnimation() },
            "lucide_laptop" to { laptopIconState.startAnimation() },
            "lucide_laptop-minimal-check" to { laptopMinimalCheckIconState.startAnimation() },
            "lucide_laptop-minimal" to { laptopMinimalIconState.startAnimation() },
            "lucide_layers" to { layersIconState.startAnimation() },
            "lucide_layout-dashboard" to { layoutDashboardIconState.startAnimation() },
            "lucide_layout-grid" to { layoutGridIconState.startAnimation() },
            "lucide_layout-list" to { layoutListIconState.startAnimation() },
            "lucide_layout-panel-left" to { layoutPanelLeftIconState.startAnimation() },
            "lucide_layout-panel-top" to { layoutPanelTopIconState.startAnimation() },
            "lucide_layout-template" to { layoutTemplateIconState.startAnimation() },
            "lucide_leaf" to { leafIconState.startAnimation() },
            "lucide_life-buoy" to { lifeBuoyIconState.startAnimation() },
            "lucide_lightbulb" to { lightbulbIconState.startAnimation() },
            "lucide_link" to { linkIconState.startAnimation() },
            "lucide_linkedin" to { linkedinIconState.startAnimation() },
            "lucide_list-check" to { listCheckIconState.startAnimation() },
            "lucide_list-checks" to { listChecksIconState.startAnimation() },
            "lucide_list-chevrons-down-up" to { listChevronsDownUpIconState.startAnimation() },
        ),
        mapOf(
            "lucide_git-fork" to { gitForkIconState.stopAnimation() },
            "lucide_git-graph" to { gitGraphIconState.stopAnimation() },
            "lucide_git-merge-conflict" to { gitMergeConflictIconState.stopAnimation() },
            "lucide_git-merge" to { gitMergeIconState.stopAnimation() },
            "lucide_git-pull-request-arrow" to { gitPullRequestArrowIconState.stopAnimation() },
            "lucide_git-pull-request-closed" to { gitPullRequestClosedIconState.stopAnimation() },
            "lucide_git-pull-request-create-arrow" to { gitPullRequestCreateArrowIconState.stopAnimation() },
            "lucide_git-pull-request-create" to { gitPullRequestCreateIconState.stopAnimation() },
            "lucide_git-pull-request-draft" to { gitPullRequestDraftIconState.stopAnimation() },
            "lucide_git-pull-request" to { gitPullRequestIconState.stopAnimation() },
            "lucide_github" to { githubIconState.stopAnimation() },
            "lucide_gitlab" to { gitlabIconState.stopAnimation() },
            "lucide_globe" to { globeIconState.stopAnimation() },
            "lucide_globe-lock" to { globeLockIconState.stopAnimation() },
            "lucide_graduation-cap" to { graduationCapIconState.stopAnimation() },
            "lucide_grid-2x2-check" to { grid2x2CheckIconState.stopAnimation() },
            "lucide_grid-2x2" to { grid2x2IconState.stopAnimation() },
            "lucide_grid-2x2-plus" to { grid2x2PlusIconState.stopAnimation() },
            "lucide_grid-2x2-x" to { grid2x2XIconState.stopAnimation() },
            "lucide_grid-3x2" to { grid3x2IconState.stopAnimation() },
            "lucide_grid-3x3" to { grid3x3IconState.stopAnimation() },
            "lucide_grip-vertical" to { gripVerticalIconState.stopAnimation() },
            "lucide_guitar" to { guitarIconState.stopAnimation() },
            "lucide_hammer" to { hammerIconState.stopAnimation() },
            "lucide_hand-coins" to { handCoinsIconState.stopAnimation() },
            "lucide_hand-heart" to { handHeartIconState.stopAnimation() },
            "lucide_hand" to { handIconState.stopAnimation() },
            "lucide_handshake" to { handshakeIconState.stopAnimation() },
            "lucide_hard-drive-download" to { hardDriveDownloadIconState.stopAnimation() },
            "lucide_hard-drive" to { hardDriveIconState.stopAnimation() },
            "lucide_hard-drive-upload" to { hardDriveUploadIconState.stopAnimation() },
            "lucide_hash" to { hashIconState.stopAnimation() },
            "lucide_haze" to { hazeIconState.stopAnimation() },
            "lucide_heading-1" to { heading1IconState.stopAnimation() },
            "lucide_heading-2" to { heading2IconState.stopAnimation() },
            "lucide_heading-3" to { heading3IconState.stopAnimation() },
            "lucide_heading-4" to { heading4IconState.stopAnimation() },
            "lucide_heading-5" to { heading5IconState.stopAnimation() },
            "lucide_heading-6" to { heading6IconState.stopAnimation() },
            "lucide_heading" to { headingIconState.stopAnimation() },
            "lucide_headphone-off" to { headphoneOffIconState.stopAnimation() },
            "lucide_headphones" to { headphonesIconState.stopAnimation() },
            "lucide_headset" to { headsetIconState.stopAnimation() },
            "lucide_heart-crack" to { heartCrackIconState.stopAnimation() },
            "lucide_heart-handshake" to { heartHandshakeIconState.stopAnimation() },
            "lucide_heart" to { heartIconState.stopAnimation() },
            "lucide_heart-minus" to { heartMinusIconState.stopAnimation() },
            "lucide_heart-off" to { heartOffIconState.stopAnimation() },
            "lucide_heart-plus" to { heartPlusIconState.stopAnimation() },
            "lucide_heart-pulse" to { heartPulseIconState.stopAnimation() },
            "lucide_heart-x" to { heartXIconState.stopAnimation() },
            "lucide_hexagon" to { hexagonIconState.stopAnimation() },
            "lucide_highlighter" to { highlighterIconState.stopAnimation() },
            "lucide_history" to { historyIconState.stopAnimation() },
            "lucide_hospital" to { hospitalIconState.stopAnimation() },
            "lucide_hotel" to { hotelIconState.stopAnimation() },
            "lucide_hourglass" to { hourglassIconState.stopAnimation() },
            "lucide_house-cog" to { houseCogIconState.stopAnimation() },
            "lucide_house-heart" to { houseHeartIconState.stopAnimation() },
            "lucide_house" to { houseIconState.stopAnimation() },
            "lucide_house-plug" to { housePlugIconState.stopAnimation() },
            "lucide_house-plus" to { housePlusIconState.stopAnimation() },
            "lucide_house-wifi" to { houseWifiIconState.stopAnimation() },
            "lucide_image" to { imageIconState.stopAnimation() },
            "lucide_image-off" to { imageOffIconState.stopAnimation() },
            "lucide_image-plus" to { imagePlusIconState.stopAnimation() },
            "lucide_inbox" to { inboxIconState.stopAnimation() },
            "lucide_indent-decrease" to { indentDecreaseIconState.stopAnimation() },
            "lucide_indent-increase" to { indentIncreaseIconState.stopAnimation() },
            "lucide_indian-rupee" to { indianRupeeIconState.stopAnimation() },
            "lucide_infinity" to { infinityIconState.stopAnimation() },
            "lucide_info" to { infoIconState.stopAnimation() },
            "lucide_instagram" to { instagramIconState.stopAnimation() },
            "lucide_italic" to { italicIconState.stopAnimation() },
            "lucide_japanese-yen" to { japaneseYenIconState.stopAnimation() },
            "lucide_kanban" to { kanbanIconState.stopAnimation() },
            "lucide_key" to { keyIconState.stopAnimation() },
            "lucide_key-round" to { keyRoundIconState.stopAnimation() },
            "lucide_key-square" to { keySquareIconState.stopAnimation() },
            "lucide_keyboard" to { keyboardIconState.stopAnimation() },
            "lucide_landmark" to { landmarkIconState.stopAnimation() },
            "lucide_languages" to { languagesIconState.stopAnimation() },
            "lucide_laptop" to { laptopIconState.stopAnimation() },
            "lucide_laptop-minimal-check" to { laptopMinimalCheckIconState.stopAnimation() },
            "lucide_laptop-minimal" to { laptopMinimalIconState.stopAnimation() },
            "lucide_layers" to { layersIconState.stopAnimation() },
            "lucide_layout-dashboard" to { layoutDashboardIconState.stopAnimation() },
            "lucide_layout-grid" to { layoutGridIconState.stopAnimation() },
            "lucide_layout-list" to { layoutListIconState.stopAnimation() },
            "lucide_layout-panel-left" to { layoutPanelLeftIconState.stopAnimation() },
            "lucide_layout-panel-top" to { layoutPanelTopIconState.stopAnimation() },
            "lucide_layout-template" to { layoutTemplateIconState.stopAnimation() },
            "lucide_leaf" to { leafIconState.stopAnimation() },
            "lucide_life-buoy" to { lifeBuoyIconState.stopAnimation() },
            "lucide_lightbulb" to { lightbulbIconState.stopAnimation() },
            "lucide_link" to { linkIconState.stopAnimation() },
            "lucide_linkedin" to { linkedinIconState.stopAnimation() },
            "lucide_list-check" to { listCheckIconState.stopAnimation() },
            "lucide_list-checks" to { listChecksIconState.stopAnimation() },
            "lucide_list-chevrons-down-up" to { listChevronsDownUpIconState.stopAnimation() },
        ),
    )
}

@Composable
private fun rememberLucideStatesChunk5(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val listChevronsUpDownIconState = rememberListChevronsUpDownIconState()
    val listCollapseIconState = rememberListCollapseIconState()
    val listEndIconState = rememberListEndIconState()
    val listFilterIconState = rememberListFilterIconState()
    val listFilterPlusIconState = rememberListFilterPlusIconState()
    val listIconState = rememberListIconState()
    val listMinusIconState = rememberListMinusIconState()
    val listOrderedIconState = rememberListOrderedIconState()
    val listPlusIconState = rememberListPlusIconState()
    val listStartIconState = rememberListStartIconState()
    val listTodoIconState = rememberListTodoIconState()
    val listTreeIconState = rememberListTreeIconState()
    val listXIconState = rememberListXIconState()
    val loaderCircleIconState = rememberLoaderCircleIconState()
    val loaderIconState = rememberLoaderIconState()
    val locateFixedIconState = rememberLocateFixedIconState()
    val locateIconState = rememberLocateIconState()
    val locateOffIconState = rememberLocateOffIconState()
    val lockIconState = rememberLockIconState()
    val lockKeyholeIconState = rememberLockKeyholeIconState()
    val lockKeyholeOpenIconState = rememberLockKeyholeOpenIconState()
    val lockOpenIconState = rememberLockOpenIconState()
    val logInIconState = rememberLogInIconState()
    val logOutIconState = rememberLogOutIconState()
    val magnetIconState = rememberMagnetIconState()
    val mailCheckIconState = rememberMailCheckIconState()
    val mailClockIconState = rememberMailClockIconState()
    val mailIconState = rememberMailIconState()
    val mailMinusIconState = rememberMailMinusIconState()
    val mailOpenIconState = rememberMailOpenIconState()
    val mailPlusIconState = rememberMailPlusIconState()
    val mailSearchIconState = rememberMailSearchIconState()
    val mailWarningIconState = rememberMailWarningIconState()
    val mailXIconState = rememberMailXIconState()
    val mailboxIconState = rememberMailboxIconState()
    val mailsIconState = rememberMailsIconState()
    val mapIconState = rememberMapIconState()
    val mapMinusIconState = rememberMapMinusIconState()
    val mapPinCheckIconState = rememberMapPinCheckIconState()
    val mapPinCheckInsideIconState = rememberMapPinCheckInsideIconState()
    val mapPinHouseIconState = rememberMapPinHouseIconState()
    val mapPinIconState = rememberMapPinIconState()
    val mapPinMinusIconState = rememberMapPinMinusIconState()
    val mapPinMinusInsideIconState = rememberMapPinMinusInsideIconState()
    val mapPinOffIconState = rememberMapPinOffIconState()
    val mapPinPenIconState = rememberMapPinPenIconState()
    val mapPinPlusIconState = rememberMapPinPlusIconState()
    val mapPinPlusInsideIconState = rememberMapPinPlusInsideIconState()
    val mapPinSearchIconState = rememberMapPinSearchIconState()
    val mapPinXIconState = rememberMapPinXIconState()
    val mapPinXInsideIconState = rememberMapPinXInsideIconState()
    val mapPinnedIconState = rememberMapPinnedIconState()
    val mapPlusIconState = rememberMapPlusIconState()
    val marsIconState = rememberMarsIconState()
    val maximize2IconState = rememberMaximize2IconState()
    val maximizeIconState = rememberMaximizeIconState()
    val medalIconState = rememberMedalIconState()
    val megaphoneIconState = rememberMegaphoneIconState()
    val memoryStickIconState = rememberMemoryStickIconState()
    val menuIconState = rememberMenuIconState()
    val messageCircleCheckIconState = rememberMessageCircleCheckIconState()
    val messageCircleCodeIconState = rememberMessageCircleCodeIconState()
    val messageCircleDashedCheckIconState = rememberMessageCircleDashedCheckIconState()
    val messageCircleDashedIconState = rememberMessageCircleDashedIconState()
    val messageCircleHeartIconState = rememberMessageCircleHeartIconState()
    val messageCircleIconState = rememberMessageCircleIconState()
    val messageCircleMoreIconState = rememberMessageCircleMoreIconState()
    val messageCircleOffIconState = rememberMessageCircleOffIconState()
    val messageCirclePlusIconState = rememberMessageCirclePlusIconState()
    val messageCircleQuestionMarkIconState = rememberMessageCircleQuestionMarkIconState()
    val messageCircleReplyIconState = rememberMessageCircleReplyIconState()
    val messageCircleWarningIconState = rememberMessageCircleWarningIconState()
    val messageCircleXIconState = rememberMessageCircleXIconState()
    val messageSquareCheckIconState = rememberMessageSquareCheckIconState()
    val messageSquareCodeIconState = rememberMessageSquareCodeIconState()
    val messageSquareDashedIconState = rememberMessageSquareDashedIconState()
    val messageSquareDiffIconState = rememberMessageSquareDiffIconState()
    val messageSquareDotIconState = rememberMessageSquareDotIconState()
    val messageSquareHeartIconState = rememberMessageSquareHeartIconState()
    val messageSquareIconState = rememberMessageSquareIconState()
    val messageSquareLockIconState = rememberMessageSquareLockIconState()
    val messageSquareMoreIconState = rememberMessageSquareMoreIconState()
    val messageSquareOffIconState = rememberMessageSquareOffIconState()
    val messageSquarePlusIconState = rememberMessageSquarePlusIconState()
    val messageSquareQuoteIconState = rememberMessageSquareQuoteIconState()
    val messageSquareReplyIconState = rememberMessageSquareReplyIconState()
    val messageSquareShareIconState = rememberMessageSquareShareIconState()
    val messageSquareTextIconState = rememberMessageSquareTextIconState()
    val messageSquareWarningIconState = rememberMessageSquareWarningIconState()
    val messageSquareXIconState = rememberMessageSquareXIconState()
    val messagesCircleIconState = rememberMessagesCircleIconState()
    val messagesSquareIconState = rememberMessagesSquareIconState()
    val micAudioLinesIconState = rememberMicAudioLinesIconState()
    val micIconState = rememberMicIconState()
    val micOffIconState = rememberMicOffIconState()
    val micSignalIconState = rememberMicSignalIconState()
    val micVocalIconState = rememberMicVocalIconState()
    val microchipIconState = rememberMicrochipIconState()
    val milestoneIconState = rememberMilestoneIconState()
    val minimize2IconState = rememberMinimize2IconState()
    return Triple(
        mapOf(
            "lucide_list-chevrons-up-down" to listChevronsUpDownIconState,
            "lucide_list-collapse" to listCollapseIconState,
            "lucide_list-end" to listEndIconState,
            "lucide_list-filter" to listFilterIconState,
            "lucide_list-filter-plus" to listFilterPlusIconState,
            "lucide_list" to listIconState,
            "lucide_list-minus" to listMinusIconState,
            "lucide_list-ordered" to listOrderedIconState,
            "lucide_list-plus" to listPlusIconState,
            "lucide_list-start" to listStartIconState,
            "lucide_list-todo" to listTodoIconState,
            "lucide_list-tree" to listTreeIconState,
            "lucide_list-x" to listXIconState,
            "lucide_loader-circle" to loaderCircleIconState,
            "lucide_loader" to loaderIconState,
            "lucide_locate-fixed" to locateFixedIconState,
            "lucide_locate" to locateIconState,
            "lucide_locate-off" to locateOffIconState,
            "lucide_lock" to lockIconState,
            "lucide_lock-keyhole" to lockKeyholeIconState,
            "lucide_lock-keyhole-open" to lockKeyholeOpenIconState,
            "lucide_lock-open" to lockOpenIconState,
            "lucide_log-in" to logInIconState,
            "lucide_log-out" to logOutIconState,
            "lucide_magnet" to magnetIconState,
            "lucide_mail-check" to mailCheckIconState,
            "lucide_mail-clock" to mailClockIconState,
            "lucide_mail" to mailIconState,
            "lucide_mail-minus" to mailMinusIconState,
            "lucide_mail-open" to mailOpenIconState,
            "lucide_mail-plus" to mailPlusIconState,
            "lucide_mail-search" to mailSearchIconState,
            "lucide_mail-warning" to mailWarningIconState,
            "lucide_mail-x" to mailXIconState,
            "lucide_mailbox" to mailboxIconState,
            "lucide_mails" to mailsIconState,
            "lucide_map" to mapIconState,
            "lucide_map-minus" to mapMinusIconState,
            "lucide_map-pin-check" to mapPinCheckIconState,
            "lucide_map-pin-check-inside" to mapPinCheckInsideIconState,
            "lucide_map-pin-house" to mapPinHouseIconState,
            "lucide_map-pin" to mapPinIconState,
            "lucide_map-pin-minus" to mapPinMinusIconState,
            "lucide_map-pin-minus-inside" to mapPinMinusInsideIconState,
            "lucide_map-pin-off" to mapPinOffIconState,
            "lucide_map-pin-pen" to mapPinPenIconState,
            "lucide_map-pin-plus" to mapPinPlusIconState,
            "lucide_map-pin-plus-inside" to mapPinPlusInsideIconState,
            "lucide_map-pin-search" to mapPinSearchIconState,
            "lucide_map-pin-x" to mapPinXIconState,
            "lucide_map-pin-x-inside" to mapPinXInsideIconState,
            "lucide_map-pinned" to mapPinnedIconState,
            "lucide_map-plus" to mapPlusIconState,
            "lucide_mars" to marsIconState,
            "lucide_maximize-2" to maximize2IconState,
            "lucide_maximize" to maximizeIconState,
            "lucide_medal" to medalIconState,
            "lucide_megaphone" to megaphoneIconState,
            "lucide_memory-stick" to memoryStickIconState,
            "lucide_menu" to menuIconState,
            "lucide_message-circle-check" to messageCircleCheckIconState,
            "lucide_message-circle-code" to messageCircleCodeIconState,
            "lucide_message-circle-dashed-check" to messageCircleDashedCheckIconState,
            "lucide_message-circle-dashed" to messageCircleDashedIconState,
            "lucide_message-circle-heart" to messageCircleHeartIconState,
            "lucide_message-circle" to messageCircleIconState,
            "lucide_message-circle-more" to messageCircleMoreIconState,
            "lucide_message-circle-off" to messageCircleOffIconState,
            "lucide_message-circle-plus" to messageCirclePlusIconState,
            "lucide_message-circle-question-mark" to messageCircleQuestionMarkIconState,
            "lucide_message-circle-reply" to messageCircleReplyIconState,
            "lucide_message-circle-warning" to messageCircleWarningIconState,
            "lucide_message-circle-x" to messageCircleXIconState,
            "lucide_message-square-check" to messageSquareCheckIconState,
            "lucide_message-square-code" to messageSquareCodeIconState,
            "lucide_message-square-dashed" to messageSquareDashedIconState,
            "lucide_message-square-diff" to messageSquareDiffIconState,
            "lucide_message-square-dot" to messageSquareDotIconState,
            "lucide_message-square-heart" to messageSquareHeartIconState,
            "lucide_message-square" to messageSquareIconState,
            "lucide_message-square-lock" to messageSquareLockIconState,
            "lucide_message-square-more" to messageSquareMoreIconState,
            "lucide_message-square-off" to messageSquareOffIconState,
            "lucide_message-square-plus" to messageSquarePlusIconState,
            "lucide_message-square-quote" to messageSquareQuoteIconState,
            "lucide_message-square-reply" to messageSquareReplyIconState,
            "lucide_message-square-share" to messageSquareShareIconState,
            "lucide_message-square-text" to messageSquareTextIconState,
            "lucide_message-square-warning" to messageSquareWarningIconState,
            "lucide_message-square-x" to messageSquareXIconState,
            "lucide_messages-circle" to messagesCircleIconState,
            "lucide_messages-square" to messagesSquareIconState,
            "lucide_mic-audio-lines" to micAudioLinesIconState,
            "lucide_mic" to micIconState,
            "lucide_mic-off" to micOffIconState,
            "lucide_mic-signal" to micSignalIconState,
            "lucide_mic-vocal" to micVocalIconState,
            "lucide_microchip" to microchipIconState,
            "lucide_milestone" to milestoneIconState,
            "lucide_minimize-2" to minimize2IconState,
        ),
        mapOf(
            "lucide_list-chevrons-up-down" to { listChevronsUpDownIconState.startAnimation() },
            "lucide_list-collapse" to { listCollapseIconState.startAnimation() },
            "lucide_list-end" to { listEndIconState.startAnimation() },
            "lucide_list-filter" to { listFilterIconState.startAnimation() },
            "lucide_list-filter-plus" to { listFilterPlusIconState.startAnimation() },
            "lucide_list" to { listIconState.startAnimation() },
            "lucide_list-minus" to { listMinusIconState.startAnimation() },
            "lucide_list-ordered" to { listOrderedIconState.startAnimation() },
            "lucide_list-plus" to { listPlusIconState.startAnimation() },
            "lucide_list-start" to { listStartIconState.startAnimation() },
            "lucide_list-todo" to { listTodoIconState.startAnimation() },
            "lucide_list-tree" to { listTreeIconState.startAnimation() },
            "lucide_list-x" to { listXIconState.startAnimation() },
            "lucide_loader-circle" to { loaderCircleIconState.startAnimation() },
            "lucide_loader" to { loaderIconState.startAnimation() },
            "lucide_locate-fixed" to { locateFixedIconState.startAnimation() },
            "lucide_locate" to { locateIconState.startAnimation() },
            "lucide_locate-off" to { locateOffIconState.startAnimation() },
            "lucide_lock" to { lockIconState.startAnimation() },
            "lucide_lock-keyhole" to { lockKeyholeIconState.startAnimation() },
            "lucide_lock-keyhole-open" to { lockKeyholeOpenIconState.startAnimation() },
            "lucide_lock-open" to { lockOpenIconState.startAnimation() },
            "lucide_log-in" to { logInIconState.startAnimation() },
            "lucide_log-out" to { logOutIconState.startAnimation() },
            "lucide_magnet" to { magnetIconState.startAnimation() },
            "lucide_mail-check" to { mailCheckIconState.startAnimation() },
            "lucide_mail-clock" to { mailClockIconState.startAnimation() },
            "lucide_mail" to { mailIconState.startAnimation() },
            "lucide_mail-minus" to { mailMinusIconState.startAnimation() },
            "lucide_mail-open" to { mailOpenIconState.startAnimation() },
            "lucide_mail-plus" to { mailPlusIconState.startAnimation() },
            "lucide_mail-search" to { mailSearchIconState.startAnimation() },
            "lucide_mail-warning" to { mailWarningIconState.startAnimation() },
            "lucide_mail-x" to { mailXIconState.startAnimation() },
            "lucide_mailbox" to { mailboxIconState.startAnimation() },
            "lucide_mails" to { mailsIconState.startAnimation() },
            "lucide_map" to { mapIconState.startAnimation() },
            "lucide_map-minus" to { mapMinusIconState.startAnimation() },
            "lucide_map-pin-check" to { mapPinCheckIconState.startAnimation() },
            "lucide_map-pin-check-inside" to { mapPinCheckInsideIconState.startAnimation() },
            "lucide_map-pin-house" to { mapPinHouseIconState.startAnimation() },
            "lucide_map-pin" to { mapPinIconState.startAnimation() },
            "lucide_map-pin-minus" to { mapPinMinusIconState.startAnimation() },
            "lucide_map-pin-minus-inside" to { mapPinMinusInsideIconState.startAnimation() },
            "lucide_map-pin-off" to { mapPinOffIconState.startAnimation() },
            "lucide_map-pin-pen" to { mapPinPenIconState.startAnimation() },
            "lucide_map-pin-plus" to { mapPinPlusIconState.startAnimation() },
            "lucide_map-pin-plus-inside" to { mapPinPlusInsideIconState.startAnimation() },
            "lucide_map-pin-search" to { mapPinSearchIconState.startAnimation() },
            "lucide_map-pin-x" to { mapPinXIconState.startAnimation() },
            "lucide_map-pin-x-inside" to { mapPinXInsideIconState.startAnimation() },
            "lucide_map-pinned" to { mapPinnedIconState.startAnimation() },
            "lucide_map-plus" to { mapPlusIconState.startAnimation() },
            "lucide_mars" to { marsIconState.startAnimation() },
            "lucide_maximize-2" to { maximize2IconState.startAnimation() },
            "lucide_maximize" to { maximizeIconState.startAnimation() },
            "lucide_medal" to { medalIconState.startAnimation() },
            "lucide_megaphone" to { megaphoneIconState.startAnimation() },
            "lucide_memory-stick" to { memoryStickIconState.startAnimation() },
            "lucide_menu" to { menuIconState.startAnimation() },
            "lucide_message-circle-check" to { messageCircleCheckIconState.startAnimation() },
            "lucide_message-circle-code" to { messageCircleCodeIconState.startAnimation() },
            "lucide_message-circle-dashed-check" to { messageCircleDashedCheckIconState.startAnimation() },
            "lucide_message-circle-dashed" to { messageCircleDashedIconState.startAnimation() },
            "lucide_message-circle-heart" to { messageCircleHeartIconState.startAnimation() },
            "lucide_message-circle" to { messageCircleIconState.startAnimation() },
            "lucide_message-circle-more" to { messageCircleMoreIconState.startAnimation() },
            "lucide_message-circle-off" to { messageCircleOffIconState.startAnimation() },
            "lucide_message-circle-plus" to { messageCirclePlusIconState.startAnimation() },
            "lucide_message-circle-question-mark" to { messageCircleQuestionMarkIconState.startAnimation() },
            "lucide_message-circle-reply" to { messageCircleReplyIconState.startAnimation() },
            "lucide_message-circle-warning" to { messageCircleWarningIconState.startAnimation() },
            "lucide_message-circle-x" to { messageCircleXIconState.startAnimation() },
            "lucide_message-square-check" to { messageSquareCheckIconState.startAnimation() },
            "lucide_message-square-code" to { messageSquareCodeIconState.startAnimation() },
            "lucide_message-square-dashed" to { messageSquareDashedIconState.startAnimation() },
            "lucide_message-square-diff" to { messageSquareDiffIconState.startAnimation() },
            "lucide_message-square-dot" to { messageSquareDotIconState.startAnimation() },
            "lucide_message-square-heart" to { messageSquareHeartIconState.startAnimation() },
            "lucide_message-square" to { messageSquareIconState.startAnimation() },
            "lucide_message-square-lock" to { messageSquareLockIconState.startAnimation() },
            "lucide_message-square-more" to { messageSquareMoreIconState.startAnimation() },
            "lucide_message-square-off" to { messageSquareOffIconState.startAnimation() },
            "lucide_message-square-plus" to { messageSquarePlusIconState.startAnimation() },
            "lucide_message-square-quote" to { messageSquareQuoteIconState.startAnimation() },
            "lucide_message-square-reply" to { messageSquareReplyIconState.startAnimation() },
            "lucide_message-square-share" to { messageSquareShareIconState.startAnimation() },
            "lucide_message-square-text" to { messageSquareTextIconState.startAnimation() },
            "lucide_message-square-warning" to { messageSquareWarningIconState.startAnimation() },
            "lucide_message-square-x" to { messageSquareXIconState.startAnimation() },
            "lucide_messages-circle" to { messagesCircleIconState.startAnimation() },
            "lucide_messages-square" to { messagesSquareIconState.startAnimation() },
            "lucide_mic-audio-lines" to { micAudioLinesIconState.startAnimation() },
            "lucide_mic" to { micIconState.startAnimation() },
            "lucide_mic-off" to { micOffIconState.startAnimation() },
            "lucide_mic-signal" to { micSignalIconState.startAnimation() },
            "lucide_mic-vocal" to { micVocalIconState.startAnimation() },
            "lucide_microchip" to { microchipIconState.startAnimation() },
            "lucide_milestone" to { milestoneIconState.startAnimation() },
            "lucide_minimize-2" to { minimize2IconState.startAnimation() },
        ),
        mapOf(
            "lucide_list-chevrons-up-down" to { listChevronsUpDownIconState.stopAnimation() },
            "lucide_list-collapse" to { listCollapseIconState.stopAnimation() },
            "lucide_list-end" to { listEndIconState.stopAnimation() },
            "lucide_list-filter" to { listFilterIconState.stopAnimation() },
            "lucide_list-filter-plus" to { listFilterPlusIconState.stopAnimation() },
            "lucide_list" to { listIconState.stopAnimation() },
            "lucide_list-minus" to { listMinusIconState.stopAnimation() },
            "lucide_list-ordered" to { listOrderedIconState.stopAnimation() },
            "lucide_list-plus" to { listPlusIconState.stopAnimation() },
            "lucide_list-start" to { listStartIconState.stopAnimation() },
            "lucide_list-todo" to { listTodoIconState.stopAnimation() },
            "lucide_list-tree" to { listTreeIconState.stopAnimation() },
            "lucide_list-x" to { listXIconState.stopAnimation() },
            "lucide_loader-circle" to { loaderCircleIconState.stopAnimation() },
            "lucide_loader" to { loaderIconState.stopAnimation() },
            "lucide_locate-fixed" to { locateFixedIconState.stopAnimation() },
            "lucide_locate" to { locateIconState.stopAnimation() },
            "lucide_locate-off" to { locateOffIconState.stopAnimation() },
            "lucide_lock" to { lockIconState.stopAnimation() },
            "lucide_lock-keyhole" to { lockKeyholeIconState.stopAnimation() },
            "lucide_lock-keyhole-open" to { lockKeyholeOpenIconState.stopAnimation() },
            "lucide_lock-open" to { lockOpenIconState.stopAnimation() },
            "lucide_log-in" to { logInIconState.stopAnimation() },
            "lucide_log-out" to { logOutIconState.stopAnimation() },
            "lucide_magnet" to { magnetIconState.stopAnimation() },
            "lucide_mail-check" to { mailCheckIconState.stopAnimation() },
            "lucide_mail-clock" to { mailClockIconState.stopAnimation() },
            "lucide_mail" to { mailIconState.stopAnimation() },
            "lucide_mail-minus" to { mailMinusIconState.stopAnimation() },
            "lucide_mail-open" to { mailOpenIconState.stopAnimation() },
            "lucide_mail-plus" to { mailPlusIconState.stopAnimation() },
            "lucide_mail-search" to { mailSearchIconState.stopAnimation() },
            "lucide_mail-warning" to { mailWarningIconState.stopAnimation() },
            "lucide_mail-x" to { mailXIconState.stopAnimation() },
            "lucide_mailbox" to { mailboxIconState.stopAnimation() },
            "lucide_mails" to { mailsIconState.stopAnimation() },
            "lucide_map" to { mapIconState.stopAnimation() },
            "lucide_map-minus" to { mapMinusIconState.stopAnimation() },
            "lucide_map-pin-check" to { mapPinCheckIconState.stopAnimation() },
            "lucide_map-pin-check-inside" to { mapPinCheckInsideIconState.stopAnimation() },
            "lucide_map-pin-house" to { mapPinHouseIconState.stopAnimation() },
            "lucide_map-pin" to { mapPinIconState.stopAnimation() },
            "lucide_map-pin-minus" to { mapPinMinusIconState.stopAnimation() },
            "lucide_map-pin-minus-inside" to { mapPinMinusInsideIconState.stopAnimation() },
            "lucide_map-pin-off" to { mapPinOffIconState.stopAnimation() },
            "lucide_map-pin-pen" to { mapPinPenIconState.stopAnimation() },
            "lucide_map-pin-plus" to { mapPinPlusIconState.stopAnimation() },
            "lucide_map-pin-plus-inside" to { mapPinPlusInsideIconState.stopAnimation() },
            "lucide_map-pin-search" to { mapPinSearchIconState.stopAnimation() },
            "lucide_map-pin-x" to { mapPinXIconState.stopAnimation() },
            "lucide_map-pin-x-inside" to { mapPinXInsideIconState.stopAnimation() },
            "lucide_map-pinned" to { mapPinnedIconState.stopAnimation() },
            "lucide_map-plus" to { mapPlusIconState.stopAnimation() },
            "lucide_mars" to { marsIconState.stopAnimation() },
            "lucide_maximize-2" to { maximize2IconState.stopAnimation() },
            "lucide_maximize" to { maximizeIconState.stopAnimation() },
            "lucide_medal" to { medalIconState.stopAnimation() },
            "lucide_megaphone" to { megaphoneIconState.stopAnimation() },
            "lucide_memory-stick" to { memoryStickIconState.stopAnimation() },
            "lucide_menu" to { menuIconState.stopAnimation() },
            "lucide_message-circle-check" to { messageCircleCheckIconState.stopAnimation() },
            "lucide_message-circle-code" to { messageCircleCodeIconState.stopAnimation() },
            "lucide_message-circle-dashed-check" to { messageCircleDashedCheckIconState.stopAnimation() },
            "lucide_message-circle-dashed" to { messageCircleDashedIconState.stopAnimation() },
            "lucide_message-circle-heart" to { messageCircleHeartIconState.stopAnimation() },
            "lucide_message-circle" to { messageCircleIconState.stopAnimation() },
            "lucide_message-circle-more" to { messageCircleMoreIconState.stopAnimation() },
            "lucide_message-circle-off" to { messageCircleOffIconState.stopAnimation() },
            "lucide_message-circle-plus" to { messageCirclePlusIconState.stopAnimation() },
            "lucide_message-circle-question-mark" to { messageCircleQuestionMarkIconState.stopAnimation() },
            "lucide_message-circle-reply" to { messageCircleReplyIconState.stopAnimation() },
            "lucide_message-circle-warning" to { messageCircleWarningIconState.stopAnimation() },
            "lucide_message-circle-x" to { messageCircleXIconState.stopAnimation() },
            "lucide_message-square-check" to { messageSquareCheckIconState.stopAnimation() },
            "lucide_message-square-code" to { messageSquareCodeIconState.stopAnimation() },
            "lucide_message-square-dashed" to { messageSquareDashedIconState.stopAnimation() },
            "lucide_message-square-diff" to { messageSquareDiffIconState.stopAnimation() },
            "lucide_message-square-dot" to { messageSquareDotIconState.stopAnimation() },
            "lucide_message-square-heart" to { messageSquareHeartIconState.stopAnimation() },
            "lucide_message-square" to { messageSquareIconState.stopAnimation() },
            "lucide_message-square-lock" to { messageSquareLockIconState.stopAnimation() },
            "lucide_message-square-more" to { messageSquareMoreIconState.stopAnimation() },
            "lucide_message-square-off" to { messageSquareOffIconState.stopAnimation() },
            "lucide_message-square-plus" to { messageSquarePlusIconState.stopAnimation() },
            "lucide_message-square-quote" to { messageSquareQuoteIconState.stopAnimation() },
            "lucide_message-square-reply" to { messageSquareReplyIconState.stopAnimation() },
            "lucide_message-square-share" to { messageSquareShareIconState.stopAnimation() },
            "lucide_message-square-text" to { messageSquareTextIconState.stopAnimation() },
            "lucide_message-square-warning" to { messageSquareWarningIconState.stopAnimation() },
            "lucide_message-square-x" to { messageSquareXIconState.stopAnimation() },
            "lucide_messages-circle" to { messagesCircleIconState.stopAnimation() },
            "lucide_messages-square" to { messagesSquareIconState.stopAnimation() },
            "lucide_mic-audio-lines" to { micAudioLinesIconState.stopAnimation() },
            "lucide_mic" to { micIconState.stopAnimation() },
            "lucide_mic-off" to { micOffIconState.stopAnimation() },
            "lucide_mic-signal" to { micSignalIconState.stopAnimation() },
            "lucide_mic-vocal" to { micVocalIconState.stopAnimation() },
            "lucide_microchip" to { microchipIconState.stopAnimation() },
            "lucide_milestone" to { milestoneIconState.stopAnimation() },
            "lucide_minimize-2" to { minimize2IconState.stopAnimation() },
        ),
    )
}

@Composable
private fun rememberLucideStatesChunk6(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val minimizeIconState = rememberMinimizeIconState()
    val minusIconState = rememberMinusIconState()
    val monitorCheckIconState = rememberMonitorCheckIconState()
    val monitorCloudIconState = rememberMonitorCloudIconState()
    val monitorCogIconState = rememberMonitorCogIconState()
    val monitorDotIconState = rememberMonitorDotIconState()
    val monitorDownIconState = rememberMonitorDownIconState()
    val monitorIconState = rememberMonitorIconState()
    val monitorOffIconState = rememberMonitorOffIconState()
    val monitorPauseIconState = rememberMonitorPauseIconState()
    val monitorPcIconState = rememberMonitorPcIconState()
    val monitorPlayIconState = rememberMonitorPlayIconState()
    val monitorSmartphoneIconState = rememberMonitorSmartphoneIconState()
    val monitorSpeakerIconState = rememberMonitorSpeakerIconState()
    val monitorStopIconState = rememberMonitorStopIconState()
    val monitorUpIconState = rememberMonitorUpIconState()
    val monitorXIconState = rememberMonitorXIconState()
    val moonIconState = rememberMoonIconState()
    val moonStarIconState = rememberMoonStarIconState()
    val mountainIconState = rememberMountainIconState()
    val mouseIconState = rememberMouseIconState()
    val mousePointerClickIconState = rememberMousePointerClickIconState()
    val mousePointerIconState = rememberMousePointerIconState()
    val moveDiagonal2IconState = rememberMoveDiagonal2IconState()
    val moveDiagonalIconState = rememberMoveDiagonalIconState()
    val moveDownIconState = rememberMoveDownIconState()
    val moveDownLeftIconState = rememberMoveDownLeftIconState()
    val moveDownRightIconState = rememberMoveDownRightIconState()
    val moveHorizontalIconState = rememberMoveHorizontalIconState()
    val moveIconState = rememberMoveIconState()
    val moveLeftIconState = rememberMoveLeftIconState()
    val moveRightIconState = rememberMoveRightIconState()
    val moveUpIconState = rememberMoveUpIconState()
    val moveUpLeftIconState = rememberMoveUpLeftIconState()
    val moveUpRightIconState = rememberMoveUpRightIconState()
    val moveVerticalIconState = rememberMoveVerticalIconState()
    val music2IconState = rememberMusic2IconState()
    val music3IconState = rememberMusic3IconState()
    val music4IconState = rememberMusic4IconState()
    val musicIconState = rememberMusicIconState()
    val navigation2IconState = rememberNavigation2IconState()
    val navigationIconState = rememberNavigationIconState()
    val networkIconState = rememberNetworkIconState()
    val nfcIconState = rememberNfcIconState()
    val notebookPenIconState = rememberNotebookPenIconState()
    val octagonAlertIconState = rememberOctagonAlertIconState()
    val octagonIconState = rememberOctagonIconState()
    val octagonMinusIconState = rememberOctagonMinusIconState()
    val octagonPauseIconState = rememberOctagonPauseIconState()
    val octagonXIconState = rememberOctagonXIconState()
    val orbitIconState = rememberOrbitIconState()
    val packageCheckIconState = rememberPackageCheckIconState()
    val packageIconState = rememberPackageIconState()
    val packageOpenIconState = rememberPackageOpenIconState()
    val packagePlusIconState = rememberPackagePlusIconState()
    val packageSearchIconState = rememberPackageSearchIconState()
    val packageXIconState = rememberPackageXIconState()
    val paintBucketIconState = rememberPaintBucketIconState()
    val paintbrushIconState = rememberPaintbrushIconState()
    val paletteIconState = rememberPaletteIconState()
    val panelBottomCloseIconState = rememberPanelBottomCloseIconState()
    val panelBottomDashedIconState = rememberPanelBottomDashedIconState()
    val panelBottomIconState = rememberPanelBottomIconState()
    val panelBottomOpenIconState = rememberPanelBottomOpenIconState()
    val panelLeftCloseIconState = rememberPanelLeftCloseIconState()
    val panelLeftDashedIconState = rememberPanelLeftDashedIconState()
    val panelLeftIconState = rememberPanelLeftIconState()
    val panelLeftOpenIconState = rememberPanelLeftOpenIconState()
    val panelLeftRightDashedIconState = rememberPanelLeftRightDashedIconState()
    val panelRightCloseIconState = rememberPanelRightCloseIconState()
    val panelRightDashedIconState = rememberPanelRightDashedIconState()
    val panelRightIconState = rememberPanelRightIconState()
    val panelRightOpenIconState = rememberPanelRightOpenIconState()
    val panelTopBottomDashedIconState = rememberPanelTopBottomDashedIconState()
    val panelTopCloseIconState = rememberPanelTopCloseIconState()
    val panelTopDashedIconState = rememberPanelTopDashedIconState()
    val panelTopIconState = rememberPanelTopIconState()
    val panelTopOpenIconState = rememberPanelTopOpenIconState()
    val paperclipIconState = rememberPaperclipIconState()
    val partyPopperIconState = rememberPartyPopperIconState()
    val lucidePauseIconState = rememberLucidePauseIconState()
    val pawPrintIconState = rememberPawPrintIconState()
    val penToolIconState = rememberPenToolIconState()
    val pencilIconState = rememberPencilIconState()
    val pentagonIconState = rememberPentagonIconState()
    val percentIconState = rememberPercentIconState()
    val philippinePesoIconState = rememberPhilippinePesoIconState()
    val phoneCallIconState = rememberPhoneCallIconState()
    val phoneForwardedIconState = rememberPhoneForwardedIconState()
    val phoneIconState = rememberPhoneIconState()
    val phoneIncomingIconState = rememberPhoneIncomingIconState()
    val phoneMissedIconState = rememberPhoneMissedIconState()
    val phoneOffIconState = rememberPhoneOffIconState()
    val pianoIconState = rememberPianoIconState()
    val pictureInPictureIconState = rememberPictureInPictureIconState()
    val piggyBankIconState = rememberPiggyBankIconState()
    val pilcrowIconState = rememberPilcrowIconState()
    val pilcrowLeftIconState = rememberPilcrowLeftIconState()
    val pilcrowRightIconState = rememberPilcrowRightIconState()
    val pillIconState = rememberPillIconState()
    return Triple(
        mapOf(
            "lucide_minimize" to minimizeIconState,
            "lucide_minus" to minusIconState,
            "lucide_monitor-check" to monitorCheckIconState,
            "lucide_monitor-cloud" to monitorCloudIconState,
            "lucide_monitor-cog" to monitorCogIconState,
            "lucide_monitor-dot" to monitorDotIconState,
            "lucide_monitor-down" to monitorDownIconState,
            "lucide_monitor" to monitorIconState,
            "lucide_monitor-off" to monitorOffIconState,
            "lucide_monitor-pause" to monitorPauseIconState,
            "lucide_monitor-pc" to monitorPcIconState,
            "lucide_monitor-play" to monitorPlayIconState,
            "lucide_monitor-smartphone" to monitorSmartphoneIconState,
            "lucide_monitor-speaker" to monitorSpeakerIconState,
            "lucide_monitor-stop" to monitorStopIconState,
            "lucide_monitor-up" to monitorUpIconState,
            "lucide_monitor-x" to monitorXIconState,
            "lucide_moon" to moonIconState,
            "lucide_moon-star" to moonStarIconState,
            "lucide_mountain" to mountainIconState,
            "lucide_mouse" to mouseIconState,
            "lucide_mouse-pointer-click" to mousePointerClickIconState,
            "lucide_mouse-pointer" to mousePointerIconState,
            "lucide_move-diagonal-2" to moveDiagonal2IconState,
            "lucide_move-diagonal" to moveDiagonalIconState,
            "lucide_move-down" to moveDownIconState,
            "lucide_move-down-left" to moveDownLeftIconState,
            "lucide_move-down-right" to moveDownRightIconState,
            "lucide_move-horizontal" to moveHorizontalIconState,
            "lucide_move" to moveIconState,
            "lucide_move-left" to moveLeftIconState,
            "lucide_move-right" to moveRightIconState,
            "lucide_move-up" to moveUpIconState,
            "lucide_move-up-left" to moveUpLeftIconState,
            "lucide_move-up-right" to moveUpRightIconState,
            "lucide_move-vertical" to moveVerticalIconState,
            "lucide_music-2" to music2IconState,
            "lucide_music-3" to music3IconState,
            "lucide_music-4" to music4IconState,
            "lucide_music" to musicIconState,
            "lucide_navigation-2" to navigation2IconState,
            "lucide_navigation" to navigationIconState,
            "lucide_network" to networkIconState,
            "lucide_nfc" to nfcIconState,
            "lucide_notebook-pen" to notebookPenIconState,
            "lucide_octagon-alert" to octagonAlertIconState,
            "lucide_octagon" to octagonIconState,
            "lucide_octagon-minus" to octagonMinusIconState,
            "lucide_octagon-pause" to octagonPauseIconState,
            "lucide_octagon-x" to octagonXIconState,
            "lucide_orbit" to orbitIconState,
            "lucide_package-check" to packageCheckIconState,
            "lucide_package" to packageIconState,
            "lucide_package-open" to packageOpenIconState,
            "lucide_package-plus" to packagePlusIconState,
            "lucide_package-search" to packageSearchIconState,
            "lucide_package-x" to packageXIconState,
            "lucide_paint-bucket" to paintBucketIconState,
            "lucide_paintbrush" to paintbrushIconState,
            "lucide_palette" to paletteIconState,
            "lucide_panel-bottom-close" to panelBottomCloseIconState,
            "lucide_panel-bottom-dashed" to panelBottomDashedIconState,
            "lucide_panel-bottom" to panelBottomIconState,
            "lucide_panel-bottom-open" to panelBottomOpenIconState,
            "lucide_panel-left-close" to panelLeftCloseIconState,
            "lucide_panel-left-dashed" to panelLeftDashedIconState,
            "lucide_panel-left" to panelLeftIconState,
            "lucide_panel-left-open" to panelLeftOpenIconState,
            "lucide_panel-left-right-dashed" to panelLeftRightDashedIconState,
            "lucide_panel-right-close" to panelRightCloseIconState,
            "lucide_panel-right-dashed" to panelRightDashedIconState,
            "lucide_panel-right" to panelRightIconState,
            "lucide_panel-right-open" to panelRightOpenIconState,
            "lucide_panel-top-bottom-dashed" to panelTopBottomDashedIconState,
            "lucide_panel-top-close" to panelTopCloseIconState,
            "lucide_panel-top-dashed" to panelTopDashedIconState,
            "lucide_panel-top" to panelTopIconState,
            "lucide_panel-top-open" to panelTopOpenIconState,
            "lucide_paperclip" to paperclipIconState,
            "lucide_party-popper" to partyPopperIconState,
            "lucide_pause" to lucidePauseIconState,
            "lucide_paw-print" to pawPrintIconState,
            "lucide_pen-tool" to penToolIconState,
            "lucide_pencil" to pencilIconState,
            "lucide_pentagon" to pentagonIconState,
            "lucide_percent" to percentIconState,
            "lucide_philippine-peso" to philippinePesoIconState,
            "lucide_phone-call" to phoneCallIconState,
            "lucide_phone-forwarded" to phoneForwardedIconState,
            "lucide_phone" to phoneIconState,
            "lucide_phone-incoming" to phoneIncomingIconState,
            "lucide_phone-missed" to phoneMissedIconState,
            "lucide_phone-off" to phoneOffIconState,
            "lucide_piano" to pianoIconState,
            "lucide_picture-in-picture" to pictureInPictureIconState,
            "lucide_piggy-bank" to piggyBankIconState,
            "lucide_pilcrow" to pilcrowIconState,
            "lucide_pilcrow-left" to pilcrowLeftIconState,
            "lucide_pilcrow-right" to pilcrowRightIconState,
            "lucide_pill" to pillIconState,
        ),
        mapOf(
            "lucide_minimize" to { minimizeIconState.startAnimation() },
            "lucide_minus" to { minusIconState.startAnimation() },
            "lucide_monitor-check" to { monitorCheckIconState.startAnimation() },
            "lucide_monitor-cloud" to { monitorCloudIconState.startAnimation() },
            "lucide_monitor-cog" to { monitorCogIconState.startAnimation() },
            "lucide_monitor-dot" to { monitorDotIconState.startAnimation() },
            "lucide_monitor-down" to { monitorDownIconState.startAnimation() },
            "lucide_monitor" to { monitorIconState.startAnimation() },
            "lucide_monitor-off" to { monitorOffIconState.startAnimation() },
            "lucide_monitor-pause" to { monitorPauseIconState.startAnimation() },
            "lucide_monitor-pc" to { monitorPcIconState.startAnimation() },
            "lucide_monitor-play" to { monitorPlayIconState.startAnimation() },
            "lucide_monitor-smartphone" to { monitorSmartphoneIconState.startAnimation() },
            "lucide_monitor-speaker" to { monitorSpeakerIconState.startAnimation() },
            "lucide_monitor-stop" to { monitorStopIconState.startAnimation() },
            "lucide_monitor-up" to { monitorUpIconState.startAnimation() },
            "lucide_monitor-x" to { monitorXIconState.startAnimation() },
            "lucide_moon" to { moonIconState.startAnimation() },
            "lucide_moon-star" to { moonStarIconState.startAnimation() },
            "lucide_mountain" to { mountainIconState.startAnimation() },
            "lucide_mouse" to { mouseIconState.startAnimation() },
            "lucide_mouse-pointer-click" to { mousePointerClickIconState.startAnimation() },
            "lucide_mouse-pointer" to { mousePointerIconState.startAnimation() },
            "lucide_move-diagonal-2" to { moveDiagonal2IconState.startAnimation() },
            "lucide_move-diagonal" to { moveDiagonalIconState.startAnimation() },
            "lucide_move-down" to { moveDownIconState.startAnimation() },
            "lucide_move-down-left" to { moveDownLeftIconState.startAnimation() },
            "lucide_move-down-right" to { moveDownRightIconState.startAnimation() },
            "lucide_move-horizontal" to { moveHorizontalIconState.startAnimation() },
            "lucide_move" to { moveIconState.startAnimation() },
            "lucide_move-left" to { moveLeftIconState.startAnimation() },
            "lucide_move-right" to { moveRightIconState.startAnimation() },
            "lucide_move-up" to { moveUpIconState.startAnimation() },
            "lucide_move-up-left" to { moveUpLeftIconState.startAnimation() },
            "lucide_move-up-right" to { moveUpRightIconState.startAnimation() },
            "lucide_move-vertical" to { moveVerticalIconState.startAnimation() },
            "lucide_music-2" to { music2IconState.startAnimation() },
            "lucide_music-3" to { music3IconState.startAnimation() },
            "lucide_music-4" to { music4IconState.startAnimation() },
            "lucide_music" to { musicIconState.startAnimation() },
            "lucide_navigation-2" to { navigation2IconState.startAnimation() },
            "lucide_navigation" to { navigationIconState.startAnimation() },
            "lucide_network" to { networkIconState.startAnimation() },
            "lucide_nfc" to { nfcIconState.startAnimation() },
            "lucide_notebook-pen" to { notebookPenIconState.startAnimation() },
            "lucide_octagon-alert" to { octagonAlertIconState.startAnimation() },
            "lucide_octagon" to { octagonIconState.startAnimation() },
            "lucide_octagon-minus" to { octagonMinusIconState.startAnimation() },
            "lucide_octagon-pause" to { octagonPauseIconState.startAnimation() },
            "lucide_octagon-x" to { octagonXIconState.startAnimation() },
            "lucide_orbit" to { orbitIconState.startAnimation() },
            "lucide_package-check" to { packageCheckIconState.startAnimation() },
            "lucide_package" to { packageIconState.startAnimation() },
            "lucide_package-open" to { packageOpenIconState.startAnimation() },
            "lucide_package-plus" to { packagePlusIconState.startAnimation() },
            "lucide_package-search" to { packageSearchIconState.startAnimation() },
            "lucide_package-x" to { packageXIconState.startAnimation() },
            "lucide_paint-bucket" to { paintBucketIconState.startAnimation() },
            "lucide_paintbrush" to { paintbrushIconState.startAnimation() },
            "lucide_palette" to { paletteIconState.startAnimation() },
            "lucide_panel-bottom-close" to { panelBottomCloseIconState.startAnimation() },
            "lucide_panel-bottom-dashed" to { panelBottomDashedIconState.startAnimation() },
            "lucide_panel-bottom" to { panelBottomIconState.startAnimation() },
            "lucide_panel-bottom-open" to { panelBottomOpenIconState.startAnimation() },
            "lucide_panel-left-close" to { panelLeftCloseIconState.startAnimation() },
            "lucide_panel-left-dashed" to { panelLeftDashedIconState.startAnimation() },
            "lucide_panel-left" to { panelLeftIconState.startAnimation() },
            "lucide_panel-left-open" to { panelLeftOpenIconState.startAnimation() },
            "lucide_panel-left-right-dashed" to { panelLeftRightDashedIconState.startAnimation() },
            "lucide_panel-right-close" to { panelRightCloseIconState.startAnimation() },
            "lucide_panel-right-dashed" to { panelRightDashedIconState.startAnimation() },
            "lucide_panel-right" to { panelRightIconState.startAnimation() },
            "lucide_panel-right-open" to { panelRightOpenIconState.startAnimation() },
            "lucide_panel-top-bottom-dashed" to { panelTopBottomDashedIconState.startAnimation() },
            "lucide_panel-top-close" to { panelTopCloseIconState.startAnimation() },
            "lucide_panel-top-dashed" to { panelTopDashedIconState.startAnimation() },
            "lucide_panel-top" to { panelTopIconState.startAnimation() },
            "lucide_panel-top-open" to { panelTopOpenIconState.startAnimation() },
            "lucide_paperclip" to { paperclipIconState.startAnimation() },
            "lucide_party-popper" to { partyPopperIconState.startAnimation() },
            "lucide_pause" to { lucidePauseIconState.startAnimation() },
            "lucide_paw-print" to { pawPrintIconState.startAnimation() },
            "lucide_pen-tool" to { penToolIconState.startAnimation() },
            "lucide_pencil" to { pencilIconState.startAnimation() },
            "lucide_pentagon" to { pentagonIconState.startAnimation() },
            "lucide_percent" to { percentIconState.startAnimation() },
            "lucide_philippine-peso" to { philippinePesoIconState.startAnimation() },
            "lucide_phone-call" to { phoneCallIconState.startAnimation() },
            "lucide_phone-forwarded" to { phoneForwardedIconState.startAnimation() },
            "lucide_phone" to { phoneIconState.startAnimation() },
            "lucide_phone-incoming" to { phoneIncomingIconState.startAnimation() },
            "lucide_phone-missed" to { phoneMissedIconState.startAnimation() },
            "lucide_phone-off" to { phoneOffIconState.startAnimation() },
            "lucide_piano" to { pianoIconState.startAnimation() },
            "lucide_picture-in-picture" to { pictureInPictureIconState.startAnimation() },
            "lucide_piggy-bank" to { piggyBankIconState.startAnimation() },
            "lucide_pilcrow" to { pilcrowIconState.startAnimation() },
            "lucide_pilcrow-left" to { pilcrowLeftIconState.startAnimation() },
            "lucide_pilcrow-right" to { pilcrowRightIconState.startAnimation() },
            "lucide_pill" to { pillIconState.startAnimation() },
        ),
        mapOf(
            "lucide_minimize" to { minimizeIconState.stopAnimation() },
            "lucide_minus" to { minusIconState.stopAnimation() },
            "lucide_monitor-check" to { monitorCheckIconState.stopAnimation() },
            "lucide_monitor-cloud" to { monitorCloudIconState.stopAnimation() },
            "lucide_monitor-cog" to { monitorCogIconState.stopAnimation() },
            "lucide_monitor-dot" to { monitorDotIconState.stopAnimation() },
            "lucide_monitor-down" to { monitorDownIconState.stopAnimation() },
            "lucide_monitor" to { monitorIconState.stopAnimation() },
            "lucide_monitor-off" to { monitorOffIconState.stopAnimation() },
            "lucide_monitor-pause" to { monitorPauseIconState.stopAnimation() },
            "lucide_monitor-pc" to { monitorPcIconState.stopAnimation() },
            "lucide_monitor-play" to { monitorPlayIconState.stopAnimation() },
            "lucide_monitor-smartphone" to { monitorSmartphoneIconState.stopAnimation() },
            "lucide_monitor-speaker" to { monitorSpeakerIconState.stopAnimation() },
            "lucide_monitor-stop" to { monitorStopIconState.stopAnimation() },
            "lucide_monitor-up" to { monitorUpIconState.stopAnimation() },
            "lucide_monitor-x" to { monitorXIconState.stopAnimation() },
            "lucide_moon" to { moonIconState.stopAnimation() },
            "lucide_moon-star" to { moonStarIconState.stopAnimation() },
            "lucide_mountain" to { mountainIconState.stopAnimation() },
            "lucide_mouse" to { mouseIconState.stopAnimation() },
            "lucide_mouse-pointer-click" to { mousePointerClickIconState.stopAnimation() },
            "lucide_mouse-pointer" to { mousePointerIconState.stopAnimation() },
            "lucide_move-diagonal-2" to { moveDiagonal2IconState.stopAnimation() },
            "lucide_move-diagonal" to { moveDiagonalIconState.stopAnimation() },
            "lucide_move-down" to { moveDownIconState.stopAnimation() },
            "lucide_move-down-left" to { moveDownLeftIconState.stopAnimation() },
            "lucide_move-down-right" to { moveDownRightIconState.stopAnimation() },
            "lucide_move-horizontal" to { moveHorizontalIconState.stopAnimation() },
            "lucide_move" to { moveIconState.stopAnimation() },
            "lucide_move-left" to { moveLeftIconState.stopAnimation() },
            "lucide_move-right" to { moveRightIconState.stopAnimation() },
            "lucide_move-up" to { moveUpIconState.stopAnimation() },
            "lucide_move-up-left" to { moveUpLeftIconState.stopAnimation() },
            "lucide_move-up-right" to { moveUpRightIconState.stopAnimation() },
            "lucide_move-vertical" to { moveVerticalIconState.stopAnimation() },
            "lucide_music-2" to { music2IconState.stopAnimation() },
            "lucide_music-3" to { music3IconState.stopAnimation() },
            "lucide_music-4" to { music4IconState.stopAnimation() },
            "lucide_music" to { musicIconState.stopAnimation() },
            "lucide_navigation-2" to { navigation2IconState.stopAnimation() },
            "lucide_navigation" to { navigationIconState.stopAnimation() },
            "lucide_network" to { networkIconState.stopAnimation() },
            "lucide_nfc" to { nfcIconState.stopAnimation() },
            "lucide_notebook-pen" to { notebookPenIconState.stopAnimation() },
            "lucide_octagon-alert" to { octagonAlertIconState.stopAnimation() },
            "lucide_octagon" to { octagonIconState.stopAnimation() },
            "lucide_octagon-minus" to { octagonMinusIconState.stopAnimation() },
            "lucide_octagon-pause" to { octagonPauseIconState.stopAnimation() },
            "lucide_octagon-x" to { octagonXIconState.stopAnimation() },
            "lucide_orbit" to { orbitIconState.stopAnimation() },
            "lucide_package-check" to { packageCheckIconState.stopAnimation() },
            "lucide_package" to { packageIconState.stopAnimation() },
            "lucide_package-open" to { packageOpenIconState.stopAnimation() },
            "lucide_package-plus" to { packagePlusIconState.stopAnimation() },
            "lucide_package-search" to { packageSearchIconState.stopAnimation() },
            "lucide_package-x" to { packageXIconState.stopAnimation() },
            "lucide_paint-bucket" to { paintBucketIconState.stopAnimation() },
            "lucide_paintbrush" to { paintbrushIconState.stopAnimation() },
            "lucide_palette" to { paletteIconState.stopAnimation() },
            "lucide_panel-bottom-close" to { panelBottomCloseIconState.stopAnimation() },
            "lucide_panel-bottom-dashed" to { panelBottomDashedIconState.stopAnimation() },
            "lucide_panel-bottom" to { panelBottomIconState.stopAnimation() },
            "lucide_panel-bottom-open" to { panelBottomOpenIconState.stopAnimation() },
            "lucide_panel-left-close" to { panelLeftCloseIconState.stopAnimation() },
            "lucide_panel-left-dashed" to { panelLeftDashedIconState.stopAnimation() },
            "lucide_panel-left" to { panelLeftIconState.stopAnimation() },
            "lucide_panel-left-open" to { panelLeftOpenIconState.stopAnimation() },
            "lucide_panel-left-right-dashed" to { panelLeftRightDashedIconState.stopAnimation() },
            "lucide_panel-right-close" to { panelRightCloseIconState.stopAnimation() },
            "lucide_panel-right-dashed" to { panelRightDashedIconState.stopAnimation() },
            "lucide_panel-right" to { panelRightIconState.stopAnimation() },
            "lucide_panel-right-open" to { panelRightOpenIconState.stopAnimation() },
            "lucide_panel-top-bottom-dashed" to { panelTopBottomDashedIconState.stopAnimation() },
            "lucide_panel-top-close" to { panelTopCloseIconState.stopAnimation() },
            "lucide_panel-top-dashed" to { panelTopDashedIconState.stopAnimation() },
            "lucide_panel-top" to { panelTopIconState.stopAnimation() },
            "lucide_panel-top-open" to { panelTopOpenIconState.stopAnimation() },
            "lucide_paperclip" to { paperclipIconState.stopAnimation() },
            "lucide_party-popper" to { partyPopperIconState.stopAnimation() },
            "lucide_pause" to { lucidePauseIconState.stopAnimation() },
            "lucide_paw-print" to { pawPrintIconState.stopAnimation() },
            "lucide_pen-tool" to { penToolIconState.stopAnimation() },
            "lucide_pencil" to { pencilIconState.stopAnimation() },
            "lucide_pentagon" to { pentagonIconState.stopAnimation() },
            "lucide_percent" to { percentIconState.stopAnimation() },
            "lucide_philippine-peso" to { philippinePesoIconState.stopAnimation() },
            "lucide_phone-call" to { phoneCallIconState.stopAnimation() },
            "lucide_phone-forwarded" to { phoneForwardedIconState.stopAnimation() },
            "lucide_phone" to { phoneIconState.stopAnimation() },
            "lucide_phone-incoming" to { phoneIncomingIconState.stopAnimation() },
            "lucide_phone-missed" to { phoneMissedIconState.stopAnimation() },
            "lucide_phone-off" to { phoneOffIconState.stopAnimation() },
            "lucide_piano" to { pianoIconState.stopAnimation() },
            "lucide_picture-in-picture" to { pictureInPictureIconState.stopAnimation() },
            "lucide_piggy-bank" to { piggyBankIconState.stopAnimation() },
            "lucide_pilcrow" to { pilcrowIconState.stopAnimation() },
            "lucide_pilcrow-left" to { pilcrowLeftIconState.stopAnimation() },
            "lucide_pilcrow-right" to { pilcrowRightIconState.stopAnimation() },
            "lucide_pill" to { pillIconState.stopAnimation() },
        ),
    )
}

@Composable
private fun rememberLucideStatesChunk7(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val pinIconState = rememberPinIconState()
    val pinOffIconState = rememberPinOffIconState()
    val pipetteIconState = rememberPipetteIconState()
    val planeIconState = rememberPlaneIconState()
    val lucidePlayIconState = rememberLucidePlayIconState()
    val playOffIconState = rememberPlayOffIconState()
    val plug2IconState = rememberPlug2IconState()
    val plugIconState = rememberPlugIconState()
    val plugZapIconState = rememberPlugZapIconState()
    val plusIconState = rememberPlusIconState()
    val podcastIconState = rememberPodcastIconState()
    val poundSterlingIconState = rememberPoundSterlingIconState()
    val powerIconState = rememberPowerIconState()
    val powerOffIconState = rememberPowerOffIconState()
    val presentationIconState = rememberPresentationIconState()
    val printerIconState = rememberPrinterIconState()
    val puzzleIconState = rememberPuzzleIconState()
    val qrCodeIconState = rememberQrCodeIconState()
    val quoteIconState = rememberQuoteIconState()
    val rabbitIconState = rememberRabbitIconState()
    val radarIconState = rememberRadarIconState()
    val radioIconState = rememberRadioIconState()
    val radioOffIconState = rememberRadioOffIconState()
    val radioReceiverIconState = rememberRadioReceiverIconState()
    val rainbowIconState = rememberRainbowIconState()
    val receiptIconState = rememberReceiptIconState()
    val receiptTextIconState = rememberReceiptTextIconState()
    val recycleIconState = rememberRecycleIconState()
    val redoIconState = rememberRedoIconState()
    val refreshCcwDotIconState = rememberRefreshCcwDotIconState()
    val refreshCcwIconState = rememberRefreshCcwIconState()
    val refreshCwIconState = rememberRefreshCwIconState()
    val refreshCwOffIconState = rememberRefreshCwOffIconState()
    val regexIconState = rememberRegexIconState()
    val removeFormattingIconState = rememberRemoveFormattingIconState()
    val repeat1IconState = rememberRepeat1IconState()
    val repeat2IconState = rememberRepeat2IconState()
    val repeatIconState = rememberRepeatIconState()
    val replyAllIconState = rememberReplyAllIconState()
    val replyIconState = rememberReplyIconState()
    val rewindIconState = rememberRewindIconState()
    val rocketIconState = rememberRocketIconState()
    val rotateCcwIconState = rememberRotateCcwIconState()
    val rotateCwIconState = rememberRotateCwIconState()
    val routeIconState = rememberRouteIconState()
    val routeOffIconState = rememberRouteOffIconState()
    val routerIconState = rememberRouterIconState()
    val rows2IconState = rememberRows2IconState()
    val rows3IconState = rememberRows3IconState()
    val rows4IconState = rememberRows4IconState()
    val rssIconState = rememberRssIconState()
    val rulerIconState = rememberRulerIconState()
    val russianRubleIconState = rememberRussianRubleIconState()
    val saudiRiyalIconState = rememberSaudiRiyalIconState()
    val saveIconState = rememberSaveIconState()
    val scanIconState = rememberScanIconState()
    val scanLineIconState = rememberScanLineIconState()
    val scanQrCodeIconState = rememberScanQrCodeIconState()
    val schoolIconState = rememberSchoolIconState()
    val scissorsIconState = rememberScissorsIconState()
    val screenShareIconState = rememberScreenShareIconState()
    val scrollTextIconState = rememberScrollTextIconState()
    val searchIconState = rememberSearchIconState()
    val searchXIconState = rememberSearchXIconState()
    val sendHorizontalIconState = rememberSendHorizontalIconState()
    val sendIconState = rememberSendIconState()
    val serverCogIconState = rememberServerCogIconState()
    val serverCrashIconState = rememberServerCrashIconState()
    val serverIconState = rememberServerIconState()
    val serverOffIconState = rememberServerOffIconState()
    val serverPlusIconState = rememberServerPlusIconState()
    val settingsIconState = rememberSettingsIconState()
    val shapesIconState = rememberShapesIconState()
    val shareIconState = rememberShareIconState()
    val shieldAlertIconState = rememberShieldAlertIconState()
    val shieldBanIconState = rememberShieldBanIconState()
    val shieldCheckIconState = rememberShieldCheckIconState()
    val shieldCogCornerIconState = rememberShieldCogCornerIconState()
    val shieldCogIconState = rememberShieldCogIconState()
    val shieldEllipsisIconState = rememberShieldEllipsisIconState()
    val shieldHalfIconState = rememberShieldHalfIconState()
    val shieldIconState = rememberShieldIconState()
    val shieldKeyholeIconState = rememberShieldKeyholeIconState()
    val shieldLockIconState = rememberShieldLockIconState()
    val shieldMinusIconState = rememberShieldMinusIconState()
    val shieldOffIconState = rememberShieldOffIconState()
    val shieldPlusIconState = rememberShieldPlusIconState()
    val shieldQuestionMarkIconState = rememberShieldQuestionMarkIconState()
    val shieldUserIconState = rememberShieldUserIconState()
    val shieldXIconState = rememberShieldXIconState()
    val shoppingBagIconState = rememberShoppingBagIconState()
    val shoppingBasketIconState = rememberShoppingBasketIconState()
    val shoppingCartIconState = rememberShoppingCartIconState()
    val shoppingCartMinusIconState = rememberShoppingCartMinusIconState()
    val shoppingCartPlusIconState = rememberShoppingCartPlusIconState()
    val shrinkIconState = rememberShrinkIconState()
    val shuffleIconState = rememberShuffleIconState()
    val sigmaIconState = rememberSigmaIconState()
    val signalHighIconState = rememberSignalHighIconState()
    val signalIconState = rememberSignalIconState()
    return Triple(
        mapOf(
            "lucide_pin" to pinIconState,
            "lucide_pin-off" to pinOffIconState,
            "lucide_pipette" to pipetteIconState,
            "lucide_plane" to planeIconState,
            "lucide_play" to lucidePlayIconState,
            "lucide_play-off" to playOffIconState,
            "lucide_plug-2" to plug2IconState,
            "lucide_plug" to plugIconState,
            "lucide_plug-zap" to plugZapIconState,
            "lucide_plus" to plusIconState,
            "lucide_podcast" to podcastIconState,
            "lucide_pound-sterling" to poundSterlingIconState,
            "lucide_power" to powerIconState,
            "lucide_power-off" to powerOffIconState,
            "lucide_presentation" to presentationIconState,
            "lucide_printer" to printerIconState,
            "lucide_puzzle" to puzzleIconState,
            "lucide_qr-code" to qrCodeIconState,
            "lucide_quote" to quoteIconState,
            "lucide_rabbit" to rabbitIconState,
            "lucide_radar" to radarIconState,
            "lucide_radio" to radioIconState,
            "lucide_radio-off" to radioOffIconState,
            "lucide_radio-receiver" to radioReceiverIconState,
            "lucide_rainbow" to rainbowIconState,
            "lucide_receipt" to receiptIconState,
            "lucide_receipt-text" to receiptTextIconState,
            "lucide_recycle" to recycleIconState,
            "lucide_redo" to redoIconState,
            "lucide_refresh-ccw-dot" to refreshCcwDotIconState,
            "lucide_refresh-ccw" to refreshCcwIconState,
            "lucide_refresh-cw" to refreshCwIconState,
            "lucide_refresh-cw-off" to refreshCwOffIconState,
            "lucide_regex" to regexIconState,
            "lucide_remove-formatting" to removeFormattingIconState,
            "lucide_repeat-1" to repeat1IconState,
            "lucide_repeat-2" to repeat2IconState,
            "lucide_repeat" to repeatIconState,
            "lucide_reply-all" to replyAllIconState,
            "lucide_reply" to replyIconState,
            "lucide_rewind" to rewindIconState,
            "lucide_rocket" to rocketIconState,
            "lucide_rotate-ccw" to rotateCcwIconState,
            "lucide_rotate-cw" to rotateCwIconState,
            "lucide_route" to routeIconState,
            "lucide_route-off" to routeOffIconState,
            "lucide_router" to routerIconState,
            "lucide_rows-2" to rows2IconState,
            "lucide_rows-3" to rows3IconState,
            "lucide_rows-4" to rows4IconState,
            "lucide_rss" to rssIconState,
            "lucide_ruler" to rulerIconState,
            "lucide_russian-ruble" to russianRubleIconState,
            "lucide_saudi-riyal" to saudiRiyalIconState,
            "lucide_save" to saveIconState,
            "lucide_scan" to scanIconState,
            "lucide_scan-line" to scanLineIconState,
            "lucide_scan-qr-code" to scanQrCodeIconState,
            "lucide_school" to schoolIconState,
            "lucide_scissors" to scissorsIconState,
            "lucide_screen-share" to screenShareIconState,
            "lucide_scroll-text" to scrollTextIconState,
            "lucide_search" to searchIconState,
            "lucide_search-x" to searchXIconState,
            "lucide_send-horizontal" to sendHorizontalIconState,
            "lucide_send" to sendIconState,
            "lucide_server-cog" to serverCogIconState,
            "lucide_server-crash" to serverCrashIconState,
            "lucide_server" to serverIconState,
            "lucide_server-off" to serverOffIconState,
            "lucide_server-plus" to serverPlusIconState,
            "lucide_settings" to settingsIconState,
            "lucide_shapes" to shapesIconState,
            "lucide_share" to shareIconState,
            "lucide_shield-alert" to shieldAlertIconState,
            "lucide_shield-ban" to shieldBanIconState,
            "lucide_shield-check" to shieldCheckIconState,
            "lucide_shield-cog-corner" to shieldCogCornerIconState,
            "lucide_shield-cog" to shieldCogIconState,
            "lucide_shield-ellipsis" to shieldEllipsisIconState,
            "lucide_shield-half" to shieldHalfIconState,
            "lucide_shield" to shieldIconState,
            "lucide_shield-keyhole" to shieldKeyholeIconState,
            "lucide_shield-lock" to shieldLockIconState,
            "lucide_shield-minus" to shieldMinusIconState,
            "lucide_shield-off" to shieldOffIconState,
            "lucide_shield-plus" to shieldPlusIconState,
            "lucide_shield-question-mark" to shieldQuestionMarkIconState,
            "lucide_shield-user" to shieldUserIconState,
            "lucide_shield-x" to shieldXIconState,
            "lucide_shopping-bag" to shoppingBagIconState,
            "lucide_shopping-basket" to shoppingBasketIconState,
            "lucide_shopping-cart" to shoppingCartIconState,
            "lucide_shopping-cart-minus" to shoppingCartMinusIconState,
            "lucide_shopping-cart-plus" to shoppingCartPlusIconState,
            "lucide_shrink" to shrinkIconState,
            "lucide_shuffle" to shuffleIconState,
            "lucide_sigma" to sigmaIconState,
            "lucide_signal-high" to signalHighIconState,
            "lucide_signal" to signalIconState,
        ),
        mapOf(
            "lucide_pin" to { pinIconState.startAnimation() },
            "lucide_pin-off" to { pinOffIconState.startAnimation() },
            "lucide_pipette" to { pipetteIconState.startAnimation() },
            "lucide_plane" to { planeIconState.startAnimation() },
            "lucide_play" to { lucidePlayIconState.startAnimation() },
            "lucide_play-off" to { playOffIconState.startAnimation() },
            "lucide_plug-2" to { plug2IconState.startAnimation() },
            "lucide_plug" to { plugIconState.startAnimation() },
            "lucide_plug-zap" to { plugZapIconState.startAnimation() },
            "lucide_plus" to { plusIconState.startAnimation() },
            "lucide_podcast" to { podcastIconState.startAnimation() },
            "lucide_pound-sterling" to { poundSterlingIconState.startAnimation() },
            "lucide_power" to { powerIconState.startAnimation() },
            "lucide_power-off" to { powerOffIconState.startAnimation() },
            "lucide_presentation" to { presentationIconState.startAnimation() },
            "lucide_printer" to { printerIconState.startAnimation() },
            "lucide_puzzle" to { puzzleIconState.startAnimation() },
            "lucide_qr-code" to { qrCodeIconState.startAnimation() },
            "lucide_quote" to { quoteIconState.startAnimation() },
            "lucide_rabbit" to { rabbitIconState.startAnimation() },
            "lucide_radar" to { radarIconState.startAnimation() },
            "lucide_radio" to { radioIconState.startAnimation() },
            "lucide_radio-off" to { radioOffIconState.startAnimation() },
            "lucide_radio-receiver" to { radioReceiverIconState.startAnimation() },
            "lucide_rainbow" to { rainbowIconState.startAnimation() },
            "lucide_receipt" to { receiptIconState.startAnimation() },
            "lucide_receipt-text" to { receiptTextIconState.startAnimation() },
            "lucide_recycle" to { recycleIconState.startAnimation() },
            "lucide_redo" to { redoIconState.startAnimation() },
            "lucide_refresh-ccw-dot" to { refreshCcwDotIconState.startAnimation() },
            "lucide_refresh-ccw" to { refreshCcwIconState.startAnimation() },
            "lucide_refresh-cw" to { refreshCwIconState.startAnimation() },
            "lucide_refresh-cw-off" to { refreshCwOffIconState.startAnimation() },
            "lucide_regex" to { regexIconState.startAnimation() },
            "lucide_remove-formatting" to { removeFormattingIconState.startAnimation() },
            "lucide_repeat-1" to { repeat1IconState.startAnimation() },
            "lucide_repeat-2" to { repeat2IconState.startAnimation() },
            "lucide_repeat" to { repeatIconState.startAnimation() },
            "lucide_reply-all" to { replyAllIconState.startAnimation() },
            "lucide_reply" to { replyIconState.startAnimation() },
            "lucide_rewind" to { rewindIconState.startAnimation() },
            "lucide_rocket" to { rocketIconState.startAnimation() },
            "lucide_rotate-ccw" to { rotateCcwIconState.startAnimation() },
            "lucide_rotate-cw" to { rotateCwIconState.startAnimation() },
            "lucide_route" to { routeIconState.startAnimation() },
            "lucide_route-off" to { routeOffIconState.startAnimation() },
            "lucide_router" to { routerIconState.startAnimation() },
            "lucide_rows-2" to { rows2IconState.startAnimation() },
            "lucide_rows-3" to { rows3IconState.startAnimation() },
            "lucide_rows-4" to { rows4IconState.startAnimation() },
            "lucide_rss" to { rssIconState.startAnimation() },
            "lucide_ruler" to { rulerIconState.startAnimation() },
            "lucide_russian-ruble" to { russianRubleIconState.startAnimation() },
            "lucide_saudi-riyal" to { saudiRiyalIconState.startAnimation() },
            "lucide_save" to { saveIconState.startAnimation() },
            "lucide_scan" to { scanIconState.startAnimation() },
            "lucide_scan-line" to { scanLineIconState.startAnimation() },
            "lucide_scan-qr-code" to { scanQrCodeIconState.startAnimation() },
            "lucide_school" to { schoolIconState.startAnimation() },
            "lucide_scissors" to { scissorsIconState.startAnimation() },
            "lucide_screen-share" to { screenShareIconState.startAnimation() },
            "lucide_scroll-text" to { scrollTextIconState.startAnimation() },
            "lucide_search" to { searchIconState.startAnimation() },
            "lucide_search-x" to { searchXIconState.startAnimation() },
            "lucide_send-horizontal" to { sendHorizontalIconState.startAnimation() },
            "lucide_send" to { sendIconState.startAnimation() },
            "lucide_server-cog" to { serverCogIconState.startAnimation() },
            "lucide_server-crash" to { serverCrashIconState.startAnimation() },
            "lucide_server" to { serverIconState.startAnimation() },
            "lucide_server-off" to { serverOffIconState.startAnimation() },
            "lucide_server-plus" to { serverPlusIconState.startAnimation() },
            "lucide_settings" to { settingsIconState.startAnimation() },
            "lucide_shapes" to { shapesIconState.startAnimation() },
            "lucide_share" to { shareIconState.startAnimation() },
            "lucide_shield-alert" to { shieldAlertIconState.startAnimation() },
            "lucide_shield-ban" to { shieldBanIconState.startAnimation() },
            "lucide_shield-check" to { shieldCheckIconState.startAnimation() },
            "lucide_shield-cog-corner" to { shieldCogCornerIconState.startAnimation() },
            "lucide_shield-cog" to { shieldCogIconState.startAnimation() },
            "lucide_shield-ellipsis" to { shieldEllipsisIconState.startAnimation() },
            "lucide_shield-half" to { shieldHalfIconState.startAnimation() },
            "lucide_shield" to { shieldIconState.startAnimation() },
            "lucide_shield-keyhole" to { shieldKeyholeIconState.startAnimation() },
            "lucide_shield-lock" to { shieldLockIconState.startAnimation() },
            "lucide_shield-minus" to { shieldMinusIconState.startAnimation() },
            "lucide_shield-off" to { shieldOffIconState.startAnimation() },
            "lucide_shield-plus" to { shieldPlusIconState.startAnimation() },
            "lucide_shield-question-mark" to { shieldQuestionMarkIconState.startAnimation() },
            "lucide_shield-user" to { shieldUserIconState.startAnimation() },
            "lucide_shield-x" to { shieldXIconState.startAnimation() },
            "lucide_shopping-bag" to { shoppingBagIconState.startAnimation() },
            "lucide_shopping-basket" to { shoppingBasketIconState.startAnimation() },
            "lucide_shopping-cart" to { shoppingCartIconState.startAnimation() },
            "lucide_shopping-cart-minus" to { shoppingCartMinusIconState.startAnimation() },
            "lucide_shopping-cart-plus" to { shoppingCartPlusIconState.startAnimation() },
            "lucide_shrink" to { shrinkIconState.startAnimation() },
            "lucide_shuffle" to { shuffleIconState.startAnimation() },
            "lucide_sigma" to { sigmaIconState.startAnimation() },
            "lucide_signal-high" to { signalHighIconState.startAnimation() },
            "lucide_signal" to { signalIconState.startAnimation() },
        ),
        mapOf(
            "lucide_pin" to { pinIconState.stopAnimation() },
            "lucide_pin-off" to { pinOffIconState.stopAnimation() },
            "lucide_pipette" to { pipetteIconState.stopAnimation() },
            "lucide_plane" to { planeIconState.stopAnimation() },
            "lucide_play" to { lucidePlayIconState.stopAnimation() },
            "lucide_play-off" to { playOffIconState.stopAnimation() },
            "lucide_plug-2" to { plug2IconState.stopAnimation() },
            "lucide_plug" to { plugIconState.stopAnimation() },
            "lucide_plug-zap" to { plugZapIconState.stopAnimation() },
            "lucide_plus" to { plusIconState.stopAnimation() },
            "lucide_podcast" to { podcastIconState.stopAnimation() },
            "lucide_pound-sterling" to { poundSterlingIconState.stopAnimation() },
            "lucide_power" to { powerIconState.stopAnimation() },
            "lucide_power-off" to { powerOffIconState.stopAnimation() },
            "lucide_presentation" to { presentationIconState.stopAnimation() },
            "lucide_printer" to { printerIconState.stopAnimation() },
            "lucide_puzzle" to { puzzleIconState.stopAnimation() },
            "lucide_qr-code" to { qrCodeIconState.stopAnimation() },
            "lucide_quote" to { quoteIconState.stopAnimation() },
            "lucide_rabbit" to { rabbitIconState.stopAnimation() },
            "lucide_radar" to { radarIconState.stopAnimation() },
            "lucide_radio" to { radioIconState.stopAnimation() },
            "lucide_radio-off" to { radioOffIconState.stopAnimation() },
            "lucide_radio-receiver" to { radioReceiverIconState.stopAnimation() },
            "lucide_rainbow" to { rainbowIconState.stopAnimation() },
            "lucide_receipt" to { receiptIconState.stopAnimation() },
            "lucide_receipt-text" to { receiptTextIconState.stopAnimation() },
            "lucide_recycle" to { recycleIconState.stopAnimation() },
            "lucide_redo" to { redoIconState.stopAnimation() },
            "lucide_refresh-ccw-dot" to { refreshCcwDotIconState.stopAnimation() },
            "lucide_refresh-ccw" to { refreshCcwIconState.stopAnimation() },
            "lucide_refresh-cw" to { refreshCwIconState.stopAnimation() },
            "lucide_refresh-cw-off" to { refreshCwOffIconState.stopAnimation() },
            "lucide_regex" to { regexIconState.stopAnimation() },
            "lucide_remove-formatting" to { removeFormattingIconState.stopAnimation() },
            "lucide_repeat-1" to { repeat1IconState.stopAnimation() },
            "lucide_repeat-2" to { repeat2IconState.stopAnimation() },
            "lucide_repeat" to { repeatIconState.stopAnimation() },
            "lucide_reply-all" to { replyAllIconState.stopAnimation() },
            "lucide_reply" to { replyIconState.stopAnimation() },
            "lucide_rewind" to { rewindIconState.stopAnimation() },
            "lucide_rocket" to { rocketIconState.stopAnimation() },
            "lucide_rotate-ccw" to { rotateCcwIconState.stopAnimation() },
            "lucide_rotate-cw" to { rotateCwIconState.stopAnimation() },
            "lucide_route" to { routeIconState.stopAnimation() },
            "lucide_route-off" to { routeOffIconState.stopAnimation() },
            "lucide_router" to { routerIconState.stopAnimation() },
            "lucide_rows-2" to { rows2IconState.stopAnimation() },
            "lucide_rows-3" to { rows3IconState.stopAnimation() },
            "lucide_rows-4" to { rows4IconState.stopAnimation() },
            "lucide_rss" to { rssIconState.stopAnimation() },
            "lucide_ruler" to { rulerIconState.stopAnimation() },
            "lucide_russian-ruble" to { russianRubleIconState.stopAnimation() },
            "lucide_saudi-riyal" to { saudiRiyalIconState.stopAnimation() },
            "lucide_save" to { saveIconState.stopAnimation() },
            "lucide_scan" to { scanIconState.stopAnimation() },
            "lucide_scan-line" to { scanLineIconState.stopAnimation() },
            "lucide_scan-qr-code" to { scanQrCodeIconState.stopAnimation() },
            "lucide_school" to { schoolIconState.stopAnimation() },
            "lucide_scissors" to { scissorsIconState.stopAnimation() },
            "lucide_screen-share" to { screenShareIconState.stopAnimation() },
            "lucide_scroll-text" to { scrollTextIconState.stopAnimation() },
            "lucide_search" to { searchIconState.stopAnimation() },
            "lucide_search-x" to { searchXIconState.stopAnimation() },
            "lucide_send-horizontal" to { sendHorizontalIconState.stopAnimation() },
            "lucide_send" to { sendIconState.stopAnimation() },
            "lucide_server-cog" to { serverCogIconState.stopAnimation() },
            "lucide_server-crash" to { serverCrashIconState.stopAnimation() },
            "lucide_server" to { serverIconState.stopAnimation() },
            "lucide_server-off" to { serverOffIconState.stopAnimation() },
            "lucide_server-plus" to { serverPlusIconState.stopAnimation() },
            "lucide_settings" to { settingsIconState.stopAnimation() },
            "lucide_shapes" to { shapesIconState.stopAnimation() },
            "lucide_share" to { shareIconState.stopAnimation() },
            "lucide_shield-alert" to { shieldAlertIconState.stopAnimation() },
            "lucide_shield-ban" to { shieldBanIconState.stopAnimation() },
            "lucide_shield-check" to { shieldCheckIconState.stopAnimation() },
            "lucide_shield-cog-corner" to { shieldCogCornerIconState.stopAnimation() },
            "lucide_shield-cog" to { shieldCogIconState.stopAnimation() },
            "lucide_shield-ellipsis" to { shieldEllipsisIconState.stopAnimation() },
            "lucide_shield-half" to { shieldHalfIconState.stopAnimation() },
            "lucide_shield" to { shieldIconState.stopAnimation() },
            "lucide_shield-keyhole" to { shieldKeyholeIconState.stopAnimation() },
            "lucide_shield-lock" to { shieldLockIconState.stopAnimation() },
            "lucide_shield-minus" to { shieldMinusIconState.stopAnimation() },
            "lucide_shield-off" to { shieldOffIconState.stopAnimation() },
            "lucide_shield-plus" to { shieldPlusIconState.stopAnimation() },
            "lucide_shield-question-mark" to { shieldQuestionMarkIconState.stopAnimation() },
            "lucide_shield-user" to { shieldUserIconState.stopAnimation() },
            "lucide_shield-x" to { shieldXIconState.stopAnimation() },
            "lucide_shopping-bag" to { shoppingBagIconState.stopAnimation() },
            "lucide_shopping-basket" to { shoppingBasketIconState.stopAnimation() },
            "lucide_shopping-cart" to { shoppingCartIconState.stopAnimation() },
            "lucide_shopping-cart-minus" to { shoppingCartMinusIconState.stopAnimation() },
            "lucide_shopping-cart-plus" to { shoppingCartPlusIconState.stopAnimation() },
            "lucide_shrink" to { shrinkIconState.stopAnimation() },
            "lucide_shuffle" to { shuffleIconState.stopAnimation() },
            "lucide_sigma" to { sigmaIconState.stopAnimation() },
            "lucide_signal-high" to { signalHighIconState.stopAnimation() },
            "lucide_signal" to { signalIconState.stopAnimation() },
        ),
    )
}

@Composable
private fun rememberLucideStatesChunk8(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val signalLowIconState = rememberSignalLowIconState()
    val signalMediumIconState = rememberSignalMediumIconState()
    val signalZeroIconState = rememberSignalZeroIconState()
    val signatureIconState = rememberSignatureIconState()
    val signpostBigIconState = rememberSignpostBigIconState()
    val signpostIconState = rememberSignpostIconState()
    val sirenIconState = rememberSirenIconState()
    val skipBackIconState = rememberSkipBackIconState()
    val skipForwardIconState = rememberSkipForwardIconState()
    val slidersHorizontalIconState = rememberSlidersHorizontalIconState()
    val smartphoneChargingIconState = rememberSmartphoneChargingIconState()
    val smartphoneIconState = rememberSmartphoneIconState()
    val smartphoneNfcIconState = rememberSmartphoneNfcIconState()
    val snowflakeIconState = rememberSnowflakeIconState()
    val sparklesIconState = rememberSparklesIconState()
    val speakerIconState = rememberSpeakerIconState()
    val spellCheckIconState = rememberSpellCheckIconState()
    val sprayCanIconState = rememberSprayCanIconState()
    val sproutIconState = rememberSproutIconState()
    val squareArrowDownIconState = rememberSquareArrowDownIconState()
    val squareArrowDownLeftIconState = rememberSquareArrowDownLeftIconState()
    val squareArrowDownRightIconState = rememberSquareArrowDownRightIconState()
    val squareArrowLeftIconState = rememberSquareArrowLeftIconState()
    val squareArrowOutDownLeftIconState = rememberSquareArrowOutDownLeftIconState()
    val squareArrowOutDownRightIconState = rememberSquareArrowOutDownRightIconState()
    val squareArrowOutUpLeftIconState = rememberSquareArrowOutUpLeftIconState()
    val squareArrowOutUpRightIconState = rememberSquareArrowOutUpRightIconState()
    val squareArrowRightEnterIconState = rememberSquareArrowRightEnterIconState()
    val squareArrowRightExitIconState = rememberSquareArrowRightExitIconState()
    val squareArrowRightIconState = rememberSquareArrowRightIconState()
    val squareArrowUpIconState = rememberSquareArrowUpIconState()
    val squareArrowUpLeftIconState = rememberSquareArrowUpLeftIconState()
    val squareArrowUpRightIconState = rememberSquareArrowUpRightIconState()
    val squareCheckBigIconState = rememberSquareCheckBigIconState()
    val squareCheckIconState = rememberSquareCheckIconState()
    val squareChevronDownIconState = rememberSquareChevronDownIconState()
    val squareChevronLeftIconState = rememberSquareChevronLeftIconState()
    val squareChevronRightIconState = rememberSquareChevronRightIconState()
    val squareChevronUpIconState = rememberSquareChevronUpIconState()
    val squareCodeIconState = rememberSquareCodeIconState()
    val squareDashedIconState = rememberSquareDashedIconState()
    val squareDotIconState = rememberSquareDotIconState()
    val squareIconState = rememberSquareIconState()
    val squareMinusIconState = rememberSquareMinusIconState()
    val squarePauseIconState = rememberSquarePauseIconState()
    val squarePenIconState = rememberSquarePenIconState()
    val squarePlayIconState = rememberSquarePlayIconState()
    val squarePlusIconState = rememberSquarePlusIconState()
    val squareSlashIconState = rememberSquareSlashIconState()
    val squareStopIconState = rememberSquareStopIconState()
    val squareTerminalIconState = rememberSquareTerminalIconState()
    val squareUserIconState = rememberSquareUserIconState()
    val squareUserRoundIconState = rememberSquareUserRoundIconState()
    val squareXIconState = rememberSquareXIconState()
    val stampIconState = rememberStampIconState()
    val starCheckIconState = rememberStarCheckIconState()
    val starHalfIconState = rememberStarHalfIconState()
    val starIconState = rememberStarIconState()
    val starMinusIconState = rememberStarMinusIconState()
    val starOffIconState = rememberStarOffIconState()
    val starPlusIconState = rememberStarPlusIconState()
    val starXIconState = rememberStarXIconState()
    val stepBackIconState = rememberStepBackIconState()
    val stepForwardIconState = rememberStepForwardIconState()
    val stethoscopeIconState = rememberStethoscopeIconState()
    val stickyNoteIconState = rememberStickyNoteIconState()
    val storeIconState = rememberStoreIconState()
    val strikethroughIconState = rememberStrikethroughIconState()
    val subscriptIconState = rememberSubscriptIconState()
    val sunDimIconState = rememberSunDimIconState()
    val sunIconState = rememberSunIconState()
    val sunMediumIconState = rememberSunMediumIconState()
    val sunMoonIconState = rememberSunMoonIconState()
    val sunSnowIconState = rememberSunSnowIconState()
    val sunriseIconState = rememberSunriseIconState()
    val sunsetIconState = rememberSunsetIconState()
    val superscriptIconState = rememberSuperscriptIconState()
    val swissFrancIconState = rememberSwissFrancIconState()
    val swordsIconState = rememberSwordsIconState()
    val tableIconState = rememberTableIconState()
    val tabletIconState = rememberTabletIconState()
    val tagIconState = rememberTagIconState()
    val tagPlusIconState = rememberTagPlusIconState()
    val tagXIconState = rememberTagXIconState()
    val tagsIconState = rememberTagsIconState()
    val targetIconState = rememberTargetIconState()
    val telescopeIconState = rememberTelescopeIconState()
    val tentIconState = rememberTentIconState()
    val terminalIconState = rememberTerminalIconState()
    val textCursorIconState = rememberTextCursorIconState()
    val textInitialIconState = rememberTextInitialIconState()
    val textQuoteIconState = rememberTextQuoteIconState()
    val textSearchIconState = rememberTextSearchIconState()
    val thermometerIconState = rememberThermometerIconState()
    val thermometerSunIconState = rememberThermometerSunIconState()
    val thumbsDownIconState = rememberThumbsDownIconState()
    val thumbsUpIconState = rememberThumbsUpIconState()
    val ticketCheckIconState = rememberTicketCheckIconState()
    val ticketIconState = rememberTicketIconState()
    val ticketMinusIconState = rememberTicketMinusIconState()
    return Triple(
        mapOf(
            "lucide_signal-low" to signalLowIconState,
            "lucide_signal-medium" to signalMediumIconState,
            "lucide_signal-zero" to signalZeroIconState,
            "lucide_signature" to signatureIconState,
            "lucide_signpost-big" to signpostBigIconState,
            "lucide_signpost" to signpostIconState,
            "lucide_siren" to sirenIconState,
            "lucide_skip-back" to skipBackIconState,
            "lucide_skip-forward" to skipForwardIconState,
            "lucide_sliders-horizontal" to slidersHorizontalIconState,
            "lucide_smartphone-charging" to smartphoneChargingIconState,
            "lucide_smartphone" to smartphoneIconState,
            "lucide_smartphone-nfc" to smartphoneNfcIconState,
            "lucide_snowflake" to snowflakeIconState,
            "lucide_sparkles" to sparklesIconState,
            "lucide_speaker" to speakerIconState,
            "lucide_spell-check" to spellCheckIconState,
            "lucide_spray-can" to sprayCanIconState,
            "lucide_sprout" to sproutIconState,
            "lucide_square-arrow-down" to squareArrowDownIconState,
            "lucide_square-arrow-down-left" to squareArrowDownLeftIconState,
            "lucide_square-arrow-down-right" to squareArrowDownRightIconState,
            "lucide_square-arrow-left" to squareArrowLeftIconState,
            "lucide_square-arrow-out-down-left" to squareArrowOutDownLeftIconState,
            "lucide_square-arrow-out-down-right" to squareArrowOutDownRightIconState,
            "lucide_square-arrow-out-up-left" to squareArrowOutUpLeftIconState,
            "lucide_square-arrow-out-up-right" to squareArrowOutUpRightIconState,
            "lucide_square-arrow-right-enter" to squareArrowRightEnterIconState,
            "lucide_square-arrow-right-exit" to squareArrowRightExitIconState,
            "lucide_square-arrow-right" to squareArrowRightIconState,
            "lucide_square-arrow-up" to squareArrowUpIconState,
            "lucide_square-arrow-up-left" to squareArrowUpLeftIconState,
            "lucide_square-arrow-up-right" to squareArrowUpRightIconState,
            "lucide_square-check-big" to squareCheckBigIconState,
            "lucide_square-check" to squareCheckIconState,
            "lucide_square-chevron-down" to squareChevronDownIconState,
            "lucide_square-chevron-left" to squareChevronLeftIconState,
            "lucide_square-chevron-right" to squareChevronRightIconState,
            "lucide_square-chevron-up" to squareChevronUpIconState,
            "lucide_square-code" to squareCodeIconState,
            "lucide_square-dashed" to squareDashedIconState,
            "lucide_square-dot" to squareDotIconState,
            "lucide_square" to squareIconState,
            "lucide_square-minus" to squareMinusIconState,
            "lucide_square-pause" to squarePauseIconState,
            "lucide_square-pen" to squarePenIconState,
            "lucide_square-play" to squarePlayIconState,
            "lucide_square-plus" to squarePlusIconState,
            "lucide_square-slash" to squareSlashIconState,
            "lucide_square-stop" to squareStopIconState,
            "lucide_square-terminal" to squareTerminalIconState,
            "lucide_square-user" to squareUserIconState,
            "lucide_square-user-round" to squareUserRoundIconState,
            "lucide_square-x" to squareXIconState,
            "lucide_stamp" to stampIconState,
            "lucide_star-check" to starCheckIconState,
            "lucide_star-half" to starHalfIconState,
            "lucide_star" to starIconState,
            "lucide_star-minus" to starMinusIconState,
            "lucide_star-off" to starOffIconState,
            "lucide_star-plus" to starPlusIconState,
            "lucide_star-x" to starXIconState,
            "lucide_step-back" to stepBackIconState,
            "lucide_step-forward" to stepForwardIconState,
            "lucide_stethoscope" to stethoscopeIconState,
            "lucide_sticky-note" to stickyNoteIconState,
            "lucide_store" to storeIconState,
            "lucide_strikethrough" to strikethroughIconState,
            "lucide_subscript" to subscriptIconState,
            "lucide_sun-dim" to sunDimIconState,
            "lucide_sun" to sunIconState,
            "lucide_sun-medium" to sunMediumIconState,
            "lucide_sun-moon" to sunMoonIconState,
            "lucide_sun-snow" to sunSnowIconState,
            "lucide_sunrise" to sunriseIconState,
            "lucide_sunset" to sunsetIconState,
            "lucide_superscript" to superscriptIconState,
            "lucide_swiss-franc" to swissFrancIconState,
            "lucide_swords" to swordsIconState,
            "lucide_table" to tableIconState,
            "lucide_tablet" to tabletIconState,
            "lucide_tag" to tagIconState,
            "lucide_tag-plus" to tagPlusIconState,
            "lucide_tag-x" to tagXIconState,
            "lucide_tags" to tagsIconState,
            "lucide_target" to targetIconState,
            "lucide_telescope" to telescopeIconState,
            "lucide_tent" to tentIconState,
            "lucide_terminal" to terminalIconState,
            "lucide_text-cursor" to textCursorIconState,
            "lucide_text-initial" to textInitialIconState,
            "lucide_text-quote" to textQuoteIconState,
            "lucide_text-search" to textSearchIconState,
            "lucide_thermometer" to thermometerIconState,
            "lucide_thermometer-sun" to thermometerSunIconState,
            "lucide_thumbs-down" to thumbsDownIconState,
            "lucide_thumbs-up" to thumbsUpIconState,
            "lucide_ticket-check" to ticketCheckIconState,
            "lucide_ticket" to ticketIconState,
            "lucide_ticket-minus" to ticketMinusIconState,
        ),
        mapOf(
            "lucide_signal-low" to { signalLowIconState.startAnimation() },
            "lucide_signal-medium" to { signalMediumIconState.startAnimation() },
            "lucide_signal-zero" to { signalZeroIconState.startAnimation() },
            "lucide_signature" to { signatureIconState.startAnimation() },
            "lucide_signpost-big" to { signpostBigIconState.startAnimation() },
            "lucide_signpost" to { signpostIconState.startAnimation() },
            "lucide_siren" to { sirenIconState.startAnimation() },
            "lucide_skip-back" to { skipBackIconState.startAnimation() },
            "lucide_skip-forward" to { skipForwardIconState.startAnimation() },
            "lucide_sliders-horizontal" to { slidersHorizontalIconState.startAnimation() },
            "lucide_smartphone-charging" to { smartphoneChargingIconState.startAnimation() },
            "lucide_smartphone" to { smartphoneIconState.startAnimation() },
            "lucide_smartphone-nfc" to { smartphoneNfcIconState.startAnimation() },
            "lucide_snowflake" to { snowflakeIconState.startAnimation() },
            "lucide_sparkles" to { sparklesIconState.startAnimation() },
            "lucide_speaker" to { speakerIconState.startAnimation() },
            "lucide_spell-check" to { spellCheckIconState.startAnimation() },
            "lucide_spray-can" to { sprayCanIconState.startAnimation() },
            "lucide_sprout" to { sproutIconState.startAnimation() },
            "lucide_square-arrow-down" to { squareArrowDownIconState.startAnimation() },
            "lucide_square-arrow-down-left" to { squareArrowDownLeftIconState.startAnimation() },
            "lucide_square-arrow-down-right" to { squareArrowDownRightIconState.startAnimation() },
            "lucide_square-arrow-left" to { squareArrowLeftIconState.startAnimation() },
            "lucide_square-arrow-out-down-left" to { squareArrowOutDownLeftIconState.startAnimation() },
            "lucide_square-arrow-out-down-right" to { squareArrowOutDownRightIconState.startAnimation() },
            "lucide_square-arrow-out-up-left" to { squareArrowOutUpLeftIconState.startAnimation() },
            "lucide_square-arrow-out-up-right" to { squareArrowOutUpRightIconState.startAnimation() },
            "lucide_square-arrow-right-enter" to { squareArrowRightEnterIconState.startAnimation() },
            "lucide_square-arrow-right-exit" to { squareArrowRightExitIconState.startAnimation() },
            "lucide_square-arrow-right" to { squareArrowRightIconState.startAnimation() },
            "lucide_square-arrow-up" to { squareArrowUpIconState.startAnimation() },
            "lucide_square-arrow-up-left" to { squareArrowUpLeftIconState.startAnimation() },
            "lucide_square-arrow-up-right" to { squareArrowUpRightIconState.startAnimation() },
            "lucide_square-check-big" to { squareCheckBigIconState.startAnimation() },
            "lucide_square-check" to { squareCheckIconState.startAnimation() },
            "lucide_square-chevron-down" to { squareChevronDownIconState.startAnimation() },
            "lucide_square-chevron-left" to { squareChevronLeftIconState.startAnimation() },
            "lucide_square-chevron-right" to { squareChevronRightIconState.startAnimation() },
            "lucide_square-chevron-up" to { squareChevronUpIconState.startAnimation() },
            "lucide_square-code" to { squareCodeIconState.startAnimation() },
            "lucide_square-dashed" to { squareDashedIconState.startAnimation() },
            "lucide_square-dot" to { squareDotIconState.startAnimation() },
            "lucide_square" to { squareIconState.startAnimation() },
            "lucide_square-minus" to { squareMinusIconState.startAnimation() },
            "lucide_square-pause" to { squarePauseIconState.startAnimation() },
            "lucide_square-pen" to { squarePenIconState.startAnimation() },
            "lucide_square-play" to { squarePlayIconState.startAnimation() },
            "lucide_square-plus" to { squarePlusIconState.startAnimation() },
            "lucide_square-slash" to { squareSlashIconState.startAnimation() },
            "lucide_square-stop" to { squareStopIconState.startAnimation() },
            "lucide_square-terminal" to { squareTerminalIconState.startAnimation() },
            "lucide_square-user" to { squareUserIconState.startAnimation() },
            "lucide_square-user-round" to { squareUserRoundIconState.startAnimation() },
            "lucide_square-x" to { squareXIconState.startAnimation() },
            "lucide_stamp" to { stampIconState.startAnimation() },
            "lucide_star-check" to { starCheckIconState.startAnimation() },
            "lucide_star-half" to { starHalfIconState.startAnimation() },
            "lucide_star" to { starIconState.startAnimation() },
            "lucide_star-minus" to { starMinusIconState.startAnimation() },
            "lucide_star-off" to { starOffIconState.startAnimation() },
            "lucide_star-plus" to { starPlusIconState.startAnimation() },
            "lucide_star-x" to { starXIconState.startAnimation() },
            "lucide_step-back" to { stepBackIconState.startAnimation() },
            "lucide_step-forward" to { stepForwardIconState.startAnimation() },
            "lucide_stethoscope" to { stethoscopeIconState.startAnimation() },
            "lucide_sticky-note" to { stickyNoteIconState.startAnimation() },
            "lucide_store" to { storeIconState.startAnimation() },
            "lucide_strikethrough" to { strikethroughIconState.startAnimation() },
            "lucide_subscript" to { subscriptIconState.startAnimation() },
            "lucide_sun-dim" to { sunDimIconState.startAnimation() },
            "lucide_sun" to { sunIconState.startAnimation() },
            "lucide_sun-medium" to { sunMediumIconState.startAnimation() },
            "lucide_sun-moon" to { sunMoonIconState.startAnimation() },
            "lucide_sun-snow" to { sunSnowIconState.startAnimation() },
            "lucide_sunrise" to { sunriseIconState.startAnimation() },
            "lucide_sunset" to { sunsetIconState.startAnimation() },
            "lucide_superscript" to { superscriptIconState.startAnimation() },
            "lucide_swiss-franc" to { swissFrancIconState.startAnimation() },
            "lucide_swords" to { swordsIconState.startAnimation() },
            "lucide_table" to { tableIconState.startAnimation() },
            "lucide_tablet" to { tabletIconState.startAnimation() },
            "lucide_tag" to { tagIconState.startAnimation() },
            "lucide_tag-plus" to { tagPlusIconState.startAnimation() },
            "lucide_tag-x" to { tagXIconState.startAnimation() },
            "lucide_tags" to { tagsIconState.startAnimation() },
            "lucide_target" to { targetIconState.startAnimation() },
            "lucide_telescope" to { telescopeIconState.startAnimation() },
            "lucide_tent" to { tentIconState.startAnimation() },
            "lucide_terminal" to { terminalIconState.startAnimation() },
            "lucide_text-cursor" to { textCursorIconState.startAnimation() },
            "lucide_text-initial" to { textInitialIconState.startAnimation() },
            "lucide_text-quote" to { textQuoteIconState.startAnimation() },
            "lucide_text-search" to { textSearchIconState.startAnimation() },
            "lucide_thermometer" to { thermometerIconState.startAnimation() },
            "lucide_thermometer-sun" to { thermometerSunIconState.startAnimation() },
            "lucide_thumbs-down" to { thumbsDownIconState.startAnimation() },
            "lucide_thumbs-up" to { thumbsUpIconState.startAnimation() },
            "lucide_ticket-check" to { ticketCheckIconState.startAnimation() },
            "lucide_ticket" to { ticketIconState.startAnimation() },
            "lucide_ticket-minus" to { ticketMinusIconState.startAnimation() },
        ),
        mapOf(
            "lucide_signal-low" to { signalLowIconState.stopAnimation() },
            "lucide_signal-medium" to { signalMediumIconState.stopAnimation() },
            "lucide_signal-zero" to { signalZeroIconState.stopAnimation() },
            "lucide_signature" to { signatureIconState.stopAnimation() },
            "lucide_signpost-big" to { signpostBigIconState.stopAnimation() },
            "lucide_signpost" to { signpostIconState.stopAnimation() },
            "lucide_siren" to { sirenIconState.stopAnimation() },
            "lucide_skip-back" to { skipBackIconState.stopAnimation() },
            "lucide_skip-forward" to { skipForwardIconState.stopAnimation() },
            "lucide_sliders-horizontal" to { slidersHorizontalIconState.stopAnimation() },
            "lucide_smartphone-charging" to { smartphoneChargingIconState.stopAnimation() },
            "lucide_smartphone" to { smartphoneIconState.stopAnimation() },
            "lucide_smartphone-nfc" to { smartphoneNfcIconState.stopAnimation() },
            "lucide_snowflake" to { snowflakeIconState.stopAnimation() },
            "lucide_sparkles" to { sparklesIconState.stopAnimation() },
            "lucide_speaker" to { speakerIconState.stopAnimation() },
            "lucide_spell-check" to { spellCheckIconState.stopAnimation() },
            "lucide_spray-can" to { sprayCanIconState.stopAnimation() },
            "lucide_sprout" to { sproutIconState.stopAnimation() },
            "lucide_square-arrow-down" to { squareArrowDownIconState.stopAnimation() },
            "lucide_square-arrow-down-left" to { squareArrowDownLeftIconState.stopAnimation() },
            "lucide_square-arrow-down-right" to { squareArrowDownRightIconState.stopAnimation() },
            "lucide_square-arrow-left" to { squareArrowLeftIconState.stopAnimation() },
            "lucide_square-arrow-out-down-left" to { squareArrowOutDownLeftIconState.stopAnimation() },
            "lucide_square-arrow-out-down-right" to { squareArrowOutDownRightIconState.stopAnimation() },
            "lucide_square-arrow-out-up-left" to { squareArrowOutUpLeftIconState.stopAnimation() },
            "lucide_square-arrow-out-up-right" to { squareArrowOutUpRightIconState.stopAnimation() },
            "lucide_square-arrow-right-enter" to { squareArrowRightEnterIconState.stopAnimation() },
            "lucide_square-arrow-right-exit" to { squareArrowRightExitIconState.stopAnimation() },
            "lucide_square-arrow-right" to { squareArrowRightIconState.stopAnimation() },
            "lucide_square-arrow-up" to { squareArrowUpIconState.stopAnimation() },
            "lucide_square-arrow-up-left" to { squareArrowUpLeftIconState.stopAnimation() },
            "lucide_square-arrow-up-right" to { squareArrowUpRightIconState.stopAnimation() },
            "lucide_square-check-big" to { squareCheckBigIconState.stopAnimation() },
            "lucide_square-check" to { squareCheckIconState.stopAnimation() },
            "lucide_square-chevron-down" to { squareChevronDownIconState.stopAnimation() },
            "lucide_square-chevron-left" to { squareChevronLeftIconState.stopAnimation() },
            "lucide_square-chevron-right" to { squareChevronRightIconState.stopAnimation() },
            "lucide_square-chevron-up" to { squareChevronUpIconState.stopAnimation() },
            "lucide_square-code" to { squareCodeIconState.stopAnimation() },
            "lucide_square-dashed" to { squareDashedIconState.stopAnimation() },
            "lucide_square-dot" to { squareDotIconState.stopAnimation() },
            "lucide_square" to { squareIconState.stopAnimation() },
            "lucide_square-minus" to { squareMinusIconState.stopAnimation() },
            "lucide_square-pause" to { squarePauseIconState.stopAnimation() },
            "lucide_square-pen" to { squarePenIconState.stopAnimation() },
            "lucide_square-play" to { squarePlayIconState.stopAnimation() },
            "lucide_square-plus" to { squarePlusIconState.stopAnimation() },
            "lucide_square-slash" to { squareSlashIconState.stopAnimation() },
            "lucide_square-stop" to { squareStopIconState.stopAnimation() },
            "lucide_square-terminal" to { squareTerminalIconState.stopAnimation() },
            "lucide_square-user" to { squareUserIconState.stopAnimation() },
            "lucide_square-user-round" to { squareUserRoundIconState.stopAnimation() },
            "lucide_square-x" to { squareXIconState.stopAnimation() },
            "lucide_stamp" to { stampIconState.stopAnimation() },
            "lucide_star-check" to { starCheckIconState.stopAnimation() },
            "lucide_star-half" to { starHalfIconState.stopAnimation() },
            "lucide_star" to { starIconState.stopAnimation() },
            "lucide_star-minus" to { starMinusIconState.stopAnimation() },
            "lucide_star-off" to { starOffIconState.stopAnimation() },
            "lucide_star-plus" to { starPlusIconState.stopAnimation() },
            "lucide_star-x" to { starXIconState.stopAnimation() },
            "lucide_step-back" to { stepBackIconState.stopAnimation() },
            "lucide_step-forward" to { stepForwardIconState.stopAnimation() },
            "lucide_stethoscope" to { stethoscopeIconState.stopAnimation() },
            "lucide_sticky-note" to { stickyNoteIconState.stopAnimation() },
            "lucide_store" to { storeIconState.stopAnimation() },
            "lucide_strikethrough" to { strikethroughIconState.stopAnimation() },
            "lucide_subscript" to { subscriptIconState.stopAnimation() },
            "lucide_sun-dim" to { sunDimIconState.stopAnimation() },
            "lucide_sun" to { sunIconState.stopAnimation() },
            "lucide_sun-medium" to { sunMediumIconState.stopAnimation() },
            "lucide_sun-moon" to { sunMoonIconState.stopAnimation() },
            "lucide_sun-snow" to { sunSnowIconState.stopAnimation() },
            "lucide_sunrise" to { sunriseIconState.stopAnimation() },
            "lucide_sunset" to { sunsetIconState.stopAnimation() },
            "lucide_superscript" to { superscriptIconState.stopAnimation() },
            "lucide_swiss-franc" to { swissFrancIconState.stopAnimation() },
            "lucide_swords" to { swordsIconState.stopAnimation() },
            "lucide_table" to { tableIconState.stopAnimation() },
            "lucide_tablet" to { tabletIconState.stopAnimation() },
            "lucide_tag" to { tagIconState.stopAnimation() },
            "lucide_tag-plus" to { tagPlusIconState.stopAnimation() },
            "lucide_tag-x" to { tagXIconState.stopAnimation() },
            "lucide_tags" to { tagsIconState.stopAnimation() },
            "lucide_target" to { targetIconState.stopAnimation() },
            "lucide_telescope" to { telescopeIconState.stopAnimation() },
            "lucide_tent" to { tentIconState.stopAnimation() },
            "lucide_terminal" to { terminalIconState.stopAnimation() },
            "lucide_text-cursor" to { textCursorIconState.stopAnimation() },
            "lucide_text-initial" to { textInitialIconState.stopAnimation() },
            "lucide_text-quote" to { textQuoteIconState.stopAnimation() },
            "lucide_text-search" to { textSearchIconState.stopAnimation() },
            "lucide_thermometer" to { thermometerIconState.stopAnimation() },
            "lucide_thermometer-sun" to { thermometerSunIconState.stopAnimation() },
            "lucide_thumbs-down" to { thumbsDownIconState.stopAnimation() },
            "lucide_thumbs-up" to { thumbsUpIconState.stopAnimation() },
            "lucide_ticket-check" to { ticketCheckIconState.stopAnimation() },
            "lucide_ticket" to { ticketIconState.stopAnimation() },
            "lucide_ticket-minus" to { ticketMinusIconState.stopAnimation() },
        ),
    )
}

@Composable
private fun rememberLucideStatesChunk9(): Triple<Map<String, Any>, Map<String, () -> Unit>, Map<String, () -> Unit>> {
    val ticketPercentIconState = rememberTicketPercentIconState()
    val ticketPlusIconState = rememberTicketPlusIconState()
    val ticketSlashIconState = rememberTicketSlashIconState()
    val ticketXIconState = rememberTicketXIconState()
    val timerIconState = rememberTimerIconState()
    val timerOffIconState = rememberTimerOffIconState()
    val timerResetIconState = rememberTimerResetIconState()
    val toggleLeftIconState = rememberToggleLeftIconState()
    val toggleRightIconState = rememberToggleRightIconState()
    val tornadoIconState = rememberTornadoIconState()
    val trafficConeIconState = rememberTrafficConeIconState()
    val trash2IconState = rememberTrash2IconState()
    val trashIconState = rememberTrashIconState()
    val trendingDownIconState = rememberTrendingDownIconState()
    val trendingUpDownIconState = rememberTrendingUpDownIconState()
    val trendingUpIconState = rememberTrendingUpIconState()
    val triangleAlertIconState = rememberTriangleAlertIconState()
    val triangleDashedIconState = rememberTriangleDashedIconState()
    val triangleIconState = rememberTriangleIconState()
    val triangleRightIconState = rememberTriangleRightIconState()
    val trophyIconState = rememberTrophyIconState()
    val truckIconState = rememberTruckIconState()
    val turkishLiraIconState = rememberTurkishLiraIconState()
    val tvIconState = rememberTvIconState()
    val tvMinimalIconState = rememberTvMinimalIconState()
    val twitterIconState = rememberTwitterIconState()
    val typeIconState = rememberTypeIconState()
    val umbrellaIconState = rememberUmbrellaIconState()
    val underlineIconState = rememberUnderlineIconState()
    val undoIconState = rememberUndoIconState()
    val unfoldVerticalIconState = rememberUnfoldVerticalIconState()
    val universityIconState = rememberUniversityIconState()
    val unlinkIconState = rememberUnlinkIconState()
    val uploadIconState = rememberUploadIconState()
    val usbIconState = rememberUsbIconState()
    val userCheckIconState = rememberUserCheckIconState()
    val userCogIconState = rememberUserCogIconState()
    val userGroupIconState = rememberUserGroupIconState()
    val userIconState = rememberUserIconState()
    val userKeyIconState = rememberUserKeyIconState()
    val userLockIconState = rememberUserLockIconState()
    val userMinusIconState = rememberUserMinusIconState()
    val userPenIconState = rememberUserPenIconState()
    val userPlusIconState = rememberUserPlusIconState()
    val userRoundArrowLeftIconState = rememberUserRoundArrowLeftIconState()
    val userRoundCheckIconState = rememberUserRoundCheckIconState()
    val userRoundCogIconState = rememberUserRoundCogIconState()
    val userRoundGroupIconState = rememberUserRoundGroupIconState()
    val userRoundIconState = rememberUserRoundIconState()
    val userRoundKeyIconState = rememberUserRoundKeyIconState()
    val userRoundMinusIconState = rememberUserRoundMinusIconState()
    val userRoundPenIconState = rememberUserRoundPenIconState()
    val userRoundPlusIconState = rememberUserRoundPlusIconState()
    val userRoundSearchIconState = rememberUserRoundSearchIconState()
    val userRoundXIconState = rememberUserRoundXIconState()
    val userSearchIconState = rememberUserSearchIconState()
    val userShieldIconState = rememberUserShieldIconState()
    val userStarIconState = rememberUserStarIconState()
    val userXIconState = rememberUserXIconState()
    val usersIconState = rememberUsersIconState()
    val usersRoundIconState = rememberUsersRoundIconState()
    val utensilsIconState = rememberUtensilsIconState()
    val variableIconState = rememberVariableIconState()
    val vaultIconState = rememberVaultIconState()
    val venusIconState = rememberVenusIconState()
    val videoIconState = rememberVideoIconState()
    val videoOffIconState = rememberVideoOffIconState()
    val volume1IconState = rememberVolume1IconState()
    val volume2IconState = rememberVolume2IconState()
    val volumeIconState = rememberVolumeIconState()
    val volumeOffIconState = rememberVolumeOffIconState()
    val volumeXIconState = rememberVolumeXIconState()
    val walletCardsIconState = rememberWalletCardsIconState()
    val walletIconState = rememberWalletIconState()
    val walletMinimalIconState = rememberWalletMinimalIconState()
    val wandSparklesIconState = rememberWandSparklesIconState()
    val warehouseIconState = rememberWarehouseIconState()
    val watchIconState = rememberWatchIconState()
    val waypointsIconState = rememberWaypointsIconState()
    val webcamIconState = rememberWebcamIconState()
    val webcamOffIconState = rememberWebcamOffIconState()
    val webhookIconState = rememberWebhookIconState()
    val webhookOffIconState = rememberWebhookOffIconState()
    val wifiCogIconState = rememberWifiCogIconState()
    val wifiHighIconState = rememberWifiHighIconState()
    val wifiIconState = rememberWifiIconState()
    val wifiLowIconState = rememberWifiLowIconState()
    val wifiOffIconState = rememberWifiOffIconState()
    val wifiPenIconState = rememberWifiPenIconState()
    val wifiSyncIconState = rememberWifiSyncIconState()
    val wifiZeroIconState = rememberWifiZeroIconState()
    val windIconState = rememberWindIconState()
    val workflowIconState = rememberWorkflowIconState()
    val wrapTextIconState = rememberWrapTextIconState()
    val wrenchIconState = rememberWrenchIconState()
    val xIconState = rememberXIconState()
    val zapIconState = rememberZapIconState()
    val zoomInIconState = rememberZoomInIconState()
    val zoomOutIconState = rememberZoomOutIconState()
    return Triple(
        mapOf(
            "lucide_ticket-percent" to ticketPercentIconState,
            "lucide_ticket-plus" to ticketPlusIconState,
            "lucide_ticket-slash" to ticketSlashIconState,
            "lucide_ticket-x" to ticketXIconState,
            "lucide_timer" to timerIconState,
            "lucide_timer-off" to timerOffIconState,
            "lucide_timer-reset" to timerResetIconState,
            "lucide_toggle-left" to toggleLeftIconState,
            "lucide_toggle-right" to toggleRightIconState,
            "lucide_tornado" to tornadoIconState,
            "lucide_traffic-cone" to trafficConeIconState,
            "lucide_trash-2" to trash2IconState,
            "lucide_trash" to trashIconState,
            "lucide_trending-down" to trendingDownIconState,
            "lucide_trending-up-down" to trendingUpDownIconState,
            "lucide_trending-up" to trendingUpIconState,
            "lucide_triangle-alert" to triangleAlertIconState,
            "lucide_triangle-dashed" to triangleDashedIconState,
            "lucide_triangle" to triangleIconState,
            "lucide_triangle-right" to triangleRightIconState,
            "lucide_trophy" to trophyIconState,
            "lucide_truck" to truckIconState,
            "lucide_turkish-lira" to turkishLiraIconState,
            "lucide_tv" to tvIconState,
            "lucide_tv-minimal" to tvMinimalIconState,
            "lucide_twitter" to twitterIconState,
            "lucide_type" to typeIconState,
            "lucide_umbrella" to umbrellaIconState,
            "lucide_underline" to underlineIconState,
            "lucide_undo" to undoIconState,
            "lucide_unfold-vertical" to unfoldVerticalIconState,
            "lucide_university" to universityIconState,
            "lucide_unlink" to unlinkIconState,
            "lucide_upload" to uploadIconState,
            "lucide_usb" to usbIconState,
            "lucide_user-check" to userCheckIconState,
            "lucide_user-cog" to userCogIconState,
            "lucide_user-group" to userGroupIconState,
            "lucide_user" to userIconState,
            "lucide_user-key" to userKeyIconState,
            "lucide_user-lock" to userLockIconState,
            "lucide_user-minus" to userMinusIconState,
            "lucide_user-pen" to userPenIconState,
            "lucide_user-plus" to userPlusIconState,
            "lucide_user-round-arrow-left" to userRoundArrowLeftIconState,
            "lucide_user-round-check" to userRoundCheckIconState,
            "lucide_user-round-cog" to userRoundCogIconState,
            "lucide_user-round-group" to userRoundGroupIconState,
            "lucide_user-round" to userRoundIconState,
            "lucide_user-round-key" to userRoundKeyIconState,
            "lucide_user-round-minus" to userRoundMinusIconState,
            "lucide_user-round-pen" to userRoundPenIconState,
            "lucide_user-round-plus" to userRoundPlusIconState,
            "lucide_user-round-search" to userRoundSearchIconState,
            "lucide_user-round-x" to userRoundXIconState,
            "lucide_user-search" to userSearchIconState,
            "lucide_user-shield" to userShieldIconState,
            "lucide_user-star" to userStarIconState,
            "lucide_user-x" to userXIconState,
            "lucide_users" to usersIconState,
            "lucide_users-round" to usersRoundIconState,
            "lucide_utensils" to utensilsIconState,
            "lucide_variable" to variableIconState,
            "lucide_vault" to vaultIconState,
            "lucide_venus" to venusIconState,
            "lucide_video" to videoIconState,
            "lucide_video-off" to videoOffIconState,
            "lucide_volume-1" to volume1IconState,
            "lucide_volume-2" to volume2IconState,
            "lucide_volume" to volumeIconState,
            "lucide_volume-off" to volumeOffIconState,
            "lucide_volume-x" to volumeXIconState,
            "lucide_wallet-cards" to walletCardsIconState,
            "lucide_wallet" to walletIconState,
            "lucide_wallet-minimal" to walletMinimalIconState,
            "lucide_wand-sparkles" to wandSparklesIconState,
            "lucide_warehouse" to warehouseIconState,
            "lucide_watch" to watchIconState,
            "lucide_waypoints" to waypointsIconState,
            "lucide_webcam" to webcamIconState,
            "lucide_webcam-off" to webcamOffIconState,
            "lucide_webhook" to webhookIconState,
            "lucide_webhook-off" to webhookOffIconState,
            "lucide_wifi-cog" to wifiCogIconState,
            "lucide_wifi-high" to wifiHighIconState,
            "lucide_wifi" to wifiIconState,
            "lucide_wifi-low" to wifiLowIconState,
            "lucide_wifi-off" to wifiOffIconState,
            "lucide_wifi-pen" to wifiPenIconState,
            "lucide_wifi-sync" to wifiSyncIconState,
            "lucide_wifi-zero" to wifiZeroIconState,
            "lucide_wind" to windIconState,
            "lucide_workflow" to workflowIconState,
            "lucide_wrap-text" to wrapTextIconState,
            "lucide_wrench" to wrenchIconState,
            "lucide_x" to xIconState,
            "lucide_zap" to zapIconState,
            "lucide_zoom-in" to zoomInIconState,
            "lucide_zoom-out" to zoomOutIconState,
        ),
        mapOf(
            "lucide_ticket-percent" to { ticketPercentIconState.startAnimation() },
            "lucide_ticket-plus" to { ticketPlusIconState.startAnimation() },
            "lucide_ticket-slash" to { ticketSlashIconState.startAnimation() },
            "lucide_ticket-x" to { ticketXIconState.startAnimation() },
            "lucide_timer" to { timerIconState.startAnimation() },
            "lucide_timer-off" to { timerOffIconState.startAnimation() },
            "lucide_timer-reset" to { timerResetIconState.startAnimation() },
            "lucide_toggle-left" to { toggleLeftIconState.startAnimation() },
            "lucide_toggle-right" to { toggleRightIconState.startAnimation() },
            "lucide_tornado" to { tornadoIconState.startAnimation() },
            "lucide_traffic-cone" to { trafficConeIconState.startAnimation() },
            "lucide_trash-2" to { trash2IconState.startAnimation() },
            "lucide_trash" to { trashIconState.startAnimation() },
            "lucide_trending-down" to { trendingDownIconState.startAnimation() },
            "lucide_trending-up-down" to { trendingUpDownIconState.startAnimation() },
            "lucide_trending-up" to { trendingUpIconState.startAnimation() },
            "lucide_triangle-alert" to { triangleAlertIconState.startAnimation() },
            "lucide_triangle-dashed" to { triangleDashedIconState.startAnimation() },
            "lucide_triangle" to { triangleIconState.startAnimation() },
            "lucide_triangle-right" to { triangleRightIconState.startAnimation() },
            "lucide_trophy" to { trophyIconState.startAnimation() },
            "lucide_truck" to { truckIconState.startAnimation() },
            "lucide_turkish-lira" to { turkishLiraIconState.startAnimation() },
            "lucide_tv" to { tvIconState.startAnimation() },
            "lucide_tv-minimal" to { tvMinimalIconState.startAnimation() },
            "lucide_twitter" to { twitterIconState.startAnimation() },
            "lucide_type" to { typeIconState.startAnimation() },
            "lucide_umbrella" to { umbrellaIconState.startAnimation() },
            "lucide_underline" to { underlineIconState.startAnimation() },
            "lucide_undo" to { undoIconState.startAnimation() },
            "lucide_unfold-vertical" to { unfoldVerticalIconState.startAnimation() },
            "lucide_university" to { universityIconState.startAnimation() },
            "lucide_unlink" to { unlinkIconState.startAnimation() },
            "lucide_upload" to { uploadIconState.startAnimation() },
            "lucide_usb" to { usbIconState.startAnimation() },
            "lucide_user-check" to { userCheckIconState.startAnimation() },
            "lucide_user-cog" to { userCogIconState.startAnimation() },
            "lucide_user-group" to { userGroupIconState.startAnimation() },
            "lucide_user" to { userIconState.startAnimation() },
            "lucide_user-key" to { userKeyIconState.startAnimation() },
            "lucide_user-lock" to { userLockIconState.startAnimation() },
            "lucide_user-minus" to { userMinusIconState.startAnimation() },
            "lucide_user-pen" to { userPenIconState.startAnimation() },
            "lucide_user-plus" to { userPlusIconState.startAnimation() },
            "lucide_user-round-arrow-left" to { userRoundArrowLeftIconState.startAnimation() },
            "lucide_user-round-check" to { userRoundCheckIconState.startAnimation() },
            "lucide_user-round-cog" to { userRoundCogIconState.startAnimation() },
            "lucide_user-round-group" to { userRoundGroupIconState.startAnimation() },
            "lucide_user-round" to { userRoundIconState.startAnimation() },
            "lucide_user-round-key" to { userRoundKeyIconState.startAnimation() },
            "lucide_user-round-minus" to { userRoundMinusIconState.startAnimation() },
            "lucide_user-round-pen" to { userRoundPenIconState.startAnimation() },
            "lucide_user-round-plus" to { userRoundPlusIconState.startAnimation() },
            "lucide_user-round-search" to { userRoundSearchIconState.startAnimation() },
            "lucide_user-round-x" to { userRoundXIconState.startAnimation() },
            "lucide_user-search" to { userSearchIconState.startAnimation() },
            "lucide_user-shield" to { userShieldIconState.startAnimation() },
            "lucide_user-star" to { userStarIconState.startAnimation() },
            "lucide_user-x" to { userXIconState.startAnimation() },
            "lucide_users" to { usersIconState.startAnimation() },
            "lucide_users-round" to { usersRoundIconState.startAnimation() },
            "lucide_utensils" to { utensilsIconState.startAnimation() },
            "lucide_variable" to { variableIconState.startAnimation() },
            "lucide_vault" to { vaultIconState.startAnimation() },
            "lucide_venus" to { venusIconState.startAnimation() },
            "lucide_video" to { videoIconState.startAnimation() },
            "lucide_video-off" to { videoOffIconState.startAnimation() },
            "lucide_volume-1" to { volume1IconState.startAnimation() },
            "lucide_volume-2" to { volume2IconState.startAnimation() },
            "lucide_volume" to { volumeIconState.startAnimation() },
            "lucide_volume-off" to { volumeOffIconState.startAnimation() },
            "lucide_volume-x" to { volumeXIconState.startAnimation() },
            "lucide_wallet-cards" to { walletCardsIconState.startAnimation() },
            "lucide_wallet" to { walletIconState.startAnimation() },
            "lucide_wallet-minimal" to { walletMinimalIconState.startAnimation() },
            "lucide_wand-sparkles" to { wandSparklesIconState.startAnimation() },
            "lucide_warehouse" to { warehouseIconState.startAnimation() },
            "lucide_watch" to { watchIconState.startAnimation() },
            "lucide_waypoints" to { waypointsIconState.startAnimation() },
            "lucide_webcam" to { webcamIconState.startAnimation() },
            "lucide_webcam-off" to { webcamOffIconState.startAnimation() },
            "lucide_webhook" to { webhookIconState.startAnimation() },
            "lucide_webhook-off" to { webhookOffIconState.startAnimation() },
            "lucide_wifi-cog" to { wifiCogIconState.startAnimation() },
            "lucide_wifi-high" to { wifiHighIconState.startAnimation() },
            "lucide_wifi" to { wifiIconState.startAnimation() },
            "lucide_wifi-low" to { wifiLowIconState.startAnimation() },
            "lucide_wifi-off" to { wifiOffIconState.startAnimation() },
            "lucide_wifi-pen" to { wifiPenIconState.startAnimation() },
            "lucide_wifi-sync" to { wifiSyncIconState.startAnimation() },
            "lucide_wifi-zero" to { wifiZeroIconState.startAnimation() },
            "lucide_wind" to { windIconState.startAnimation() },
            "lucide_workflow" to { workflowIconState.startAnimation() },
            "lucide_wrap-text" to { wrapTextIconState.startAnimation() },
            "lucide_wrench" to { wrenchIconState.startAnimation() },
            "lucide_x" to { xIconState.startAnimation() },
            "lucide_zap" to { zapIconState.startAnimation() },
            "lucide_zoom-in" to { zoomInIconState.startAnimation() },
            "lucide_zoom-out" to { zoomOutIconState.startAnimation() },
        ),
        mapOf(
            "lucide_ticket-percent" to { ticketPercentIconState.stopAnimation() },
            "lucide_ticket-plus" to { ticketPlusIconState.stopAnimation() },
            "lucide_ticket-slash" to { ticketSlashIconState.stopAnimation() },
            "lucide_ticket-x" to { ticketXIconState.stopAnimation() },
            "lucide_timer" to { timerIconState.stopAnimation() },
            "lucide_timer-off" to { timerOffIconState.stopAnimation() },
            "lucide_timer-reset" to { timerResetIconState.stopAnimation() },
            "lucide_toggle-left" to { toggleLeftIconState.stopAnimation() },
            "lucide_toggle-right" to { toggleRightIconState.stopAnimation() },
            "lucide_tornado" to { tornadoIconState.stopAnimation() },
            "lucide_traffic-cone" to { trafficConeIconState.stopAnimation() },
            "lucide_trash-2" to { trash2IconState.stopAnimation() },
            "lucide_trash" to { trashIconState.stopAnimation() },
            "lucide_trending-down" to { trendingDownIconState.stopAnimation() },
            "lucide_trending-up-down" to { trendingUpDownIconState.stopAnimation() },
            "lucide_trending-up" to { trendingUpIconState.stopAnimation() },
            "lucide_triangle-alert" to { triangleAlertIconState.stopAnimation() },
            "lucide_triangle-dashed" to { triangleDashedIconState.stopAnimation() },
            "lucide_triangle" to { triangleIconState.stopAnimation() },
            "lucide_triangle-right" to { triangleRightIconState.stopAnimation() },
            "lucide_trophy" to { trophyIconState.stopAnimation() },
            "lucide_truck" to { truckIconState.stopAnimation() },
            "lucide_turkish-lira" to { turkishLiraIconState.stopAnimation() },
            "lucide_tv" to { tvIconState.stopAnimation() },
            "lucide_tv-minimal" to { tvMinimalIconState.stopAnimation() },
            "lucide_twitter" to { twitterIconState.stopAnimation() },
            "lucide_type" to { typeIconState.stopAnimation() },
            "lucide_umbrella" to { umbrellaIconState.stopAnimation() },
            "lucide_underline" to { underlineIconState.stopAnimation() },
            "lucide_undo" to { undoIconState.stopAnimation() },
            "lucide_unfold-vertical" to { unfoldVerticalIconState.stopAnimation() },
            "lucide_university" to { universityIconState.stopAnimation() },
            "lucide_unlink" to { unlinkIconState.stopAnimation() },
            "lucide_upload" to { uploadIconState.stopAnimation() },
            "lucide_usb" to { usbIconState.stopAnimation() },
            "lucide_user-check" to { userCheckIconState.stopAnimation() },
            "lucide_user-cog" to { userCogIconState.stopAnimation() },
            "lucide_user-group" to { userGroupIconState.stopAnimation() },
            "lucide_user" to { userIconState.stopAnimation() },
            "lucide_user-key" to { userKeyIconState.stopAnimation() },
            "lucide_user-lock" to { userLockIconState.stopAnimation() },
            "lucide_user-minus" to { userMinusIconState.stopAnimation() },
            "lucide_user-pen" to { userPenIconState.stopAnimation() },
            "lucide_user-plus" to { userPlusIconState.stopAnimation() },
            "lucide_user-round-arrow-left" to { userRoundArrowLeftIconState.stopAnimation() },
            "lucide_user-round-check" to { userRoundCheckIconState.stopAnimation() },
            "lucide_user-round-cog" to { userRoundCogIconState.stopAnimation() },
            "lucide_user-round-group" to { userRoundGroupIconState.stopAnimation() },
            "lucide_user-round" to { userRoundIconState.stopAnimation() },
            "lucide_user-round-key" to { userRoundKeyIconState.stopAnimation() },
            "lucide_user-round-minus" to { userRoundMinusIconState.stopAnimation() },
            "lucide_user-round-pen" to { userRoundPenIconState.stopAnimation() },
            "lucide_user-round-plus" to { userRoundPlusIconState.stopAnimation() },
            "lucide_user-round-search" to { userRoundSearchIconState.stopAnimation() },
            "lucide_user-round-x" to { userRoundXIconState.stopAnimation() },
            "lucide_user-search" to { userSearchIconState.stopAnimation() },
            "lucide_user-shield" to { userShieldIconState.stopAnimation() },
            "lucide_user-star" to { userStarIconState.stopAnimation() },
            "lucide_user-x" to { userXIconState.stopAnimation() },
            "lucide_users" to { usersIconState.stopAnimation() },
            "lucide_users-round" to { usersRoundIconState.stopAnimation() },
            "lucide_utensils" to { utensilsIconState.stopAnimation() },
            "lucide_variable" to { variableIconState.stopAnimation() },
            "lucide_vault" to { vaultIconState.stopAnimation() },
            "lucide_venus" to { venusIconState.stopAnimation() },
            "lucide_video" to { videoIconState.stopAnimation() },
            "lucide_video-off" to { videoOffIconState.stopAnimation() },
            "lucide_volume-1" to { volume1IconState.stopAnimation() },
            "lucide_volume-2" to { volume2IconState.stopAnimation() },
            "lucide_volume" to { volumeIconState.stopAnimation() },
            "lucide_volume-off" to { volumeOffIconState.stopAnimation() },
            "lucide_volume-x" to { volumeXIconState.stopAnimation() },
            "lucide_wallet-cards" to { walletCardsIconState.stopAnimation() },
            "lucide_wallet" to { walletIconState.stopAnimation() },
            "lucide_wallet-minimal" to { walletMinimalIconState.stopAnimation() },
            "lucide_wand-sparkles" to { wandSparklesIconState.stopAnimation() },
            "lucide_warehouse" to { warehouseIconState.stopAnimation() },
            "lucide_watch" to { watchIconState.stopAnimation() },
            "lucide_waypoints" to { waypointsIconState.stopAnimation() },
            "lucide_webcam" to { webcamIconState.stopAnimation() },
            "lucide_webcam-off" to { webcamOffIconState.stopAnimation() },
            "lucide_webhook" to { webhookIconState.stopAnimation() },
            "lucide_webhook-off" to { webhookOffIconState.stopAnimation() },
            "lucide_wifi-cog" to { wifiCogIconState.stopAnimation() },
            "lucide_wifi-high" to { wifiHighIconState.stopAnimation() },
            "lucide_wifi" to { wifiIconState.stopAnimation() },
            "lucide_wifi-low" to { wifiLowIconState.stopAnimation() },
            "lucide_wifi-off" to { wifiOffIconState.stopAnimation() },
            "lucide_wifi-pen" to { wifiPenIconState.stopAnimation() },
            "lucide_wifi-sync" to { wifiSyncIconState.stopAnimation() },
            "lucide_wifi-zero" to { wifiZeroIconState.stopAnimation() },
            "lucide_wind" to { windIconState.stopAnimation() },
            "lucide_workflow" to { workflowIconState.stopAnimation() },
            "lucide_wrap-text" to { wrapTextIconState.stopAnimation() },
            "lucide_wrench" to { wrenchIconState.stopAnimation() },
            "lucide_x" to { xIconState.stopAnimation() },
            "lucide_zap" to { zapIconState.stopAnimation() },
            "lucide_zoom-in" to { zoomInIconState.stopAnimation() },
            "lucide_zoom-out" to { zoomOutIconState.stopAnimation() },
        ),
    )
}

@Composable
internal fun LucideGalleryIcon(id: String, color: Color) {
    LucideGalleryChunk0(id, color)
    LucideGalleryChunk1(id, color)
    LucideGalleryChunk2(id, color)
    LucideGalleryChunk3(id, color)
    LucideGalleryChunk4(id, color)
    LucideGalleryChunk5(id, color)
    LucideGalleryChunk6(id, color)
    LucideGalleryChunk7(id, color)
    LucideGalleryChunk8(id, color)
    LucideGalleryChunk9(id, color)
}

@Composable
private fun LucideGalleryChunk0(id: String, color: Color) {
    when (id) {
        "lucide_a-arrow-down" -> AArrowDownIcon(size = 40.dp, color = color)
        "lucide_a-arrow-up" -> AArrowUpIcon(size = 40.dp, color = color)
        "lucide_accessibility" -> AccessibilityIcon(size = 40.dp, color = color)
        "lucide_activity" -> ActivityIcon(size = 40.dp, color = color)
        "lucide_alarm-clock-check" -> AlarmClockCheckIcon(size = 40.dp, color = color)
        "lucide_alarm-clock" -> AlarmClockIcon(size = 40.dp, color = color)
        "lucide_alarm-clock-minus" -> AlarmClockMinusIcon(size = 40.dp, color = color)
        "lucide_alarm-clock-off" -> AlarmClockOffIcon(size = 40.dp, color = color)
        "lucide_alarm-clock-plus" -> AlarmClockPlusIcon(size = 40.dp, color = color)
        "lucide_align-center" -> AlignCenterIcon(size = 40.dp, color = color)
        "lucide_align-justify" -> AlignJustifyIcon(size = 40.dp, color = color)
        "lucide_align-left" -> AlignLeftIcon(size = 40.dp, color = color)
        "lucide_align-right" -> AlignRightIcon(size = 40.dp, color = color)
        "lucide_anchor" -> AnchorIcon(size = 40.dp, color = color)
        "lucide_angry" -> AngryIcon(size = 40.dp, color = color)
        "lucide_annoyed" -> AnnoyedIcon(size = 40.dp, color = color)
        "lucide_app-window" -> AppWindowIcon(size = 40.dp, color = color)
        "lucide_archive" -> ArchiveIcon(size = 40.dp, color = color)
        "lucide_arrow-big-down-dash" -> ArrowBigDownDashIcon(size = 40.dp, color = color)
        "lucide_arrow-big-down" -> ArrowBigDownIcon(size = 40.dp, color = color)
        "lucide_arrow-big-left-dash" -> ArrowBigLeftDashIcon(size = 40.dp, color = color)
        "lucide_arrow-big-left" -> ArrowBigLeftIcon(size = 40.dp, color = color)
        "lucide_arrow-big-right-dash" -> ArrowBigRightDashIcon(size = 40.dp, color = color)
        "lucide_arrow-big-right" -> ArrowBigRightIcon(size = 40.dp, color = color)
        "lucide_arrow-big-up-dash" -> ArrowBigUpDashIcon(size = 40.dp, color = color)
        "lucide_arrow-big-up" -> ArrowBigUpIcon(size = 40.dp, color = color)
        "lucide_arrow-down-0-1" -> ArrowDown01Icon(size = 40.dp, color = color)
        "lucide_arrow-down-1-0" -> ArrowDown10Icon(size = 40.dp, color = color)
        "lucide_arrow-down-a-z" -> ArrowDownAZIcon(size = 40.dp, color = color)
        "lucide_arrow-down-from-line" -> ArrowDownFromLineIcon(size = 40.dp, color = color)
        "lucide_arrow-down" -> ArrowDownIcon(size = 40.dp, color = color)
        "lucide_arrow-down-left" -> ArrowDownLeftIcon(size = 40.dp, color = color)
        "lucide_arrow-down-narrow-wide" -> ArrowDownNarrowWideIcon(size = 40.dp, color = color)
        "lucide_arrow-down-right" -> ArrowDownRightIcon(size = 40.dp, color = color)
        "lucide_arrow-down-to-dot" -> ArrowDownToDotIcon(size = 40.dp, color = color)
        "lucide_arrow-down-to-line" -> ArrowDownToLineIcon(size = 40.dp, color = color)
        "lucide_arrow-down-up" -> ArrowDownUpIcon(size = 40.dp, color = color)
        "lucide_arrow-down-wide-narrow" -> ArrowDownWideNarrowIcon(size = 40.dp, color = color)
        "lucide_arrow-down-z-a" -> ArrowDownZAIcon(size = 40.dp, color = color)
        "lucide_arrow-left-from-line" -> ArrowLeftFromLineIcon(size = 40.dp, color = color)
        "lucide_arrow-left" -> ArrowLeftIcon(size = 40.dp, color = color)
        "lucide_arrow-left-right" -> ArrowLeftRightIcon(size = 40.dp, color = color)
        "lucide_arrow-left-to-line" -> ArrowLeftToLineIcon(size = 40.dp, color = color)
        "lucide_arrow-right-from-line" -> ArrowRightFromLineIcon(size = 40.dp, color = color)
        "lucide_arrow-right" -> ArrowRightIcon(size = 40.dp, color = color)
        "lucide_arrow-right-left" -> ArrowRightLeftIcon(size = 40.dp, color = color)
        "lucide_arrow-right-to-line" -> ArrowRightToLineIcon(size = 40.dp, color = color)
        "lucide_arrow-up-0-1" -> ArrowUp01Icon(size = 40.dp, color = color)
        "lucide_arrow-up-1-0" -> ArrowUp10Icon(size = 40.dp, color = color)
        "lucide_arrow-up-a-z" -> ArrowUpAZIcon(size = 40.dp, color = color)
        "lucide_arrow-up-down" -> ArrowUpDownIcon(size = 40.dp, color = color)
        "lucide_arrow-up-from-dot" -> ArrowUpFromDotIcon(size = 40.dp, color = color)
        "lucide_arrow-up-from-line" -> ArrowUpFromLineIcon(size = 40.dp, color = color)
        "lucide_arrow-up" -> ArrowUpIcon(size = 40.dp, color = color)
        "lucide_arrow-up-left" -> ArrowUpLeftIcon(size = 40.dp, color = color)
        "lucide_arrow-up-narrow-wide" -> ArrowUpNarrowWideIcon(size = 40.dp, color = color)
        "lucide_arrow-up-right" -> ArrowUpRightIcon(size = 40.dp, color = color)
        "lucide_arrow-up-to-line" -> ArrowUpToLineIcon(size = 40.dp, color = color)
        "lucide_arrow-up-wide-narrow" -> ArrowUpWideNarrowIcon(size = 40.dp, color = color)
        "lucide_arrow-up-z-a" -> ArrowUpZAIcon(size = 40.dp, color = color)
        "lucide_at-sign" -> AtSignIcon(size = 40.dp, color = color)
        "lucide_atom" -> AtomIcon(size = 40.dp, color = color)
        "lucide_audio-lines" -> AudioLinesIcon(size = 40.dp, color = color)
        "lucide_audio-lines-off" -> AudioLinesOffIcon(size = 40.dp, color = color)
        "lucide_audio-lines-x" -> AudioLinesXIcon(size = 40.dp, color = color)
        "lucide_audio-waveform" -> AudioWaveformIcon(size = 40.dp, color = color)
        "lucide_badge-cent" -> BadgeCentIcon(size = 40.dp, color = color)
        "lucide_badge-check" -> BadgeCheckIcon(size = 40.dp, color = color)
        "lucide_badge-dollar-sign" -> BadgeDollarSignIcon(size = 40.dp, color = color)
        "lucide_badge-info" -> BadgeInfoIcon(size = 40.dp, color = color)
        "lucide_badge-percent" -> BadgePercentIcon(size = 40.dp, color = color)
        "lucide_baggage-claim" -> BaggageClaimIcon(size = 40.dp, color = color)
        "lucide_ban" -> BanIcon(size = 40.dp, color = color)
        "lucide_banknote-arrow-down" -> BanknoteArrowDownIcon(size = 40.dp, color = color)
        "lucide_banknote-arrow-up" -> BanknoteArrowUpIcon(size = 40.dp, color = color)
        "lucide_banknote-check" -> BanknoteCheckIcon(size = 40.dp, color = color)
        "lucide_banknote" -> BanknoteIcon(size = 40.dp, color = color)
        "lucide_banknote-x" -> BanknoteXIcon(size = 40.dp, color = color)
        "lucide_barcode" -> BarcodeIcon(size = 40.dp, color = color)
        "lucide_battery-charging" -> BatteryChargingIcon(size = 40.dp, color = color)
        "lucide_battery-full" -> BatteryFullIcon(size = 40.dp, color = color)
        "lucide_battery" -> BatteryIcon(size = 40.dp, color = color)
        "lucide_battery-low" -> BatteryLowIcon(size = 40.dp, color = color)
        "lucide_battery-medium" -> BatteryMediumIcon(size = 40.dp, color = color)
        "lucide_battery-plus" -> BatteryPlusIcon(size = 40.dp, color = color)
        "lucide_battery-warning" -> BatteryWarningIcon(size = 40.dp, color = color)
        "lucide_bell-dot" -> BellDotIcon(size = 40.dp, color = color)
        "lucide_bell-electric" -> BellElectricIcon(size = 40.dp, color = color)
        "lucide_bell" -> BellIcon(size = 40.dp, color = color)
        "lucide_bell-minus" -> BellMinusIcon(size = 40.dp, color = color)
        "lucide_bell-off" -> BellOffIcon(size = 40.dp, color = color)
        "lucide_bell-plus" -> BellPlusIcon(size = 40.dp, color = color)
        "lucide_bell-ring" -> BellRingIcon(size = 40.dp, color = color)
        "lucide_biceps-flexed" -> BicepsFlexedIcon(size = 40.dp, color = color)
        "lucide_bike" -> BikeIcon(size = 40.dp, color = color)
        "lucide_bitcoin" -> BitcoinIcon(size = 40.dp, color = color)
        "lucide_blend" -> BlendIcon(size = 40.dp, color = color)
        "lucide_blocks" -> BlocksIcon(size = 40.dp, color = color)
        "lucide_bluetooth" -> BluetoothIcon(size = 40.dp, color = color)
        "lucide_bold" -> BoldIcon(size = 40.dp, color = color)
        else -> Unit
    }
}

@Composable
private fun LucideGalleryChunk1(id: String, color: Color) {
    when (id) {
        "lucide_bolt" -> BoltIcon(size = 40.dp, color = color)
        "lucide_book-open-check" -> BookOpenCheckIcon(size = 40.dp, color = color)
        "lucide_book-open" -> BookOpenIcon(size = 40.dp, color = color)
        "lucide_book-open-text" -> BookOpenTextIcon(size = 40.dp, color = color)
        "lucide_bookmark-check" -> BookmarkCheckIcon(size = 40.dp, color = color)
        "lucide_bookmark" -> BookmarkIcon(size = 40.dp, color = color)
        "lucide_bookmark-minus" -> BookmarkMinusIcon(size = 40.dp, color = color)
        "lucide_bookmark-off" -> BookmarkOffIcon(size = 40.dp, color = color)
        "lucide_bookmark-plus" -> BookmarkPlusIcon(size = 40.dp, color = color)
        "lucide_bookmark-x" -> BookmarkXIcon(size = 40.dp, color = color)
        "lucide_bot" -> LucideBotIcon(size = 40.dp, color = color)
        "lucide_box" -> BoxIcon(size = 40.dp, color = color)
        "lucide_boxes" -> BoxesIcon(size = 40.dp, color = color)
        "lucide_brain" -> BrainIcon(size = 40.dp, color = color)
        "lucide_briefcase" -> BriefcaseIcon(size = 40.dp, color = color)
        "lucide_bring-to-front" -> BringToFrontIcon(size = 40.dp, color = color)
        "lucide_bug" -> BugIcon(size = 40.dp, color = color)
        "lucide_bug-off" -> BugOffIcon(size = 40.dp, color = color)
        "lucide_bug-play" -> BugPlayIcon(size = 40.dp, color = color)
        "lucide_building-2" -> Building2Icon(size = 40.dp, color = color)
        "lucide_building-complex" -> BuildingComplexIcon(size = 40.dp, color = color)
        "lucide_building-complex-plus" -> BuildingComplexPlusIcon(size = 40.dp, color = color)
        "lucide_building" -> BuildingIcon(size = 40.dp, color = color)
        "lucide_calculator" -> CalculatorIcon(size = 40.dp, color = color)
        "lucide_calendar-arrow-down" -> CalendarArrowDownIcon(size = 40.dp, color = color)
        "lucide_calendar-arrow-up" -> CalendarArrowUpIcon(size = 40.dp, color = color)
        "lucide_calendar-check" -> CalendarCheckIcon(size = 40.dp, color = color)
        "lucide_calendar-chevrons-right" -> CalendarChevronsRightIcon(size = 40.dp, color = color)
        "lucide_calendar-clock" -> CalendarClockIcon(size = 40.dp, color = color)
        "lucide_calendar-cog" -> CalendarCogIcon(size = 40.dp, color = color)
        "lucide_calendar-days" -> CalendarDaysIcon(size = 40.dp, color = color)
        "lucide_calendar-fold" -> CalendarFoldIcon(size = 40.dp, color = color)
        "lucide_calendar-heart" -> CalendarHeartIcon(size = 40.dp, color = color)
        "lucide_calendar" -> CalendarIcon(size = 40.dp, color = color)
        "lucide_calendar-minus" -> CalendarMinusIcon(size = 40.dp, color = color)
        "lucide_calendar-off" -> CalendarOffIcon(size = 40.dp, color = color)
        "lucide_calendar-plus" -> CalendarPlusIcon(size = 40.dp, color = color)
        "lucide_calendar-range" -> CalendarRangeIcon(size = 40.dp, color = color)
        "lucide_calendar-search" -> CalendarSearchIcon(size = 40.dp, color = color)
        "lucide_calendar-sync" -> CalendarSyncIcon(size = 40.dp, color = color)
        "lucide_calendar-x" -> CalendarXIcon(size = 40.dp, color = color)
        "lucide_calendars" -> CalendarsIcon(size = 40.dp, color = color)
        "lucide_camera" -> CameraIcon(size = 40.dp, color = color)
        "lucide_camera-off" -> CameraOffIcon(size = 40.dp, color = color)
        "lucide_captions" -> CaptionsIcon(size = 40.dp, color = color)
        "lucide_car" -> CarIcon(size = 40.dp, color = color)
        "lucide_case-lower" -> CaseLowerIcon(size = 40.dp, color = color)
        "lucide_case-sensitive" -> CaseSensitiveIcon(size = 40.dp, color = color)
        "lucide_case-upper" -> CaseUpperIcon(size = 40.dp, color = color)
        "lucide_cast" -> CastIcon(size = 40.dp, color = color)
        "lucide_cat" -> CatIcon(size = 40.dp, color = color)
        "lucide_cctv" -> CctvIcon(size = 40.dp, color = color)
        "lucide_cctv-off" -> CctvOffIcon(size = 40.dp, color = color)
        "lucide_chart-area" -> ChartAreaIcon(size = 40.dp, color = color)
        "lucide_chart-bar-big" -> ChartBarBigIcon(size = 40.dp, color = color)
        "lucide_chart-bar-decreasing" -> ChartBarDecreasingIcon(size = 40.dp, color = color)
        "lucide_chart-bar" -> ChartBarIcon(size = 40.dp, color = color)
        "lucide_chart-bar-increasing" -> ChartBarIncreasingIcon(size = 40.dp, color = color)
        "lucide_chart-candlestick" -> ChartCandlestickIcon(size = 40.dp, color = color)
        "lucide_chart-column-big" -> ChartColumnBigIcon(size = 40.dp, color = color)
        "lucide_chart-column-decreasing" -> ChartColumnDecreasingIcon(size = 40.dp, color = color)
        "lucide_chart-column" -> ChartColumnIcon(size = 40.dp, color = color)
        "lucide_chart-column-increasing" -> ChartColumnIncreasingIcon(size = 40.dp, color = color)
        "lucide_chart-gantt" -> ChartGanttIcon(size = 40.dp, color = color)
        "lucide_chart-line" -> ChartLineIcon(size = 40.dp, color = color)
        "lucide_chart-network" -> ChartNetworkIcon(size = 40.dp, color = color)
        "lucide_chart-no-axes-column" -> ChartNoAxesColumnIcon(size = 40.dp, color = color)
        "lucide_chart-no-axes-combined" -> ChartNoAxesCombinedIcon(size = 40.dp, color = color)
        "lucide_chart-pie" -> ChartPieIcon(size = 40.dp, color = color)
        "lucide_chart-scatter" -> ChartScatterIcon(size = 40.dp, color = color)
        "lucide_chart-spline" -> ChartSplineIcon(size = 40.dp, color = color)
        "lucide_check-check" -> CheckCheckIcon(size = 40.dp, color = color)
        "lucide_check" -> CheckIcon(size = 40.dp, color = color)
        "lucide_chevron-down" -> ChevronDownIcon(size = 40.dp, color = color)
        "lucide_chevron-first" -> ChevronFirstIcon(size = 40.dp, color = color)
        "lucide_chevron-last" -> ChevronLastIcon(size = 40.dp, color = color)
        "lucide_chevron-left" -> ChevronLeftIcon(size = 40.dp, color = color)
        "lucide_chevron-right" -> ChevronRightIcon(size = 40.dp, color = color)
        "lucide_chevron-up" -> ChevronUpIcon(size = 40.dp, color = color)
        "lucide_chevrons-down" -> ChevronsDownIcon(size = 40.dp, color = color)
        "lucide_chevrons-down-up" -> ChevronsDownUpIcon(size = 40.dp, color = color)
        "lucide_chevrons-left" -> ChevronsLeftIcon(size = 40.dp, color = color)
        "lucide_chevrons-left-right-ellipsis" -> ChevronsLeftRightEllipsisIcon(size = 40.dp, color = color)
        "lucide_chevrons-left-right" -> ChevronsLeftRightIcon(size = 40.dp, color = color)
        "lucide_chevrons-right" -> ChevronsRightIcon(size = 40.dp, color = color)
        "lucide_chevrons-right-left" -> ChevronsRightLeftIcon(size = 40.dp, color = color)
        "lucide_chevrons-up-down" -> ChevronsUpDownIcon(size = 40.dp, color = color)
        "lucide_chevrons-up" -> ChevronsUpIcon(size = 40.dp, color = color)
        "lucide_chrome" -> ChromeIcon(size = 40.dp, color = color)
        "lucide_circle-alert" -> CircleAlertIcon(size = 40.dp, color = color)
        "lucide_circle-arrow-down" -> CircleArrowDownIcon(size = 40.dp, color = color)
        "lucide_circle-arrow-left" -> CircleArrowLeftIcon(size = 40.dp, color = color)
        "lucide_circle-arrow-out-down-left" -> CircleArrowOutDownLeftIcon(size = 40.dp, color = color)
        "lucide_circle-arrow-out-down-right" -> CircleArrowOutDownRightIcon(size = 40.dp, color = color)
        "lucide_circle-arrow-out-up-left" -> CircleArrowOutUpLeftIcon(size = 40.dp, color = color)
        "lucide_circle-arrow-out-up-right" -> CircleArrowOutUpRightIcon(size = 40.dp, color = color)
        "lucide_circle-arrow-right" -> CircleArrowRightIcon(size = 40.dp, color = color)
        "lucide_circle-arrow-up" -> CircleArrowUpIcon(size = 40.dp, color = color)
        "lucide_circle-check-big" -> CircleCheckBigIcon(size = 40.dp, color = color)
        "lucide_circle-check" -> CircleCheckIcon(size = 40.dp, color = color)
        else -> Unit
    }
}

@Composable
private fun LucideGalleryChunk2(id: String, color: Color) {
    when (id) {
        "lucide_circle-chevron-down" -> CircleChevronDownIcon(size = 40.dp, color = color)
        "lucide_circle-chevron-left" -> CircleChevronLeftIcon(size = 40.dp, color = color)
        "lucide_circle-chevron-right" -> CircleChevronRightIcon(size = 40.dp, color = color)
        "lucide_circle-chevron-up" -> CircleChevronUpIcon(size = 40.dp, color = color)
        "lucide_circle-dashed" -> CircleDashedIcon(size = 40.dp, color = color)
        "lucide_circle-dollar-sign" -> CircleDollarSignIcon(size = 40.dp, color = color)
        "lucide_circle-dot" -> CircleDotIcon(size = 40.dp, color = color)
        "lucide_circle-ellipsis" -> CircleEllipsisIcon(size = 40.dp, color = color)
        "lucide_circle-euro" -> CircleEuroIcon(size = 40.dp, color = color)
        "lucide_circle" -> CircleIcon(size = 40.dp, color = color)
        "lucide_circle-minus" -> CircleMinusIcon(size = 40.dp, color = color)
        "lucide_circle-off" -> CircleOffIcon(size = 40.dp, color = color)
        "lucide_circle-pause" -> CirclePauseIcon(size = 40.dp, color = color)
        "lucide_circle-percent" -> CirclePercentIcon(size = 40.dp, color = color)
        "lucide_circle-play" -> CirclePlayIcon(size = 40.dp, color = color)
        "lucide_circle-plus" -> CirclePlusIcon(size = 40.dp, color = color)
        "lucide_circle-pound-sterling" -> CirclePoundSterlingIcon(size = 40.dp, color = color)
        "lucide_circle-power" -> CirclePowerIcon(size = 40.dp, color = color)
        "lucide_circle-question-mark" -> CircleQuestionMarkIcon(size = 40.dp, color = color)
        "lucide_circle-slash-2" -> CircleSlash2Icon(size = 40.dp, color = color)
        "lucide_circle-slash" -> CircleSlashIcon(size = 40.dp, color = color)
        "lucide_circle-star" -> CircleStarIcon(size = 40.dp, color = color)
        "lucide_circle-stop" -> CircleStopIcon(size = 40.dp, color = color)
        "lucide_circle-user" -> CircleUserIcon(size = 40.dp, color = color)
        "lucide_circle-user-round" -> CircleUserRoundIcon(size = 40.dp, color = color)
        "lucide_circle-x" -> CircleXIcon(size = 40.dp, color = color)
        "lucide_clapperboard" -> ClapperboardIcon(size = 40.dp, color = color)
        "lucide_clipboard-check" -> ClipboardCheckIcon(size = 40.dp, color = color)
        "lucide_clipboard-clock" -> ClipboardClockIcon(size = 40.dp, color = color)
        "lucide_clipboard" -> ClipboardIcon(size = 40.dp, color = color)
        "lucide_clipboard-list" -> ClipboardListIcon(size = 40.dp, color = color)
        "lucide_clipboard-paste" -> ClipboardPasteIcon(size = 40.dp, color = color)
        "lucide_clock-alert" -> ClockAlertIcon(size = 40.dp, color = color)
        "lucide_clock-arrow-down" -> ClockArrowDownIcon(size = 40.dp, color = color)
        "lucide_clock-arrow-left" -> ClockArrowLeftIcon(size = 40.dp, color = color)
        "lucide_clock-arrow-right" -> ClockArrowRightIcon(size = 40.dp, color = color)
        "lucide_clock-arrow-up" -> ClockArrowUpIcon(size = 40.dp, color = color)
        "lucide_clock-check" -> ClockCheckIcon(size = 40.dp, color = color)
        "lucide_clock-fading" -> ClockFadingIcon(size = 40.dp, color = color)
        "lucide_clock" -> ClockIcon(size = 40.dp, color = color)
        "lucide_clock-plus" -> ClockPlusIcon(size = 40.dp, color = color)
        "lucide_cloud-check" -> CloudCheckIcon(size = 40.dp, color = color)
        "lucide_cloud-download" -> CloudDownloadIcon(size = 40.dp, color = color)
        "lucide_cloud-drizzle" -> CloudDrizzleIcon(size = 40.dp, color = color)
        "lucide_cloud-fog" -> CloudFogIcon(size = 40.dp, color = color)
        "lucide_cloud-hail" -> CloudHailIcon(size = 40.dp, color = color)
        "lucide_cloud" -> CloudIcon(size = 40.dp, color = color)
        "lucide_cloud-lightning" -> CloudLightningIcon(size = 40.dp, color = color)
        "lucide_cloud-moon" -> CloudMoonIcon(size = 40.dp, color = color)
        "lucide_cloud-moon-rain" -> CloudMoonRainIcon(size = 40.dp, color = color)
        "lucide_cloud-off" -> CloudOffIcon(size = 40.dp, color = color)
        "lucide_cloud-rain" -> CloudRainIcon(size = 40.dp, color = color)
        "lucide_cloud-rain-wind" -> CloudRainWindIcon(size = 40.dp, color = color)
        "lucide_cloud-snow" -> CloudSnowIcon(size = 40.dp, color = color)
        "lucide_cloud-sun" -> CloudSunIcon(size = 40.dp, color = color)
        "lucide_cloud-sun-rain" -> CloudSunRainIcon(size = 40.dp, color = color)
        "lucide_cloud-sync" -> LucideCloudSyncIcon(size = 40.dp, color = color)
        "lucide_cloud-upload" -> CloudUploadIcon(size = 40.dp, color = color)
        "lucide_cloudy" -> CloudyIcon(size = 40.dp, color = color)
        "lucide_code" -> CodeIcon(size = 40.dp, color = color)
        "lucide_code-xml" -> CodeXmlIcon(size = 40.dp, color = color)
        "lucide_coffee" -> CoffeeIcon(size = 40.dp, color = color)
        "lucide_coins" -> CoinsIcon(size = 40.dp, color = color)
        "lucide_columns-2" -> Columns2Icon(size = 40.dp, color = color)
        "lucide_columns-3" -> Columns3Icon(size = 40.dp, color = color)
        "lucide_columns-4" -> Columns4Icon(size = 40.dp, color = color)
        "lucide_command" -> CommandIcon(size = 40.dp, color = color)
        "lucide_compass" -> CompassIcon(size = 40.dp, color = color)
        "lucide_component" -> ComponentIcon(size = 40.dp, color = color)
        "lucide_contact" -> ContactIcon(size = 40.dp, color = color)
        "lucide_contact-round" -> ContactRoundIcon(size = 40.dp, color = color)
        "lucide_contrast" -> ContrastIcon(size = 40.dp, color = color)
        "lucide_cookie" -> CookieIcon(size = 40.dp, color = color)
        "lucide_copy-check" -> CopyCheckIcon(size = 40.dp, color = color)
        "lucide_copy" -> CopyIcon(size = 40.dp, color = color)
        "lucide_copy-plus" -> CopyPlusIcon(size = 40.dp, color = color)
        "lucide_corner-down-left" -> CornerDownLeftIcon(size = 40.dp, color = color)
        "lucide_corner-down-right" -> CornerDownRightIcon(size = 40.dp, color = color)
        "lucide_corner-left-down" -> CornerLeftDownIcon(size = 40.dp, color = color)
        "lucide_corner-left-up" -> CornerLeftUpIcon(size = 40.dp, color = color)
        "lucide_corner-right-down" -> CornerRightDownIcon(size = 40.dp, color = color)
        "lucide_corner-right-up" -> CornerRightUpIcon(size = 40.dp, color = color)
        "lucide_corner-up-left" -> CornerUpLeftIcon(size = 40.dp, color = color)
        "lucide_corner-up-right" -> CornerUpRightIcon(size = 40.dp, color = color)
        "lucide_cpu" -> CpuIcon(size = 40.dp, color = color)
        "lucide_credit-card-check" -> CreditCardCheckIcon(size = 40.dp, color = color)
        "lucide_credit-card" -> CreditCardIcon(size = 40.dp, color = color)
        "lucide_credit-card-minus" -> CreditCardMinusIcon(size = 40.dp, color = color)
        "lucide_credit-card-plus" -> CreditCardPlusIcon(size = 40.dp, color = color)
        "lucide_credit-card-reader" -> CreditCardReaderIcon(size = 40.dp, color = color)
        "lucide_credit-card-x" -> CreditCardXIcon(size = 40.dp, color = color)
        "lucide_crop" -> CropIcon(size = 40.dp, color = color)
        "lucide_crown" -> CrownIcon(size = 40.dp, color = color)
        "lucide_database-arrow-down" -> DatabaseArrowDownIcon(size = 40.dp, color = color)
        "lucide_database-arrow-up" -> DatabaseArrowUpIcon(size = 40.dp, color = color)
        "lucide_database-backup" -> DatabaseBackupIcon(size = 40.dp, color = color)
        "lucide_database-check" -> DatabaseCheckIcon(size = 40.dp, color = color)
        "lucide_database" -> DatabaseIcon(size = 40.dp, color = color)
        "lucide_database-minus" -> DatabaseMinusIcon(size = 40.dp, color = color)
        "lucide_database-plus" -> DatabasePlusIcon(size = 40.dp, color = color)
        else -> Unit
    }
}

@Composable
private fun LucideGalleryChunk3(id: String, color: Color) {
    when (id) {
        "lucide_database-search" -> DatabaseSearchIcon(size = 40.dp, color = color)
        "lucide_database-x" -> DatabaseXIcon(size = 40.dp, color = color)
        "lucide_database-zap" -> DatabaseZapIcon(size = 40.dp, color = color)
        "lucide_delete" -> DeleteIcon(size = 40.dp, color = color)
        "lucide_diamond" -> DiamondIcon(size = 40.dp, color = color)
        "lucide_diamond-minus" -> DiamondMinusIcon(size = 40.dp, color = color)
        "lucide_diamond-percent" -> DiamondPercentIcon(size = 40.dp, color = color)
        "lucide_diamond-plus" -> DiamondPlusIcon(size = 40.dp, color = color)
        "lucide_dice-5" -> Dice5Icon(size = 40.dp, color = color)
        "lucide_diff" -> DiffIcon(size = 40.dp, color = color)
        "lucide_disc-2" -> Disc2Icon(size = 40.dp, color = color)
        "lucide_disc-3" -> Disc3Icon(size = 40.dp, color = color)
        "lucide_disc-album" -> DiscAlbumIcon(size = 40.dp, color = color)
        "lucide_disc" -> DiscIcon(size = 40.dp, color = color)
        "lucide_dog" -> DogIcon(size = 40.dp, color = color)
        "lucide_dollar-sign" -> DollarSignIcon(size = 40.dp, color = color)
        "lucide_door-open" -> DoorOpenIcon(size = 40.dp, color = color)
        "lucide_download" -> DownloadIcon(size = 40.dp, color = color)
        "lucide_droplet" -> DropletIcon(size = 40.dp, color = color)
        "lucide_droplets" -> DropletsIcon(size = 40.dp, color = color)
        "lucide_drum" -> DrumIcon(size = 40.dp, color = color)
        "lucide_dumbbell" -> DumbbellIcon(size = 40.dp, color = color)
        "lucide_ellipsis" -> EllipsisIcon(size = 40.dp, color = color)
        "lucide_ellipsis-vertical" -> EllipsisVerticalIcon(size = 40.dp, color = color)
        "lucide_eraser" -> EraserIcon(size = 40.dp, color = color)
        "lucide_euro" -> EuroIcon(size = 40.dp, color = color)
        "lucide_expand" -> ExpandIcon(size = 40.dp, color = color)
        "lucide_external-link" -> ExternalLinkIcon(size = 40.dp, color = color)
        "lucide_eye-closed" -> EyeClosedIcon(size = 40.dp, color = color)
        "lucide_eye" -> EyeIcon(size = 40.dp, color = color)
        "lucide_eye-off" -> EyeOffIcon(size = 40.dp, color = color)
        "lucide_facebook" -> FacebookIcon(size = 40.dp, color = color)
        "lucide_factory" -> FactoryIcon(size = 40.dp, color = color)
        "lucide_fast-forward" -> FastForwardIcon(size = 40.dp, color = color)
        "lucide_feather" -> FeatherIcon(size = 40.dp, color = color)
        "lucide_figma" -> FigmaIcon(size = 40.dp, color = color)
        "lucide_file-archive" -> FileArchiveIcon(size = 40.dp, color = color)
        "lucide_file-check" -> FileCheckIcon(size = 40.dp, color = color)
        "lucide_file-clock" -> FileClockIcon(size = 40.dp, color = color)
        "lucide_file-code" -> FileCodeIcon(size = 40.dp, color = color)
        "lucide_file-down" -> FileDownIcon(size = 40.dp, color = color)
        "lucide_file-heart" -> FileHeartIcon(size = 40.dp, color = color)
        "lucide_file" -> FileIcon(size = 40.dp, color = color)
        "lucide_file-image" -> FileImageIcon(size = 40.dp, color = color)
        "lucide_file-key" -> FileKeyIcon(size = 40.dp, color = color)
        "lucide_file-lock" -> FileLockIcon(size = 40.dp, color = color)
        "lucide_file-minus" -> FileMinusIcon(size = 40.dp, color = color)
        "lucide_file-music" -> FileMusicIcon(size = 40.dp, color = color)
        "lucide_file-pen" -> FilePenIcon(size = 40.dp, color = color)
        "lucide_file-plus" -> FilePlusIcon(size = 40.dp, color = color)
        "lucide_file-scan" -> FileScanIcon(size = 40.dp, color = color)
        "lucide_file-search" -> FileSearchIcon(size = 40.dp, color = color)
        "lucide_file-spreadsheet" -> FileSpreadsheetIcon(size = 40.dp, color = color)
        "lucide_file-terminal" -> FileTerminalIcon(size = 40.dp, color = color)
        "lucide_file-text" -> FileTextIcon(size = 40.dp, color = color)
        "lucide_file-type" -> FileTypeIcon(size = 40.dp, color = color)
        "lucide_file-up" -> FileUpIcon(size = 40.dp, color = color)
        "lucide_file-x" -> FileXIcon(size = 40.dp, color = color)
        "lucide_files" -> FilesIcon(size = 40.dp, color = color)
        "lucide_film" -> FilmIcon(size = 40.dp, color = color)
        "lucide_filter" -> FilterIcon(size = 40.dp, color = color)
        "lucide_fingerprint-pattern" -> FingerprintPatternIcon(size = 40.dp, color = color)
        "lucide_fish" -> FishIcon(size = 40.dp, color = color)
        "lucide_flag" -> FlagIcon(size = 40.dp, color = color)
        "lucide_flag-off" -> FlagOffIcon(size = 40.dp, color = color)
        "lucide_flag-triangle-right" -> FlagTriangleRightIcon(size = 40.dp, color = color)
        "lucide_flame" -> LucideFlameIcon(size = 40.dp, color = color)
        "lucide_flask-conical" -> FlaskConicalIcon(size = 40.dp, color = color)
        "lucide_fold-vertical" -> FoldVerticalIcon(size = 40.dp, color = color)
        "lucide_folder-check" -> FolderCheckIcon(size = 40.dp, color = color)
        "lucide_folder-closed" -> FolderClosedIcon(size = 40.dp, color = color)
        "lucide_folder-dot" -> FolderDotIcon(size = 40.dp, color = color)
        "lucide_folder-heart" -> FolderHeartIcon(size = 40.dp, color = color)
        "lucide_folder" -> FolderIcon(size = 40.dp, color = color)
        "lucide_folder-key" -> FolderKeyIcon(size = 40.dp, color = color)
        "lucide_folder-lock" -> FolderLockIcon(size = 40.dp, color = color)
        "lucide_folder-minus" -> FolderMinusIcon(size = 40.dp, color = color)
        "lucide_folder-open" -> FolderOpenIcon(size = 40.dp, color = color)
        "lucide_folder-plus" -> FolderPlusIcon(size = 40.dp, color = color)
        "lucide_folder-search" -> FolderSearchIcon(size = 40.dp, color = color)
        "lucide_folder-tree" -> FolderTreeIcon(size = 40.dp, color = color)
        "lucide_folder-x" -> FolderXIcon(size = 40.dp, color = color)
        "lucide_footprints" -> FootprintsIcon(size = 40.dp, color = color)
        "lucide_framer" -> FramerIcon(size = 40.dp, color = color)
        "lucide_fullscreen" -> FullscreenIcon(size = 40.dp, color = color)
        "lucide_funnel-x" -> FunnelXIcon(size = 40.dp, color = color)
        "lucide_gallery-horizontal" -> GalleryHorizontalIcon(size = 40.dp, color = color)
        "lucide_gamepad" -> GamepadIcon(size = 40.dp, color = color)
        "lucide_gauge" -> GaugeIcon(size = 40.dp, color = color)
        "lucide_gem" -> GemIcon(size = 40.dp, color = color)
        "lucide_georgian-lari" -> GeorgianLariIcon(size = 40.dp, color = color)
        "lucide_ghost" -> GhostIcon(size = 40.dp, color = color)
        "lucide_gift" -> GiftIcon(size = 40.dp, color = color)
        "lucide_git-branch" -> GitBranchIcon(size = 40.dp, color = color)
        "lucide_git-branch-minus" -> GitBranchMinusIcon(size = 40.dp, color = color)
        "lucide_git-branch-plus" -> GitBranchPlusIcon(size = 40.dp, color = color)
        "lucide_git-commit-horizontal" -> GitCommitHorizontalIcon(size = 40.dp, color = color)
        "lucide_git-commit-vertical" -> GitCommitVerticalIcon(size = 40.dp, color = color)
        "lucide_git-compare-arrows" -> GitCompareArrowsIcon(size = 40.dp, color = color)
        "lucide_git-compare" -> GitCompareIcon(size = 40.dp, color = color)
        else -> Unit
    }
}

@Composable
private fun LucideGalleryChunk4(id: String, color: Color) {
    when (id) {
        "lucide_git-fork" -> GitForkIcon(size = 40.dp, color = color)
        "lucide_git-graph" -> GitGraphIcon(size = 40.dp, color = color)
        "lucide_git-merge-conflict" -> GitMergeConflictIcon(size = 40.dp, color = color)
        "lucide_git-merge" -> GitMergeIcon(size = 40.dp, color = color)
        "lucide_git-pull-request-arrow" -> GitPullRequestArrowIcon(size = 40.dp, color = color)
        "lucide_git-pull-request-closed" -> GitPullRequestClosedIcon(size = 40.dp, color = color)
        "lucide_git-pull-request-create-arrow" -> GitPullRequestCreateArrowIcon(size = 40.dp, color = color)
        "lucide_git-pull-request-create" -> GitPullRequestCreateIcon(size = 40.dp, color = color)
        "lucide_git-pull-request-draft" -> GitPullRequestDraftIcon(size = 40.dp, color = color)
        "lucide_git-pull-request" -> GitPullRequestIcon(size = 40.dp, color = color)
        "lucide_github" -> GithubIcon(size = 40.dp, color = color)
        "lucide_gitlab" -> GitlabIcon(size = 40.dp, color = color)
        "lucide_globe" -> GlobeIcon(size = 40.dp, color = color)
        "lucide_globe-lock" -> GlobeLockIcon(size = 40.dp, color = color)
        "lucide_graduation-cap" -> GraduationCapIcon(size = 40.dp, color = color)
        "lucide_grid-2x2-check" -> Grid2x2CheckIcon(size = 40.dp, color = color)
        "lucide_grid-2x2" -> Grid2x2Icon(size = 40.dp, color = color)
        "lucide_grid-2x2-plus" -> Grid2x2PlusIcon(size = 40.dp, color = color)
        "lucide_grid-2x2-x" -> Grid2x2XIcon(size = 40.dp, color = color)
        "lucide_grid-3x2" -> Grid3x2Icon(size = 40.dp, color = color)
        "lucide_grid-3x3" -> Grid3x3Icon(size = 40.dp, color = color)
        "lucide_grip-vertical" -> GripVerticalIcon(size = 40.dp, color = color)
        "lucide_guitar" -> GuitarIcon(size = 40.dp, color = color)
        "lucide_hammer" -> HammerIcon(size = 40.dp, color = color)
        "lucide_hand-coins" -> HandCoinsIcon(size = 40.dp, color = color)
        "lucide_hand-heart" -> HandHeartIcon(size = 40.dp, color = color)
        "lucide_hand" -> HandIcon(size = 40.dp, color = color)
        "lucide_handshake" -> HandshakeIcon(size = 40.dp, color = color)
        "lucide_hard-drive-download" -> HardDriveDownloadIcon(size = 40.dp, color = color)
        "lucide_hard-drive" -> HardDriveIcon(size = 40.dp, color = color)
        "lucide_hard-drive-upload" -> HardDriveUploadIcon(size = 40.dp, color = color)
        "lucide_hash" -> HashIcon(size = 40.dp, color = color)
        "lucide_haze" -> HazeIcon(size = 40.dp, color = color)
        "lucide_heading-1" -> Heading1Icon(size = 40.dp, color = color)
        "lucide_heading-2" -> Heading2Icon(size = 40.dp, color = color)
        "lucide_heading-3" -> Heading3Icon(size = 40.dp, color = color)
        "lucide_heading-4" -> Heading4Icon(size = 40.dp, color = color)
        "lucide_heading-5" -> Heading5Icon(size = 40.dp, color = color)
        "lucide_heading-6" -> Heading6Icon(size = 40.dp, color = color)
        "lucide_heading" -> HeadingIcon(size = 40.dp, color = color)
        "lucide_headphone-off" -> HeadphoneOffIcon(size = 40.dp, color = color)
        "lucide_headphones" -> HeadphonesIcon(size = 40.dp, color = color)
        "lucide_headset" -> HeadsetIcon(size = 40.dp, color = color)
        "lucide_heart-crack" -> HeartCrackIcon(size = 40.dp, color = color)
        "lucide_heart-handshake" -> HeartHandshakeIcon(size = 40.dp, color = color)
        "lucide_heart" -> HeartIcon(size = 40.dp, color = color)
        "lucide_heart-minus" -> HeartMinusIcon(size = 40.dp, color = color)
        "lucide_heart-off" -> HeartOffIcon(size = 40.dp, color = color)
        "lucide_heart-plus" -> HeartPlusIcon(size = 40.dp, color = color)
        "lucide_heart-pulse" -> HeartPulseIcon(size = 40.dp, color = color)
        "lucide_heart-x" -> HeartXIcon(size = 40.dp, color = color)
        "lucide_hexagon" -> HexagonIcon(size = 40.dp, color = color)
        "lucide_highlighter" -> HighlighterIcon(size = 40.dp, color = color)
        "lucide_history" -> HistoryIcon(size = 40.dp, color = color)
        "lucide_hospital" -> HospitalIcon(size = 40.dp, color = color)
        "lucide_hotel" -> HotelIcon(size = 40.dp, color = color)
        "lucide_hourglass" -> HourglassIcon(size = 40.dp, color = color)
        "lucide_house-cog" -> HouseCogIcon(size = 40.dp, color = color)
        "lucide_house-heart" -> HouseHeartIcon(size = 40.dp, color = color)
        "lucide_house" -> HouseIcon(size = 40.dp, color = color)
        "lucide_house-plug" -> HousePlugIcon(size = 40.dp, color = color)
        "lucide_house-plus" -> HousePlusIcon(size = 40.dp, color = color)
        "lucide_house-wifi" -> HouseWifiIcon(size = 40.dp, color = color)
        "lucide_image" -> ImageIcon(size = 40.dp, color = color)
        "lucide_image-off" -> ImageOffIcon(size = 40.dp, color = color)
        "lucide_image-plus" -> ImagePlusIcon(size = 40.dp, color = color)
        "lucide_inbox" -> InboxIcon(size = 40.dp, color = color)
        "lucide_indent-decrease" -> IndentDecreaseIcon(size = 40.dp, color = color)
        "lucide_indent-increase" -> IndentIncreaseIcon(size = 40.dp, color = color)
        "lucide_indian-rupee" -> IndianRupeeIcon(size = 40.dp, color = color)
        "lucide_infinity" -> InfinityIcon(size = 40.dp, color = color)
        "lucide_info" -> InfoIcon(size = 40.dp, color = color)
        "lucide_instagram" -> InstagramIcon(size = 40.dp, color = color)
        "lucide_italic" -> ItalicIcon(size = 40.dp, color = color)
        "lucide_japanese-yen" -> JapaneseYenIcon(size = 40.dp, color = color)
        "lucide_kanban" -> KanbanIcon(size = 40.dp, color = color)
        "lucide_key" -> KeyIcon(size = 40.dp, color = color)
        "lucide_key-round" -> KeyRoundIcon(size = 40.dp, color = color)
        "lucide_key-square" -> KeySquareIcon(size = 40.dp, color = color)
        "lucide_keyboard" -> KeyboardIcon(size = 40.dp, color = color)
        "lucide_landmark" -> LandmarkIcon(size = 40.dp, color = color)
        "lucide_languages" -> LanguagesIcon(size = 40.dp, color = color)
        "lucide_laptop" -> LaptopIcon(size = 40.dp, color = color)
        "lucide_laptop-minimal-check" -> LaptopMinimalCheckIcon(size = 40.dp, color = color)
        "lucide_laptop-minimal" -> LaptopMinimalIcon(size = 40.dp, color = color)
        "lucide_layers" -> LayersIcon(size = 40.dp, color = color)
        "lucide_layout-dashboard" -> LayoutDashboardIcon(size = 40.dp, color = color)
        "lucide_layout-grid" -> LayoutGridIcon(size = 40.dp, color = color)
        "lucide_layout-list" -> LayoutListIcon(size = 40.dp, color = color)
        "lucide_layout-panel-left" -> LayoutPanelLeftIcon(size = 40.dp, color = color)
        "lucide_layout-panel-top" -> LayoutPanelTopIcon(size = 40.dp, color = color)
        "lucide_layout-template" -> LayoutTemplateIcon(size = 40.dp, color = color)
        "lucide_leaf" -> LeafIcon(size = 40.dp, color = color)
        "lucide_life-buoy" -> LifeBuoyIcon(size = 40.dp, color = color)
        "lucide_lightbulb" -> LightbulbIcon(size = 40.dp, color = color)
        "lucide_link" -> LinkIcon(size = 40.dp, color = color)
        "lucide_linkedin" -> LinkedinIcon(size = 40.dp, color = color)
        "lucide_list-check" -> ListCheckIcon(size = 40.dp, color = color)
        "lucide_list-checks" -> ListChecksIcon(size = 40.dp, color = color)
        "lucide_list-chevrons-down-up" -> ListChevronsDownUpIcon(size = 40.dp, color = color)
        else -> Unit
    }
}

@Composable
private fun LucideGalleryChunk5(id: String, color: Color) {
    when (id) {
        "lucide_list-chevrons-up-down" -> ListChevronsUpDownIcon(size = 40.dp, color = color)
        "lucide_list-collapse" -> ListCollapseIcon(size = 40.dp, color = color)
        "lucide_list-end" -> ListEndIcon(size = 40.dp, color = color)
        "lucide_list-filter" -> ListFilterIcon(size = 40.dp, color = color)
        "lucide_list-filter-plus" -> ListFilterPlusIcon(size = 40.dp, color = color)
        "lucide_list" -> ListIcon(size = 40.dp, color = color)
        "lucide_list-minus" -> ListMinusIcon(size = 40.dp, color = color)
        "lucide_list-ordered" -> ListOrderedIcon(size = 40.dp, color = color)
        "lucide_list-plus" -> ListPlusIcon(size = 40.dp, color = color)
        "lucide_list-start" -> ListStartIcon(size = 40.dp, color = color)
        "lucide_list-todo" -> ListTodoIcon(size = 40.dp, color = color)
        "lucide_list-tree" -> ListTreeIcon(size = 40.dp, color = color)
        "lucide_list-x" -> ListXIcon(size = 40.dp, color = color)
        "lucide_loader-circle" -> LoaderCircleIcon(size = 40.dp, color = color)
        "lucide_loader" -> LoaderIcon(size = 40.dp, color = color)
        "lucide_locate-fixed" -> LocateFixedIcon(size = 40.dp, color = color)
        "lucide_locate" -> LocateIcon(size = 40.dp, color = color)
        "lucide_locate-off" -> LocateOffIcon(size = 40.dp, color = color)
        "lucide_lock" -> LockIcon(size = 40.dp, color = color)
        "lucide_lock-keyhole" -> LockKeyholeIcon(size = 40.dp, color = color)
        "lucide_lock-keyhole-open" -> LockKeyholeOpenIcon(size = 40.dp, color = color)
        "lucide_lock-open" -> LockOpenIcon(size = 40.dp, color = color)
        "lucide_log-in" -> LogInIcon(size = 40.dp, color = color)
        "lucide_log-out" -> LogOutIcon(size = 40.dp, color = color)
        "lucide_magnet" -> MagnetIcon(size = 40.dp, color = color)
        "lucide_mail-check" -> MailCheckIcon(size = 40.dp, color = color)
        "lucide_mail-clock" -> MailClockIcon(size = 40.dp, color = color)
        "lucide_mail" -> MailIcon(size = 40.dp, color = color)
        "lucide_mail-minus" -> MailMinusIcon(size = 40.dp, color = color)
        "lucide_mail-open" -> MailOpenIcon(size = 40.dp, color = color)
        "lucide_mail-plus" -> MailPlusIcon(size = 40.dp, color = color)
        "lucide_mail-search" -> MailSearchIcon(size = 40.dp, color = color)
        "lucide_mail-warning" -> MailWarningIcon(size = 40.dp, color = color)
        "lucide_mail-x" -> MailXIcon(size = 40.dp, color = color)
        "lucide_mailbox" -> MailboxIcon(size = 40.dp, color = color)
        "lucide_mails" -> MailsIcon(size = 40.dp, color = color)
        "lucide_map" -> MapIcon(size = 40.dp, color = color)
        "lucide_map-minus" -> MapMinusIcon(size = 40.dp, color = color)
        "lucide_map-pin-check" -> MapPinCheckIcon(size = 40.dp, color = color)
        "lucide_map-pin-check-inside" -> MapPinCheckInsideIcon(size = 40.dp, color = color)
        "lucide_map-pin-house" -> MapPinHouseIcon(size = 40.dp, color = color)
        "lucide_map-pin" -> MapPinIcon(size = 40.dp, color = color)
        "lucide_map-pin-minus" -> MapPinMinusIcon(size = 40.dp, color = color)
        "lucide_map-pin-minus-inside" -> MapPinMinusInsideIcon(size = 40.dp, color = color)
        "lucide_map-pin-off" -> MapPinOffIcon(size = 40.dp, color = color)
        "lucide_map-pin-pen" -> MapPinPenIcon(size = 40.dp, color = color)
        "lucide_map-pin-plus" -> MapPinPlusIcon(size = 40.dp, color = color)
        "lucide_map-pin-plus-inside" -> MapPinPlusInsideIcon(size = 40.dp, color = color)
        "lucide_map-pin-search" -> MapPinSearchIcon(size = 40.dp, color = color)
        "lucide_map-pin-x" -> MapPinXIcon(size = 40.dp, color = color)
        "lucide_map-pin-x-inside" -> MapPinXInsideIcon(size = 40.dp, color = color)
        "lucide_map-pinned" -> MapPinnedIcon(size = 40.dp, color = color)
        "lucide_map-plus" -> MapPlusIcon(size = 40.dp, color = color)
        "lucide_mars" -> MarsIcon(size = 40.dp, color = color)
        "lucide_maximize-2" -> Maximize2Icon(size = 40.dp, color = color)
        "lucide_maximize" -> MaximizeIcon(size = 40.dp, color = color)
        "lucide_medal" -> MedalIcon(size = 40.dp, color = color)
        "lucide_megaphone" -> MegaphoneIcon(size = 40.dp, color = color)
        "lucide_memory-stick" -> MemoryStickIcon(size = 40.dp, color = color)
        "lucide_menu" -> MenuIcon(size = 40.dp, color = color)
        "lucide_message-circle-check" -> MessageCircleCheckIcon(size = 40.dp, color = color)
        "lucide_message-circle-code" -> MessageCircleCodeIcon(size = 40.dp, color = color)
        "lucide_message-circle-dashed-check" -> MessageCircleDashedCheckIcon(size = 40.dp, color = color)
        "lucide_message-circle-dashed" -> MessageCircleDashedIcon(size = 40.dp, color = color)
        "lucide_message-circle-heart" -> MessageCircleHeartIcon(size = 40.dp, color = color)
        "lucide_message-circle" -> MessageCircleIcon(size = 40.dp, color = color)
        "lucide_message-circle-more" -> MessageCircleMoreIcon(size = 40.dp, color = color)
        "lucide_message-circle-off" -> MessageCircleOffIcon(size = 40.dp, color = color)
        "lucide_message-circle-plus" -> MessageCirclePlusIcon(size = 40.dp, color = color)
        "lucide_message-circle-question-mark" -> MessageCircleQuestionMarkIcon(size = 40.dp, color = color)
        "lucide_message-circle-reply" -> MessageCircleReplyIcon(size = 40.dp, color = color)
        "lucide_message-circle-warning" -> MessageCircleWarningIcon(size = 40.dp, color = color)
        "lucide_message-circle-x" -> MessageCircleXIcon(size = 40.dp, color = color)
        "lucide_message-square-check" -> MessageSquareCheckIcon(size = 40.dp, color = color)
        "lucide_message-square-code" -> MessageSquareCodeIcon(size = 40.dp, color = color)
        "lucide_message-square-dashed" -> MessageSquareDashedIcon(size = 40.dp, color = color)
        "lucide_message-square-diff" -> MessageSquareDiffIcon(size = 40.dp, color = color)
        "lucide_message-square-dot" -> MessageSquareDotIcon(size = 40.dp, color = color)
        "lucide_message-square-heart" -> MessageSquareHeartIcon(size = 40.dp, color = color)
        "lucide_message-square" -> MessageSquareIcon(size = 40.dp, color = color)
        "lucide_message-square-lock" -> MessageSquareLockIcon(size = 40.dp, color = color)
        "lucide_message-square-more" -> MessageSquareMoreIcon(size = 40.dp, color = color)
        "lucide_message-square-off" -> MessageSquareOffIcon(size = 40.dp, color = color)
        "lucide_message-square-plus" -> MessageSquarePlusIcon(size = 40.dp, color = color)
        "lucide_message-square-quote" -> MessageSquareQuoteIcon(size = 40.dp, color = color)
        "lucide_message-square-reply" -> MessageSquareReplyIcon(size = 40.dp, color = color)
        "lucide_message-square-share" -> MessageSquareShareIcon(size = 40.dp, color = color)
        "lucide_message-square-text" -> MessageSquareTextIcon(size = 40.dp, color = color)
        "lucide_message-square-warning" -> MessageSquareWarningIcon(size = 40.dp, color = color)
        "lucide_message-square-x" -> MessageSquareXIcon(size = 40.dp, color = color)
        "lucide_messages-circle" -> MessagesCircleIcon(size = 40.dp, color = color)
        "lucide_messages-square" -> MessagesSquareIcon(size = 40.dp, color = color)
        "lucide_mic-audio-lines" -> MicAudioLinesIcon(size = 40.dp, color = color)
        "lucide_mic" -> MicIcon(size = 40.dp, color = color)
        "lucide_mic-off" -> MicOffIcon(size = 40.dp, color = color)
        "lucide_mic-signal" -> MicSignalIcon(size = 40.dp, color = color)
        "lucide_mic-vocal" -> MicVocalIcon(size = 40.dp, color = color)
        "lucide_microchip" -> MicrochipIcon(size = 40.dp, color = color)
        "lucide_milestone" -> MilestoneIcon(size = 40.dp, color = color)
        "lucide_minimize-2" -> Minimize2Icon(size = 40.dp, color = color)
        else -> Unit
    }
}

@Composable
private fun LucideGalleryChunk6(id: String, color: Color) {
    when (id) {
        "lucide_minimize" -> MinimizeIcon(size = 40.dp, color = color)
        "lucide_minus" -> MinusIcon(size = 40.dp, color = color)
        "lucide_monitor-check" -> MonitorCheckIcon(size = 40.dp, color = color)
        "lucide_monitor-cloud" -> MonitorCloudIcon(size = 40.dp, color = color)
        "lucide_monitor-cog" -> MonitorCogIcon(size = 40.dp, color = color)
        "lucide_monitor-dot" -> MonitorDotIcon(size = 40.dp, color = color)
        "lucide_monitor-down" -> MonitorDownIcon(size = 40.dp, color = color)
        "lucide_monitor" -> MonitorIcon(size = 40.dp, color = color)
        "lucide_monitor-off" -> MonitorOffIcon(size = 40.dp, color = color)
        "lucide_monitor-pause" -> MonitorPauseIcon(size = 40.dp, color = color)
        "lucide_monitor-pc" -> MonitorPcIcon(size = 40.dp, color = color)
        "lucide_monitor-play" -> MonitorPlayIcon(size = 40.dp, color = color)
        "lucide_monitor-smartphone" -> MonitorSmartphoneIcon(size = 40.dp, color = color)
        "lucide_monitor-speaker" -> MonitorSpeakerIcon(size = 40.dp, color = color)
        "lucide_monitor-stop" -> MonitorStopIcon(size = 40.dp, color = color)
        "lucide_monitor-up" -> MonitorUpIcon(size = 40.dp, color = color)
        "lucide_monitor-x" -> MonitorXIcon(size = 40.dp, color = color)
        "lucide_moon" -> MoonIcon(size = 40.dp, color = color)
        "lucide_moon-star" -> MoonStarIcon(size = 40.dp, color = color)
        "lucide_mountain" -> MountainIcon(size = 40.dp, color = color)
        "lucide_mouse" -> MouseIcon(size = 40.dp, color = color)
        "lucide_mouse-pointer-click" -> MousePointerClickIcon(size = 40.dp, color = color)
        "lucide_mouse-pointer" -> MousePointerIcon(size = 40.dp, color = color)
        "lucide_move-diagonal-2" -> MoveDiagonal2Icon(size = 40.dp, color = color)
        "lucide_move-diagonal" -> MoveDiagonalIcon(size = 40.dp, color = color)
        "lucide_move-down" -> MoveDownIcon(size = 40.dp, color = color)
        "lucide_move-down-left" -> MoveDownLeftIcon(size = 40.dp, color = color)
        "lucide_move-down-right" -> MoveDownRightIcon(size = 40.dp, color = color)
        "lucide_move-horizontal" -> MoveHorizontalIcon(size = 40.dp, color = color)
        "lucide_move" -> MoveIcon(size = 40.dp, color = color)
        "lucide_move-left" -> MoveLeftIcon(size = 40.dp, color = color)
        "lucide_move-right" -> MoveRightIcon(size = 40.dp, color = color)
        "lucide_move-up" -> MoveUpIcon(size = 40.dp, color = color)
        "lucide_move-up-left" -> MoveUpLeftIcon(size = 40.dp, color = color)
        "lucide_move-up-right" -> MoveUpRightIcon(size = 40.dp, color = color)
        "lucide_move-vertical" -> MoveVerticalIcon(size = 40.dp, color = color)
        "lucide_music-2" -> Music2Icon(size = 40.dp, color = color)
        "lucide_music-3" -> Music3Icon(size = 40.dp, color = color)
        "lucide_music-4" -> Music4Icon(size = 40.dp, color = color)
        "lucide_music" -> MusicIcon(size = 40.dp, color = color)
        "lucide_navigation-2" -> Navigation2Icon(size = 40.dp, color = color)
        "lucide_navigation" -> NavigationIcon(size = 40.dp, color = color)
        "lucide_network" -> NetworkIcon(size = 40.dp, color = color)
        "lucide_nfc" -> NfcIcon(size = 40.dp, color = color)
        "lucide_notebook-pen" -> NotebookPenIcon(size = 40.dp, color = color)
        "lucide_octagon-alert" -> OctagonAlertIcon(size = 40.dp, color = color)
        "lucide_octagon" -> OctagonIcon(size = 40.dp, color = color)
        "lucide_octagon-minus" -> OctagonMinusIcon(size = 40.dp, color = color)
        "lucide_octagon-pause" -> OctagonPauseIcon(size = 40.dp, color = color)
        "lucide_octagon-x" -> OctagonXIcon(size = 40.dp, color = color)
        "lucide_orbit" -> OrbitIcon(size = 40.dp, color = color)
        "lucide_package-check" -> PackageCheckIcon(size = 40.dp, color = color)
        "lucide_package" -> PackageIcon(size = 40.dp, color = color)
        "lucide_package-open" -> PackageOpenIcon(size = 40.dp, color = color)
        "lucide_package-plus" -> PackagePlusIcon(size = 40.dp, color = color)
        "lucide_package-search" -> PackageSearchIcon(size = 40.dp, color = color)
        "lucide_package-x" -> PackageXIcon(size = 40.dp, color = color)
        "lucide_paint-bucket" -> PaintBucketIcon(size = 40.dp, color = color)
        "lucide_paintbrush" -> PaintbrushIcon(size = 40.dp, color = color)
        "lucide_palette" -> PaletteIcon(size = 40.dp, color = color)
        "lucide_panel-bottom-close" -> PanelBottomCloseIcon(size = 40.dp, color = color)
        "lucide_panel-bottom-dashed" -> PanelBottomDashedIcon(size = 40.dp, color = color)
        "lucide_panel-bottom" -> PanelBottomIcon(size = 40.dp, color = color)
        "lucide_panel-bottom-open" -> PanelBottomOpenIcon(size = 40.dp, color = color)
        "lucide_panel-left-close" -> PanelLeftCloseIcon(size = 40.dp, color = color)
        "lucide_panel-left-dashed" -> PanelLeftDashedIcon(size = 40.dp, color = color)
        "lucide_panel-left" -> PanelLeftIcon(size = 40.dp, color = color)
        "lucide_panel-left-open" -> PanelLeftOpenIcon(size = 40.dp, color = color)
        "lucide_panel-left-right-dashed" -> PanelLeftRightDashedIcon(size = 40.dp, color = color)
        "lucide_panel-right-close" -> PanelRightCloseIcon(size = 40.dp, color = color)
        "lucide_panel-right-dashed" -> PanelRightDashedIcon(size = 40.dp, color = color)
        "lucide_panel-right" -> PanelRightIcon(size = 40.dp, color = color)
        "lucide_panel-right-open" -> PanelRightOpenIcon(size = 40.dp, color = color)
        "lucide_panel-top-bottom-dashed" -> PanelTopBottomDashedIcon(size = 40.dp, color = color)
        "lucide_panel-top-close" -> PanelTopCloseIcon(size = 40.dp, color = color)
        "lucide_panel-top-dashed" -> PanelTopDashedIcon(size = 40.dp, color = color)
        "lucide_panel-top" -> PanelTopIcon(size = 40.dp, color = color)
        "lucide_panel-top-open" -> PanelTopOpenIcon(size = 40.dp, color = color)
        "lucide_paperclip" -> PaperclipIcon(size = 40.dp, color = color)
        "lucide_party-popper" -> PartyPopperIcon(size = 40.dp, color = color)
        "lucide_pause" -> LucidePauseIcon(size = 40.dp, color = color)
        "lucide_paw-print" -> PawPrintIcon(size = 40.dp, color = color)
        "lucide_pen-tool" -> PenToolIcon(size = 40.dp, color = color)
        "lucide_pencil" -> PencilIcon(size = 40.dp, color = color)
        "lucide_pentagon" -> PentagonIcon(size = 40.dp, color = color)
        "lucide_percent" -> PercentIcon(size = 40.dp, color = color)
        "lucide_philippine-peso" -> PhilippinePesoIcon(size = 40.dp, color = color)
        "lucide_phone-call" -> PhoneCallIcon(size = 40.dp, color = color)
        "lucide_phone-forwarded" -> PhoneForwardedIcon(size = 40.dp, color = color)
        "lucide_phone" -> PhoneIcon(size = 40.dp, color = color)
        "lucide_phone-incoming" -> PhoneIncomingIcon(size = 40.dp, color = color)
        "lucide_phone-missed" -> PhoneMissedIcon(size = 40.dp, color = color)
        "lucide_phone-off" -> PhoneOffIcon(size = 40.dp, color = color)
        "lucide_piano" -> PianoIcon(size = 40.dp, color = color)
        "lucide_picture-in-picture" -> PictureInPictureIcon(size = 40.dp, color = color)
        "lucide_piggy-bank" -> PiggyBankIcon(size = 40.dp, color = color)
        "lucide_pilcrow" -> PilcrowIcon(size = 40.dp, color = color)
        "lucide_pilcrow-left" -> PilcrowLeftIcon(size = 40.dp, color = color)
        "lucide_pilcrow-right" -> PilcrowRightIcon(size = 40.dp, color = color)
        "lucide_pill" -> PillIcon(size = 40.dp, color = color)
        else -> Unit
    }
}

@Composable
private fun LucideGalleryChunk7(id: String, color: Color) {
    when (id) {
        "lucide_pin" -> PinIcon(size = 40.dp, color = color)
        "lucide_pin-off" -> PinOffIcon(size = 40.dp, color = color)
        "lucide_pipette" -> PipetteIcon(size = 40.dp, color = color)
        "lucide_plane" -> PlaneIcon(size = 40.dp, color = color)
        "lucide_play" -> LucidePlayIcon(size = 40.dp, color = color)
        "lucide_play-off" -> PlayOffIcon(size = 40.dp, color = color)
        "lucide_plug-2" -> Plug2Icon(size = 40.dp, color = color)
        "lucide_plug" -> PlugIcon(size = 40.dp, color = color)
        "lucide_plug-zap" -> PlugZapIcon(size = 40.dp, color = color)
        "lucide_plus" -> PlusIcon(size = 40.dp, color = color)
        "lucide_podcast" -> PodcastIcon(size = 40.dp, color = color)
        "lucide_pound-sterling" -> PoundSterlingIcon(size = 40.dp, color = color)
        "lucide_power" -> PowerIcon(size = 40.dp, color = color)
        "lucide_power-off" -> PowerOffIcon(size = 40.dp, color = color)
        "lucide_presentation" -> PresentationIcon(size = 40.dp, color = color)
        "lucide_printer" -> PrinterIcon(size = 40.dp, color = color)
        "lucide_puzzle" -> PuzzleIcon(size = 40.dp, color = color)
        "lucide_qr-code" -> QrCodeIcon(size = 40.dp, color = color)
        "lucide_quote" -> QuoteIcon(size = 40.dp, color = color)
        "lucide_rabbit" -> RabbitIcon(size = 40.dp, color = color)
        "lucide_radar" -> RadarIcon(size = 40.dp, color = color)
        "lucide_radio" -> RadioIcon(size = 40.dp, color = color)
        "lucide_radio-off" -> RadioOffIcon(size = 40.dp, color = color)
        "lucide_radio-receiver" -> RadioReceiverIcon(size = 40.dp, color = color)
        "lucide_rainbow" -> RainbowIcon(size = 40.dp, color = color)
        "lucide_receipt" -> ReceiptIcon(size = 40.dp, color = color)
        "lucide_receipt-text" -> ReceiptTextIcon(size = 40.dp, color = color)
        "lucide_recycle" -> RecycleIcon(size = 40.dp, color = color)
        "lucide_redo" -> RedoIcon(size = 40.dp, color = color)
        "lucide_refresh-ccw-dot" -> RefreshCcwDotIcon(size = 40.dp, color = color)
        "lucide_refresh-ccw" -> RefreshCcwIcon(size = 40.dp, color = color)
        "lucide_refresh-cw" -> RefreshCwIcon(size = 40.dp, color = color)
        "lucide_refresh-cw-off" -> RefreshCwOffIcon(size = 40.dp, color = color)
        "lucide_regex" -> RegexIcon(size = 40.dp, color = color)
        "lucide_remove-formatting" -> RemoveFormattingIcon(size = 40.dp, color = color)
        "lucide_repeat-1" -> Repeat1Icon(size = 40.dp, color = color)
        "lucide_repeat-2" -> Repeat2Icon(size = 40.dp, color = color)
        "lucide_repeat" -> RepeatIcon(size = 40.dp, color = color)
        "lucide_reply-all" -> ReplyAllIcon(size = 40.dp, color = color)
        "lucide_reply" -> ReplyIcon(size = 40.dp, color = color)
        "lucide_rewind" -> RewindIcon(size = 40.dp, color = color)
        "lucide_rocket" -> RocketIcon(size = 40.dp, color = color)
        "lucide_rotate-ccw" -> RotateCcwIcon(size = 40.dp, color = color)
        "lucide_rotate-cw" -> RotateCwIcon(size = 40.dp, color = color)
        "lucide_route" -> RouteIcon(size = 40.dp, color = color)
        "lucide_route-off" -> RouteOffIcon(size = 40.dp, color = color)
        "lucide_router" -> RouterIcon(size = 40.dp, color = color)
        "lucide_rows-2" -> Rows2Icon(size = 40.dp, color = color)
        "lucide_rows-3" -> Rows3Icon(size = 40.dp, color = color)
        "lucide_rows-4" -> Rows4Icon(size = 40.dp, color = color)
        "lucide_rss" -> RssIcon(size = 40.dp, color = color)
        "lucide_ruler" -> RulerIcon(size = 40.dp, color = color)
        "lucide_russian-ruble" -> RussianRubleIcon(size = 40.dp, color = color)
        "lucide_saudi-riyal" -> SaudiRiyalIcon(size = 40.dp, color = color)
        "lucide_save" -> SaveIcon(size = 40.dp, color = color)
        "lucide_scan" -> ScanIcon(size = 40.dp, color = color)
        "lucide_scan-line" -> ScanLineIcon(size = 40.dp, color = color)
        "lucide_scan-qr-code" -> ScanQrCodeIcon(size = 40.dp, color = color)
        "lucide_school" -> SchoolIcon(size = 40.dp, color = color)
        "lucide_scissors" -> ScissorsIcon(size = 40.dp, color = color)
        "lucide_screen-share" -> ScreenShareIcon(size = 40.dp, color = color)
        "lucide_scroll-text" -> ScrollTextIcon(size = 40.dp, color = color)
        "lucide_search" -> SearchIcon(size = 40.dp, color = color)
        "lucide_search-x" -> SearchXIcon(size = 40.dp, color = color)
        "lucide_send-horizontal" -> SendHorizontalIcon(size = 40.dp, color = color)
        "lucide_send" -> SendIcon(size = 40.dp, color = color)
        "lucide_server-cog" -> ServerCogIcon(size = 40.dp, color = color)
        "lucide_server-crash" -> ServerCrashIcon(size = 40.dp, color = color)
        "lucide_server" -> ServerIcon(size = 40.dp, color = color)
        "lucide_server-off" -> ServerOffIcon(size = 40.dp, color = color)
        "lucide_server-plus" -> ServerPlusIcon(size = 40.dp, color = color)
        "lucide_settings" -> SettingsIcon(size = 40.dp, color = color)
        "lucide_shapes" -> ShapesIcon(size = 40.dp, color = color)
        "lucide_share" -> ShareIcon(size = 40.dp, color = color)
        "lucide_shield-alert" -> ShieldAlertIcon(size = 40.dp, color = color)
        "lucide_shield-ban" -> ShieldBanIcon(size = 40.dp, color = color)
        "lucide_shield-check" -> ShieldCheckIcon(size = 40.dp, color = color)
        "lucide_shield-cog-corner" -> ShieldCogCornerIcon(size = 40.dp, color = color)
        "lucide_shield-cog" -> ShieldCogIcon(size = 40.dp, color = color)
        "lucide_shield-ellipsis" -> ShieldEllipsisIcon(size = 40.dp, color = color)
        "lucide_shield-half" -> ShieldHalfIcon(size = 40.dp, color = color)
        "lucide_shield" -> ShieldIcon(size = 40.dp, color = color)
        "lucide_shield-keyhole" -> ShieldKeyholeIcon(size = 40.dp, color = color)
        "lucide_shield-lock" -> ShieldLockIcon(size = 40.dp, color = color)
        "lucide_shield-minus" -> ShieldMinusIcon(size = 40.dp, color = color)
        "lucide_shield-off" -> ShieldOffIcon(size = 40.dp, color = color)
        "lucide_shield-plus" -> ShieldPlusIcon(size = 40.dp, color = color)
        "lucide_shield-question-mark" -> ShieldQuestionMarkIcon(size = 40.dp, color = color)
        "lucide_shield-user" -> ShieldUserIcon(size = 40.dp, color = color)
        "lucide_shield-x" -> ShieldXIcon(size = 40.dp, color = color)
        "lucide_shopping-bag" -> ShoppingBagIcon(size = 40.dp, color = color)
        "lucide_shopping-basket" -> ShoppingBasketIcon(size = 40.dp, color = color)
        "lucide_shopping-cart" -> ShoppingCartIcon(size = 40.dp, color = color)
        "lucide_shopping-cart-minus" -> ShoppingCartMinusIcon(size = 40.dp, color = color)
        "lucide_shopping-cart-plus" -> ShoppingCartPlusIcon(size = 40.dp, color = color)
        "lucide_shrink" -> ShrinkIcon(size = 40.dp, color = color)
        "lucide_shuffle" -> ShuffleIcon(size = 40.dp, color = color)
        "lucide_sigma" -> SigmaIcon(size = 40.dp, color = color)
        "lucide_signal-high" -> SignalHighIcon(size = 40.dp, color = color)
        "lucide_signal" -> SignalIcon(size = 40.dp, color = color)
        else -> Unit
    }
}

@Composable
private fun LucideGalleryChunk8(id: String, color: Color) {
    when (id) {
        "lucide_signal-low" -> SignalLowIcon(size = 40.dp, color = color)
        "lucide_signal-medium" -> SignalMediumIcon(size = 40.dp, color = color)
        "lucide_signal-zero" -> SignalZeroIcon(size = 40.dp, color = color)
        "lucide_signature" -> SignatureIcon(size = 40.dp, color = color)
        "lucide_signpost-big" -> SignpostBigIcon(size = 40.dp, color = color)
        "lucide_signpost" -> SignpostIcon(size = 40.dp, color = color)
        "lucide_siren" -> SirenIcon(size = 40.dp, color = color)
        "lucide_skip-back" -> SkipBackIcon(size = 40.dp, color = color)
        "lucide_skip-forward" -> SkipForwardIcon(size = 40.dp, color = color)
        "lucide_sliders-horizontal" -> SlidersHorizontalIcon(size = 40.dp, color = color)
        "lucide_smartphone-charging" -> SmartphoneChargingIcon(size = 40.dp, color = color)
        "lucide_smartphone" -> SmartphoneIcon(size = 40.dp, color = color)
        "lucide_smartphone-nfc" -> SmartphoneNfcIcon(size = 40.dp, color = color)
        "lucide_snowflake" -> SnowflakeIcon(size = 40.dp, color = color)
        "lucide_sparkles" -> SparklesIcon(size = 40.dp, color = color)
        "lucide_speaker" -> SpeakerIcon(size = 40.dp, color = color)
        "lucide_spell-check" -> SpellCheckIcon(size = 40.dp, color = color)
        "lucide_spray-can" -> SprayCanIcon(size = 40.dp, color = color)
        "lucide_sprout" -> SproutIcon(size = 40.dp, color = color)
        "lucide_square-arrow-down" -> SquareArrowDownIcon(size = 40.dp, color = color)
        "lucide_square-arrow-down-left" -> SquareArrowDownLeftIcon(size = 40.dp, color = color)
        "lucide_square-arrow-down-right" -> SquareArrowDownRightIcon(size = 40.dp, color = color)
        "lucide_square-arrow-left" -> SquareArrowLeftIcon(size = 40.dp, color = color)
        "lucide_square-arrow-out-down-left" -> SquareArrowOutDownLeftIcon(size = 40.dp, color = color)
        "lucide_square-arrow-out-down-right" -> SquareArrowOutDownRightIcon(size = 40.dp, color = color)
        "lucide_square-arrow-out-up-left" -> SquareArrowOutUpLeftIcon(size = 40.dp, color = color)
        "lucide_square-arrow-out-up-right" -> SquareArrowOutUpRightIcon(size = 40.dp, color = color)
        "lucide_square-arrow-right-enter" -> SquareArrowRightEnterIcon(size = 40.dp, color = color)
        "lucide_square-arrow-right-exit" -> SquareArrowRightExitIcon(size = 40.dp, color = color)
        "lucide_square-arrow-right" -> SquareArrowRightIcon(size = 40.dp, color = color)
        "lucide_square-arrow-up" -> SquareArrowUpIcon(size = 40.dp, color = color)
        "lucide_square-arrow-up-left" -> SquareArrowUpLeftIcon(size = 40.dp, color = color)
        "lucide_square-arrow-up-right" -> SquareArrowUpRightIcon(size = 40.dp, color = color)
        "lucide_square-check-big" -> SquareCheckBigIcon(size = 40.dp, color = color)
        "lucide_square-check" -> SquareCheckIcon(size = 40.dp, color = color)
        "lucide_square-chevron-down" -> SquareChevronDownIcon(size = 40.dp, color = color)
        "lucide_square-chevron-left" -> SquareChevronLeftIcon(size = 40.dp, color = color)
        "lucide_square-chevron-right" -> SquareChevronRightIcon(size = 40.dp, color = color)
        "lucide_square-chevron-up" -> SquareChevronUpIcon(size = 40.dp, color = color)
        "lucide_square-code" -> SquareCodeIcon(size = 40.dp, color = color)
        "lucide_square-dashed" -> SquareDashedIcon(size = 40.dp, color = color)
        "lucide_square-dot" -> SquareDotIcon(size = 40.dp, color = color)
        "lucide_square" -> SquareIcon(size = 40.dp, color = color)
        "lucide_square-minus" -> SquareMinusIcon(size = 40.dp, color = color)
        "lucide_square-pause" -> SquarePauseIcon(size = 40.dp, color = color)
        "lucide_square-pen" -> SquarePenIcon(size = 40.dp, color = color)
        "lucide_square-play" -> SquarePlayIcon(size = 40.dp, color = color)
        "lucide_square-plus" -> SquarePlusIcon(size = 40.dp, color = color)
        "lucide_square-slash" -> SquareSlashIcon(size = 40.dp, color = color)
        "lucide_square-stop" -> SquareStopIcon(size = 40.dp, color = color)
        "lucide_square-terminal" -> SquareTerminalIcon(size = 40.dp, color = color)
        "lucide_square-user" -> SquareUserIcon(size = 40.dp, color = color)
        "lucide_square-user-round" -> SquareUserRoundIcon(size = 40.dp, color = color)
        "lucide_square-x" -> SquareXIcon(size = 40.dp, color = color)
        "lucide_stamp" -> StampIcon(size = 40.dp, color = color)
        "lucide_star-check" -> StarCheckIcon(size = 40.dp, color = color)
        "lucide_star-half" -> StarHalfIcon(size = 40.dp, color = color)
        "lucide_star" -> StarIcon(size = 40.dp, color = color)
        "lucide_star-minus" -> StarMinusIcon(size = 40.dp, color = color)
        "lucide_star-off" -> StarOffIcon(size = 40.dp, color = color)
        "lucide_star-plus" -> StarPlusIcon(size = 40.dp, color = color)
        "lucide_star-x" -> StarXIcon(size = 40.dp, color = color)
        "lucide_step-back" -> StepBackIcon(size = 40.dp, color = color)
        "lucide_step-forward" -> StepForwardIcon(size = 40.dp, color = color)
        "lucide_stethoscope" -> StethoscopeIcon(size = 40.dp, color = color)
        "lucide_sticky-note" -> StickyNoteIcon(size = 40.dp, color = color)
        "lucide_store" -> StoreIcon(size = 40.dp, color = color)
        "lucide_strikethrough" -> StrikethroughIcon(size = 40.dp, color = color)
        "lucide_subscript" -> SubscriptIcon(size = 40.dp, color = color)
        "lucide_sun-dim" -> SunDimIcon(size = 40.dp, color = color)
        "lucide_sun" -> SunIcon(size = 40.dp, color = color)
        "lucide_sun-medium" -> SunMediumIcon(size = 40.dp, color = color)
        "lucide_sun-moon" -> SunMoonIcon(size = 40.dp, color = color)
        "lucide_sun-snow" -> SunSnowIcon(size = 40.dp, color = color)
        "lucide_sunrise" -> SunriseIcon(size = 40.dp, color = color)
        "lucide_sunset" -> SunsetIcon(size = 40.dp, color = color)
        "lucide_superscript" -> SuperscriptIcon(size = 40.dp, color = color)
        "lucide_swiss-franc" -> SwissFrancIcon(size = 40.dp, color = color)
        "lucide_swords" -> SwordsIcon(size = 40.dp, color = color)
        "lucide_table" -> TableIcon(size = 40.dp, color = color)
        "lucide_tablet" -> TabletIcon(size = 40.dp, color = color)
        "lucide_tag" -> TagIcon(size = 40.dp, color = color)
        "lucide_tag-plus" -> TagPlusIcon(size = 40.dp, color = color)
        "lucide_tag-x" -> TagXIcon(size = 40.dp, color = color)
        "lucide_tags" -> TagsIcon(size = 40.dp, color = color)
        "lucide_target" -> TargetIcon(size = 40.dp, color = color)
        "lucide_telescope" -> TelescopeIcon(size = 40.dp, color = color)
        "lucide_tent" -> TentIcon(size = 40.dp, color = color)
        "lucide_terminal" -> TerminalIcon(size = 40.dp, color = color)
        "lucide_text-cursor" -> TextCursorIcon(size = 40.dp, color = color)
        "lucide_text-initial" -> TextInitialIcon(size = 40.dp, color = color)
        "lucide_text-quote" -> TextQuoteIcon(size = 40.dp, color = color)
        "lucide_text-search" -> TextSearchIcon(size = 40.dp, color = color)
        "lucide_thermometer" -> ThermometerIcon(size = 40.dp, color = color)
        "lucide_thermometer-sun" -> ThermometerSunIcon(size = 40.dp, color = color)
        "lucide_thumbs-down" -> ThumbsDownIcon(size = 40.dp, color = color)
        "lucide_thumbs-up" -> ThumbsUpIcon(size = 40.dp, color = color)
        "lucide_ticket-check" -> TicketCheckIcon(size = 40.dp, color = color)
        "lucide_ticket" -> TicketIcon(size = 40.dp, color = color)
        "lucide_ticket-minus" -> TicketMinusIcon(size = 40.dp, color = color)
        else -> Unit
    }
}

@Composable
private fun LucideGalleryChunk9(id: String, color: Color) {
    when (id) {
        "lucide_ticket-percent" -> TicketPercentIcon(size = 40.dp, color = color)
        "lucide_ticket-plus" -> TicketPlusIcon(size = 40.dp, color = color)
        "lucide_ticket-slash" -> TicketSlashIcon(size = 40.dp, color = color)
        "lucide_ticket-x" -> TicketXIcon(size = 40.dp, color = color)
        "lucide_timer" -> TimerIcon(size = 40.dp, color = color)
        "lucide_timer-off" -> TimerOffIcon(size = 40.dp, color = color)
        "lucide_timer-reset" -> TimerResetIcon(size = 40.dp, color = color)
        "lucide_toggle-left" -> ToggleLeftIcon(size = 40.dp, color = color)
        "lucide_toggle-right" -> ToggleRightIcon(size = 40.dp, color = color)
        "lucide_tornado" -> TornadoIcon(size = 40.dp, color = color)
        "lucide_traffic-cone" -> TrafficConeIcon(size = 40.dp, color = color)
        "lucide_trash-2" -> Trash2Icon(size = 40.dp, color = color)
        "lucide_trash" -> TrashIcon(size = 40.dp, color = color)
        "lucide_trending-down" -> TrendingDownIcon(size = 40.dp, color = color)
        "lucide_trending-up-down" -> TrendingUpDownIcon(size = 40.dp, color = color)
        "lucide_trending-up" -> TrendingUpIcon(size = 40.dp, color = color)
        "lucide_triangle-alert" -> TriangleAlertIcon(size = 40.dp, color = color)
        "lucide_triangle-dashed" -> TriangleDashedIcon(size = 40.dp, color = color)
        "lucide_triangle" -> TriangleIcon(size = 40.dp, color = color)
        "lucide_triangle-right" -> TriangleRightIcon(size = 40.dp, color = color)
        "lucide_trophy" -> TrophyIcon(size = 40.dp, color = color)
        "lucide_truck" -> TruckIcon(size = 40.dp, color = color)
        "lucide_turkish-lira" -> TurkishLiraIcon(size = 40.dp, color = color)
        "lucide_tv" -> TvIcon(size = 40.dp, color = color)
        "lucide_tv-minimal" -> TvMinimalIcon(size = 40.dp, color = color)
        "lucide_twitter" -> TwitterIcon(size = 40.dp, color = color)
        "lucide_type" -> TypeIcon(size = 40.dp, color = color)
        "lucide_umbrella" -> UmbrellaIcon(size = 40.dp, color = color)
        "lucide_underline" -> UnderlineIcon(size = 40.dp, color = color)
        "lucide_undo" -> UndoIcon(size = 40.dp, color = color)
        "lucide_unfold-vertical" -> UnfoldVerticalIcon(size = 40.dp, color = color)
        "lucide_university" -> UniversityIcon(size = 40.dp, color = color)
        "lucide_unlink" -> UnlinkIcon(size = 40.dp, color = color)
        "lucide_upload" -> UploadIcon(size = 40.dp, color = color)
        "lucide_usb" -> UsbIcon(size = 40.dp, color = color)
        "lucide_user-check" -> UserCheckIcon(size = 40.dp, color = color)
        "lucide_user-cog" -> UserCogIcon(size = 40.dp, color = color)
        "lucide_user-group" -> UserGroupIcon(size = 40.dp, color = color)
        "lucide_user" -> UserIcon(size = 40.dp, color = color)
        "lucide_user-key" -> UserKeyIcon(size = 40.dp, color = color)
        "lucide_user-lock" -> UserLockIcon(size = 40.dp, color = color)
        "lucide_user-minus" -> UserMinusIcon(size = 40.dp, color = color)
        "lucide_user-pen" -> UserPenIcon(size = 40.dp, color = color)
        "lucide_user-plus" -> UserPlusIcon(size = 40.dp, color = color)
        "lucide_user-round-arrow-left" -> UserRoundArrowLeftIcon(size = 40.dp, color = color)
        "lucide_user-round-check" -> UserRoundCheckIcon(size = 40.dp, color = color)
        "lucide_user-round-cog" -> UserRoundCogIcon(size = 40.dp, color = color)
        "lucide_user-round-group" -> UserRoundGroupIcon(size = 40.dp, color = color)
        "lucide_user-round" -> UserRoundIcon(size = 40.dp, color = color)
        "lucide_user-round-key" -> UserRoundKeyIcon(size = 40.dp, color = color)
        "lucide_user-round-minus" -> UserRoundMinusIcon(size = 40.dp, color = color)
        "lucide_user-round-pen" -> UserRoundPenIcon(size = 40.dp, color = color)
        "lucide_user-round-plus" -> UserRoundPlusIcon(size = 40.dp, color = color)
        "lucide_user-round-search" -> UserRoundSearchIcon(size = 40.dp, color = color)
        "lucide_user-round-x" -> UserRoundXIcon(size = 40.dp, color = color)
        "lucide_user-search" -> UserSearchIcon(size = 40.dp, color = color)
        "lucide_user-shield" -> UserShieldIcon(size = 40.dp, color = color)
        "lucide_user-star" -> UserStarIcon(size = 40.dp, color = color)
        "lucide_user-x" -> UserXIcon(size = 40.dp, color = color)
        "lucide_users" -> UsersIcon(size = 40.dp, color = color)
        "lucide_users-round" -> UsersRoundIcon(size = 40.dp, color = color)
        "lucide_utensils" -> UtensilsIcon(size = 40.dp, color = color)
        "lucide_variable" -> VariableIcon(size = 40.dp, color = color)
        "lucide_vault" -> VaultIcon(size = 40.dp, color = color)
        "lucide_venus" -> VenusIcon(size = 40.dp, color = color)
        "lucide_video" -> VideoIcon(size = 40.dp, color = color)
        "lucide_video-off" -> VideoOffIcon(size = 40.dp, color = color)
        "lucide_volume-1" -> Volume1Icon(size = 40.dp, color = color)
        "lucide_volume-2" -> Volume2Icon(size = 40.dp, color = color)
        "lucide_volume" -> VolumeIcon(size = 40.dp, color = color)
        "lucide_volume-off" -> VolumeOffIcon(size = 40.dp, color = color)
        "lucide_volume-x" -> VolumeXIcon(size = 40.dp, color = color)
        "lucide_wallet-cards" -> WalletCardsIcon(size = 40.dp, color = color)
        "lucide_wallet" -> WalletIcon(size = 40.dp, color = color)
        "lucide_wallet-minimal" -> WalletMinimalIcon(size = 40.dp, color = color)
        "lucide_wand-sparkles" -> WandSparklesIcon(size = 40.dp, color = color)
        "lucide_warehouse" -> WarehouseIcon(size = 40.dp, color = color)
        "lucide_watch" -> WatchIcon(size = 40.dp, color = color)
        "lucide_waypoints" -> WaypointsIcon(size = 40.dp, color = color)
        "lucide_webcam" -> WebcamIcon(size = 40.dp, color = color)
        "lucide_webcam-off" -> WebcamOffIcon(size = 40.dp, color = color)
        "lucide_webhook" -> WebhookIcon(size = 40.dp, color = color)
        "lucide_webhook-off" -> WebhookOffIcon(size = 40.dp, color = color)
        "lucide_wifi-cog" -> WifiCogIcon(size = 40.dp, color = color)
        "lucide_wifi-high" -> WifiHighIcon(size = 40.dp, color = color)
        "lucide_wifi" -> WifiIcon(size = 40.dp, color = color)
        "lucide_wifi-low" -> WifiLowIcon(size = 40.dp, color = color)
        "lucide_wifi-off" -> WifiOffIcon(size = 40.dp, color = color)
        "lucide_wifi-pen" -> WifiPenIcon(size = 40.dp, color = color)
        "lucide_wifi-sync" -> WifiSyncIcon(size = 40.dp, color = color)
        "lucide_wifi-zero" -> WifiZeroIcon(size = 40.dp, color = color)
        "lucide_wind" -> WindIcon(size = 40.dp, color = color)
        "lucide_workflow" -> WorkflowIcon(size = 40.dp, color = color)
        "lucide_wrap-text" -> WrapTextIcon(size = 40.dp, color = color)
        "lucide_wrench" -> WrenchIcon(size = 40.dp, color = color)
        "lucide_x" -> XIcon(size = 40.dp, color = color)
        "lucide_zap" -> ZapIcon(size = 40.dp, color = color)
        "lucide_zoom-in" -> ZoomInIcon(size = 40.dp, color = color)
        "lucide_zoom-out" -> ZoomOutIcon(size = 40.dp, color = color)
        else -> Unit
    }
}

internal fun lucideTitle(id: String): String? {
    lucideTitleChunk0(id)?.let { return it }
    lucideTitleChunk1(id)?.let { return it }
    lucideTitleChunk2(id)?.let { return it }
    lucideTitleChunk3(id)?.let { return it }
    lucideTitleChunk4(id)?.let { return it }
    lucideTitleChunk5(id)?.let { return it }
    lucideTitleChunk6(id)?.let { return it }
    lucideTitleChunk7(id)?.let { return it }
    lucideTitleChunk8(id)?.let { return it }
    lucideTitleChunk9(id)?.let { return it }
    return null
}

private fun lucideTitleChunk0(id: String): String? = when (id) {
    "lucide_a-arrow-down" -> "AArrowDownIcon"
    "lucide_a-arrow-up" -> "AArrowUpIcon"
    "lucide_accessibility" -> "AccessibilityIcon"
    "lucide_activity" -> "ActivityIcon"
    "lucide_alarm-clock-check" -> "AlarmClockCheckIcon"
    "lucide_alarm-clock" -> "AlarmClockIcon"
    "lucide_alarm-clock-minus" -> "AlarmClockMinusIcon"
    "lucide_alarm-clock-off" -> "AlarmClockOffIcon"
    "lucide_alarm-clock-plus" -> "AlarmClockPlusIcon"
    "lucide_align-center" -> "AlignCenterIcon"
    "lucide_align-justify" -> "AlignJustifyIcon"
    "lucide_align-left" -> "AlignLeftIcon"
    "lucide_align-right" -> "AlignRightIcon"
    "lucide_anchor" -> "AnchorIcon"
    "lucide_angry" -> "AngryIcon"
    "lucide_annoyed" -> "AnnoyedIcon"
    "lucide_app-window" -> "AppWindowIcon"
    "lucide_archive" -> "ArchiveIcon"
    "lucide_arrow-big-down-dash" -> "ArrowBigDownDashIcon"
    "lucide_arrow-big-down" -> "ArrowBigDownIcon"
    "lucide_arrow-big-left-dash" -> "ArrowBigLeftDashIcon"
    "lucide_arrow-big-left" -> "ArrowBigLeftIcon"
    "lucide_arrow-big-right-dash" -> "ArrowBigRightDashIcon"
    "lucide_arrow-big-right" -> "ArrowBigRightIcon"
    "lucide_arrow-big-up-dash" -> "ArrowBigUpDashIcon"
    "lucide_arrow-big-up" -> "ArrowBigUpIcon"
    "lucide_arrow-down-0-1" -> "ArrowDown01Icon"
    "lucide_arrow-down-1-0" -> "ArrowDown10Icon"
    "lucide_arrow-down-a-z" -> "ArrowDownAZIcon"
    "lucide_arrow-down-from-line" -> "ArrowDownFromLineIcon"
    "lucide_arrow-down" -> "ArrowDownIcon"
    "lucide_arrow-down-left" -> "ArrowDownLeftIcon"
    "lucide_arrow-down-narrow-wide" -> "ArrowDownNarrowWideIcon"
    "lucide_arrow-down-right" -> "ArrowDownRightIcon"
    "lucide_arrow-down-to-dot" -> "ArrowDownToDotIcon"
    "lucide_arrow-down-to-line" -> "ArrowDownToLineIcon"
    "lucide_arrow-down-up" -> "ArrowDownUpIcon"
    "lucide_arrow-down-wide-narrow" -> "ArrowDownWideNarrowIcon"
    "lucide_arrow-down-z-a" -> "ArrowDownZAIcon"
    "lucide_arrow-left-from-line" -> "ArrowLeftFromLineIcon"
    "lucide_arrow-left" -> "ArrowLeftIcon"
    "lucide_arrow-left-right" -> "ArrowLeftRightIcon"
    "lucide_arrow-left-to-line" -> "ArrowLeftToLineIcon"
    "lucide_arrow-right-from-line" -> "ArrowRightFromLineIcon"
    "lucide_arrow-right" -> "ArrowRightIcon"
    "lucide_arrow-right-left" -> "ArrowRightLeftIcon"
    "lucide_arrow-right-to-line" -> "ArrowRightToLineIcon"
    "lucide_arrow-up-0-1" -> "ArrowUp01Icon"
    "lucide_arrow-up-1-0" -> "ArrowUp10Icon"
    "lucide_arrow-up-a-z" -> "ArrowUpAZIcon"
    "lucide_arrow-up-down" -> "ArrowUpDownIcon"
    "lucide_arrow-up-from-dot" -> "ArrowUpFromDotIcon"
    "lucide_arrow-up-from-line" -> "ArrowUpFromLineIcon"
    "lucide_arrow-up" -> "ArrowUpIcon"
    "lucide_arrow-up-left" -> "ArrowUpLeftIcon"
    "lucide_arrow-up-narrow-wide" -> "ArrowUpNarrowWideIcon"
    "lucide_arrow-up-right" -> "ArrowUpRightIcon"
    "lucide_arrow-up-to-line" -> "ArrowUpToLineIcon"
    "lucide_arrow-up-wide-narrow" -> "ArrowUpWideNarrowIcon"
    "lucide_arrow-up-z-a" -> "ArrowUpZAIcon"
    "lucide_at-sign" -> "AtSignIcon"
    "lucide_atom" -> "AtomIcon"
    "lucide_audio-lines" -> "AudioLinesIcon"
    "lucide_audio-lines-off" -> "AudioLinesOffIcon"
    "lucide_audio-lines-x" -> "AudioLinesXIcon"
    "lucide_audio-waveform" -> "AudioWaveformIcon"
    "lucide_badge-cent" -> "BadgeCentIcon"
    "lucide_badge-check" -> "BadgeCheckIcon"
    "lucide_badge-dollar-sign" -> "BadgeDollarSignIcon"
    "lucide_badge-info" -> "BadgeInfoIcon"
    "lucide_badge-percent" -> "BadgePercentIcon"
    "lucide_baggage-claim" -> "BaggageClaimIcon"
    "lucide_ban" -> "BanIcon"
    "lucide_banknote-arrow-down" -> "BanknoteArrowDownIcon"
    "lucide_banknote-arrow-up" -> "BanknoteArrowUpIcon"
    "lucide_banknote-check" -> "BanknoteCheckIcon"
    "lucide_banknote" -> "BanknoteIcon"
    "lucide_banknote-x" -> "BanknoteXIcon"
    "lucide_barcode" -> "BarcodeIcon"
    "lucide_battery-charging" -> "BatteryChargingIcon"
    "lucide_battery-full" -> "BatteryFullIcon"
    "lucide_battery" -> "BatteryIcon"
    "lucide_battery-low" -> "BatteryLowIcon"
    "lucide_battery-medium" -> "BatteryMediumIcon"
    "lucide_battery-plus" -> "BatteryPlusIcon"
    "lucide_battery-warning" -> "BatteryWarningIcon"
    "lucide_bell-dot" -> "BellDotIcon"
    "lucide_bell-electric" -> "BellElectricIcon"
    "lucide_bell" -> "BellIcon"
    "lucide_bell-minus" -> "BellMinusIcon"
    "lucide_bell-off" -> "BellOffIcon"
    "lucide_bell-plus" -> "BellPlusIcon"
    "lucide_bell-ring" -> "BellRingIcon"
    "lucide_biceps-flexed" -> "BicepsFlexedIcon"
    "lucide_bike" -> "BikeIcon"
    "lucide_bitcoin" -> "BitcoinIcon"
    "lucide_blend" -> "BlendIcon"
    "lucide_blocks" -> "BlocksIcon"
    "lucide_bluetooth" -> "BluetoothIcon"
    "lucide_bold" -> "BoldIcon"
    else -> null
}

private fun lucideTitleChunk1(id: String): String? = when (id) {
    "lucide_bolt" -> "BoltIcon"
    "lucide_book-open-check" -> "BookOpenCheckIcon"
    "lucide_book-open" -> "BookOpenIcon"
    "lucide_book-open-text" -> "BookOpenTextIcon"
    "lucide_bookmark-check" -> "BookmarkCheckIcon"
    "lucide_bookmark" -> "BookmarkIcon"
    "lucide_bookmark-minus" -> "BookmarkMinusIcon"
    "lucide_bookmark-off" -> "BookmarkOffIcon"
    "lucide_bookmark-plus" -> "BookmarkPlusIcon"
    "lucide_bookmark-x" -> "BookmarkXIcon"
    "lucide_bot" -> "LucideBotIcon"
    "lucide_box" -> "BoxIcon"
    "lucide_boxes" -> "BoxesIcon"
    "lucide_brain" -> "BrainIcon"
    "lucide_briefcase" -> "BriefcaseIcon"
    "lucide_bring-to-front" -> "BringToFrontIcon"
    "lucide_bug" -> "BugIcon"
    "lucide_bug-off" -> "BugOffIcon"
    "lucide_bug-play" -> "BugPlayIcon"
    "lucide_building-2" -> "Building2Icon"
    "lucide_building-complex" -> "BuildingComplexIcon"
    "lucide_building-complex-plus" -> "BuildingComplexPlusIcon"
    "lucide_building" -> "BuildingIcon"
    "lucide_calculator" -> "CalculatorIcon"
    "lucide_calendar-arrow-down" -> "CalendarArrowDownIcon"
    "lucide_calendar-arrow-up" -> "CalendarArrowUpIcon"
    "lucide_calendar-check" -> "CalendarCheckIcon"
    "lucide_calendar-chevrons-right" -> "CalendarChevronsRightIcon"
    "lucide_calendar-clock" -> "CalendarClockIcon"
    "lucide_calendar-cog" -> "CalendarCogIcon"
    "lucide_calendar-days" -> "CalendarDaysIcon"
    "lucide_calendar-fold" -> "CalendarFoldIcon"
    "lucide_calendar-heart" -> "CalendarHeartIcon"
    "lucide_calendar" -> "CalendarIcon"
    "lucide_calendar-minus" -> "CalendarMinusIcon"
    "lucide_calendar-off" -> "CalendarOffIcon"
    "lucide_calendar-plus" -> "CalendarPlusIcon"
    "lucide_calendar-range" -> "CalendarRangeIcon"
    "lucide_calendar-search" -> "CalendarSearchIcon"
    "lucide_calendar-sync" -> "CalendarSyncIcon"
    "lucide_calendar-x" -> "CalendarXIcon"
    "lucide_calendars" -> "CalendarsIcon"
    "lucide_camera" -> "CameraIcon"
    "lucide_camera-off" -> "CameraOffIcon"
    "lucide_captions" -> "CaptionsIcon"
    "lucide_car" -> "CarIcon"
    "lucide_case-lower" -> "CaseLowerIcon"
    "lucide_case-sensitive" -> "CaseSensitiveIcon"
    "lucide_case-upper" -> "CaseUpperIcon"
    "lucide_cast" -> "CastIcon"
    "lucide_cat" -> "CatIcon"
    "lucide_cctv" -> "CctvIcon"
    "lucide_cctv-off" -> "CctvOffIcon"
    "lucide_chart-area" -> "ChartAreaIcon"
    "lucide_chart-bar-big" -> "ChartBarBigIcon"
    "lucide_chart-bar-decreasing" -> "ChartBarDecreasingIcon"
    "lucide_chart-bar" -> "ChartBarIcon"
    "lucide_chart-bar-increasing" -> "ChartBarIncreasingIcon"
    "lucide_chart-candlestick" -> "ChartCandlestickIcon"
    "lucide_chart-column-big" -> "ChartColumnBigIcon"
    "lucide_chart-column-decreasing" -> "ChartColumnDecreasingIcon"
    "lucide_chart-column" -> "ChartColumnIcon"
    "lucide_chart-column-increasing" -> "ChartColumnIncreasingIcon"
    "lucide_chart-gantt" -> "ChartGanttIcon"
    "lucide_chart-line" -> "ChartLineIcon"
    "lucide_chart-network" -> "ChartNetworkIcon"
    "lucide_chart-no-axes-column" -> "ChartNoAxesColumnIcon"
    "lucide_chart-no-axes-combined" -> "ChartNoAxesCombinedIcon"
    "lucide_chart-pie" -> "ChartPieIcon"
    "lucide_chart-scatter" -> "ChartScatterIcon"
    "lucide_chart-spline" -> "ChartSplineIcon"
    "lucide_check-check" -> "CheckCheckIcon"
    "lucide_check" -> "CheckIcon"
    "lucide_chevron-down" -> "ChevronDownIcon"
    "lucide_chevron-first" -> "ChevronFirstIcon"
    "lucide_chevron-last" -> "ChevronLastIcon"
    "lucide_chevron-left" -> "ChevronLeftIcon"
    "lucide_chevron-right" -> "ChevronRightIcon"
    "lucide_chevron-up" -> "ChevronUpIcon"
    "lucide_chevrons-down" -> "ChevronsDownIcon"
    "lucide_chevrons-down-up" -> "ChevronsDownUpIcon"
    "lucide_chevrons-left" -> "ChevronsLeftIcon"
    "lucide_chevrons-left-right-ellipsis" -> "ChevronsLeftRightEllipsisIcon"
    "lucide_chevrons-left-right" -> "ChevronsLeftRightIcon"
    "lucide_chevrons-right" -> "ChevronsRightIcon"
    "lucide_chevrons-right-left" -> "ChevronsRightLeftIcon"
    "lucide_chevrons-up-down" -> "ChevronsUpDownIcon"
    "lucide_chevrons-up" -> "ChevronsUpIcon"
    "lucide_chrome" -> "ChromeIcon"
    "lucide_circle-alert" -> "CircleAlertIcon"
    "lucide_circle-arrow-down" -> "CircleArrowDownIcon"
    "lucide_circle-arrow-left" -> "CircleArrowLeftIcon"
    "lucide_circle-arrow-out-down-left" -> "CircleArrowOutDownLeftIcon"
    "lucide_circle-arrow-out-down-right" -> "CircleArrowOutDownRightIcon"
    "lucide_circle-arrow-out-up-left" -> "CircleArrowOutUpLeftIcon"
    "lucide_circle-arrow-out-up-right" -> "CircleArrowOutUpRightIcon"
    "lucide_circle-arrow-right" -> "CircleArrowRightIcon"
    "lucide_circle-arrow-up" -> "CircleArrowUpIcon"
    "lucide_circle-check-big" -> "CircleCheckBigIcon"
    "lucide_circle-check" -> "CircleCheckIcon"
    else -> null
}

private fun lucideTitleChunk2(id: String): String? = when (id) {
    "lucide_circle-chevron-down" -> "CircleChevronDownIcon"
    "lucide_circle-chevron-left" -> "CircleChevronLeftIcon"
    "lucide_circle-chevron-right" -> "CircleChevronRightIcon"
    "lucide_circle-chevron-up" -> "CircleChevronUpIcon"
    "lucide_circle-dashed" -> "CircleDashedIcon"
    "lucide_circle-dollar-sign" -> "CircleDollarSignIcon"
    "lucide_circle-dot" -> "CircleDotIcon"
    "lucide_circle-ellipsis" -> "CircleEllipsisIcon"
    "lucide_circle-euro" -> "CircleEuroIcon"
    "lucide_circle" -> "CircleIcon"
    "lucide_circle-minus" -> "CircleMinusIcon"
    "lucide_circle-off" -> "CircleOffIcon"
    "lucide_circle-pause" -> "CirclePauseIcon"
    "lucide_circle-percent" -> "CirclePercentIcon"
    "lucide_circle-play" -> "CirclePlayIcon"
    "lucide_circle-plus" -> "CirclePlusIcon"
    "lucide_circle-pound-sterling" -> "CirclePoundSterlingIcon"
    "lucide_circle-power" -> "CirclePowerIcon"
    "lucide_circle-question-mark" -> "CircleQuestionMarkIcon"
    "lucide_circle-slash-2" -> "CircleSlash2Icon"
    "lucide_circle-slash" -> "CircleSlashIcon"
    "lucide_circle-star" -> "CircleStarIcon"
    "lucide_circle-stop" -> "CircleStopIcon"
    "lucide_circle-user" -> "CircleUserIcon"
    "lucide_circle-user-round" -> "CircleUserRoundIcon"
    "lucide_circle-x" -> "CircleXIcon"
    "lucide_clapperboard" -> "ClapperboardIcon"
    "lucide_clipboard-check" -> "ClipboardCheckIcon"
    "lucide_clipboard-clock" -> "ClipboardClockIcon"
    "lucide_clipboard" -> "ClipboardIcon"
    "lucide_clipboard-list" -> "ClipboardListIcon"
    "lucide_clipboard-paste" -> "ClipboardPasteIcon"
    "lucide_clock-alert" -> "ClockAlertIcon"
    "lucide_clock-arrow-down" -> "ClockArrowDownIcon"
    "lucide_clock-arrow-left" -> "ClockArrowLeftIcon"
    "lucide_clock-arrow-right" -> "ClockArrowRightIcon"
    "lucide_clock-arrow-up" -> "ClockArrowUpIcon"
    "lucide_clock-check" -> "ClockCheckIcon"
    "lucide_clock-fading" -> "ClockFadingIcon"
    "lucide_clock" -> "ClockIcon"
    "lucide_clock-plus" -> "ClockPlusIcon"
    "lucide_cloud-check" -> "CloudCheckIcon"
    "lucide_cloud-download" -> "CloudDownloadIcon"
    "lucide_cloud-drizzle" -> "CloudDrizzleIcon"
    "lucide_cloud-fog" -> "CloudFogIcon"
    "lucide_cloud-hail" -> "CloudHailIcon"
    "lucide_cloud" -> "CloudIcon"
    "lucide_cloud-lightning" -> "CloudLightningIcon"
    "lucide_cloud-moon" -> "CloudMoonIcon"
    "lucide_cloud-moon-rain" -> "CloudMoonRainIcon"
    "lucide_cloud-off" -> "CloudOffIcon"
    "lucide_cloud-rain" -> "CloudRainIcon"
    "lucide_cloud-rain-wind" -> "CloudRainWindIcon"
    "lucide_cloud-snow" -> "CloudSnowIcon"
    "lucide_cloud-sun" -> "CloudSunIcon"
    "lucide_cloud-sun-rain" -> "CloudSunRainIcon"
    "lucide_cloud-sync" -> "LucideCloudSyncIcon"
    "lucide_cloud-upload" -> "CloudUploadIcon"
    "lucide_cloudy" -> "CloudyIcon"
    "lucide_code" -> "CodeIcon"
    "lucide_code-xml" -> "CodeXmlIcon"
    "lucide_coffee" -> "CoffeeIcon"
    "lucide_coins" -> "CoinsIcon"
    "lucide_columns-2" -> "Columns2Icon"
    "lucide_columns-3" -> "Columns3Icon"
    "lucide_columns-4" -> "Columns4Icon"
    "lucide_command" -> "CommandIcon"
    "lucide_compass" -> "CompassIcon"
    "lucide_component" -> "ComponentIcon"
    "lucide_contact" -> "ContactIcon"
    "lucide_contact-round" -> "ContactRoundIcon"
    "lucide_contrast" -> "ContrastIcon"
    "lucide_cookie" -> "CookieIcon"
    "lucide_copy-check" -> "CopyCheckIcon"
    "lucide_copy" -> "CopyIcon"
    "lucide_copy-plus" -> "CopyPlusIcon"
    "lucide_corner-down-left" -> "CornerDownLeftIcon"
    "lucide_corner-down-right" -> "CornerDownRightIcon"
    "lucide_corner-left-down" -> "CornerLeftDownIcon"
    "lucide_corner-left-up" -> "CornerLeftUpIcon"
    "lucide_corner-right-down" -> "CornerRightDownIcon"
    "lucide_corner-right-up" -> "CornerRightUpIcon"
    "lucide_corner-up-left" -> "CornerUpLeftIcon"
    "lucide_corner-up-right" -> "CornerUpRightIcon"
    "lucide_cpu" -> "CpuIcon"
    "lucide_credit-card-check" -> "CreditCardCheckIcon"
    "lucide_credit-card" -> "CreditCardIcon"
    "lucide_credit-card-minus" -> "CreditCardMinusIcon"
    "lucide_credit-card-plus" -> "CreditCardPlusIcon"
    "lucide_credit-card-reader" -> "CreditCardReaderIcon"
    "lucide_credit-card-x" -> "CreditCardXIcon"
    "lucide_crop" -> "CropIcon"
    "lucide_crown" -> "CrownIcon"
    "lucide_database-arrow-down" -> "DatabaseArrowDownIcon"
    "lucide_database-arrow-up" -> "DatabaseArrowUpIcon"
    "lucide_database-backup" -> "DatabaseBackupIcon"
    "lucide_database-check" -> "DatabaseCheckIcon"
    "lucide_database" -> "DatabaseIcon"
    "lucide_database-minus" -> "DatabaseMinusIcon"
    "lucide_database-plus" -> "DatabasePlusIcon"
    else -> null
}

private fun lucideTitleChunk3(id: String): String? = when (id) {
    "lucide_database-search" -> "DatabaseSearchIcon"
    "lucide_database-x" -> "DatabaseXIcon"
    "lucide_database-zap" -> "DatabaseZapIcon"
    "lucide_delete" -> "DeleteIcon"
    "lucide_diamond" -> "DiamondIcon"
    "lucide_diamond-minus" -> "DiamondMinusIcon"
    "lucide_diamond-percent" -> "DiamondPercentIcon"
    "lucide_diamond-plus" -> "DiamondPlusIcon"
    "lucide_dice-5" -> "Dice5Icon"
    "lucide_diff" -> "DiffIcon"
    "lucide_disc-2" -> "Disc2Icon"
    "lucide_disc-3" -> "Disc3Icon"
    "lucide_disc-album" -> "DiscAlbumIcon"
    "lucide_disc" -> "DiscIcon"
    "lucide_dog" -> "DogIcon"
    "lucide_dollar-sign" -> "DollarSignIcon"
    "lucide_door-open" -> "DoorOpenIcon"
    "lucide_download" -> "DownloadIcon"
    "lucide_droplet" -> "DropletIcon"
    "lucide_droplets" -> "DropletsIcon"
    "lucide_drum" -> "DrumIcon"
    "lucide_dumbbell" -> "DumbbellIcon"
    "lucide_ellipsis" -> "EllipsisIcon"
    "lucide_ellipsis-vertical" -> "EllipsisVerticalIcon"
    "lucide_eraser" -> "EraserIcon"
    "lucide_euro" -> "EuroIcon"
    "lucide_expand" -> "ExpandIcon"
    "lucide_external-link" -> "ExternalLinkIcon"
    "lucide_eye-closed" -> "EyeClosedIcon"
    "lucide_eye" -> "EyeIcon"
    "lucide_eye-off" -> "EyeOffIcon"
    "lucide_facebook" -> "FacebookIcon"
    "lucide_factory" -> "FactoryIcon"
    "lucide_fast-forward" -> "FastForwardIcon"
    "lucide_feather" -> "FeatherIcon"
    "lucide_figma" -> "FigmaIcon"
    "lucide_file-archive" -> "FileArchiveIcon"
    "lucide_file-check" -> "FileCheckIcon"
    "lucide_file-clock" -> "FileClockIcon"
    "lucide_file-code" -> "FileCodeIcon"
    "lucide_file-down" -> "FileDownIcon"
    "lucide_file-heart" -> "FileHeartIcon"
    "lucide_file" -> "FileIcon"
    "lucide_file-image" -> "FileImageIcon"
    "lucide_file-key" -> "FileKeyIcon"
    "lucide_file-lock" -> "FileLockIcon"
    "lucide_file-minus" -> "FileMinusIcon"
    "lucide_file-music" -> "FileMusicIcon"
    "lucide_file-pen" -> "FilePenIcon"
    "lucide_file-plus" -> "FilePlusIcon"
    "lucide_file-scan" -> "FileScanIcon"
    "lucide_file-search" -> "FileSearchIcon"
    "lucide_file-spreadsheet" -> "FileSpreadsheetIcon"
    "lucide_file-terminal" -> "FileTerminalIcon"
    "lucide_file-text" -> "FileTextIcon"
    "lucide_file-type" -> "FileTypeIcon"
    "lucide_file-up" -> "FileUpIcon"
    "lucide_file-x" -> "FileXIcon"
    "lucide_files" -> "FilesIcon"
    "lucide_film" -> "FilmIcon"
    "lucide_filter" -> "FilterIcon"
    "lucide_fingerprint-pattern" -> "FingerprintPatternIcon"
    "lucide_fish" -> "FishIcon"
    "lucide_flag" -> "FlagIcon"
    "lucide_flag-off" -> "FlagOffIcon"
    "lucide_flag-triangle-right" -> "FlagTriangleRightIcon"
    "lucide_flame" -> "LucideFlameIcon"
    "lucide_flask-conical" -> "FlaskConicalIcon"
    "lucide_fold-vertical" -> "FoldVerticalIcon"
    "lucide_folder-check" -> "FolderCheckIcon"
    "lucide_folder-closed" -> "FolderClosedIcon"
    "lucide_folder-dot" -> "FolderDotIcon"
    "lucide_folder-heart" -> "FolderHeartIcon"
    "lucide_folder" -> "FolderIcon"
    "lucide_folder-key" -> "FolderKeyIcon"
    "lucide_folder-lock" -> "FolderLockIcon"
    "lucide_folder-minus" -> "FolderMinusIcon"
    "lucide_folder-open" -> "FolderOpenIcon"
    "lucide_folder-plus" -> "FolderPlusIcon"
    "lucide_folder-search" -> "FolderSearchIcon"
    "lucide_folder-tree" -> "FolderTreeIcon"
    "lucide_folder-x" -> "FolderXIcon"
    "lucide_footprints" -> "FootprintsIcon"
    "lucide_framer" -> "FramerIcon"
    "lucide_fullscreen" -> "FullscreenIcon"
    "lucide_funnel-x" -> "FunnelXIcon"
    "lucide_gallery-horizontal" -> "GalleryHorizontalIcon"
    "lucide_gamepad" -> "GamepadIcon"
    "lucide_gauge" -> "GaugeIcon"
    "lucide_gem" -> "GemIcon"
    "lucide_georgian-lari" -> "GeorgianLariIcon"
    "lucide_ghost" -> "GhostIcon"
    "lucide_gift" -> "GiftIcon"
    "lucide_git-branch" -> "GitBranchIcon"
    "lucide_git-branch-minus" -> "GitBranchMinusIcon"
    "lucide_git-branch-plus" -> "GitBranchPlusIcon"
    "lucide_git-commit-horizontal" -> "GitCommitHorizontalIcon"
    "lucide_git-commit-vertical" -> "GitCommitVerticalIcon"
    "lucide_git-compare-arrows" -> "GitCompareArrowsIcon"
    "lucide_git-compare" -> "GitCompareIcon"
    else -> null
}

private fun lucideTitleChunk4(id: String): String? = when (id) {
    "lucide_git-fork" -> "GitForkIcon"
    "lucide_git-graph" -> "GitGraphIcon"
    "lucide_git-merge-conflict" -> "GitMergeConflictIcon"
    "lucide_git-merge" -> "GitMergeIcon"
    "lucide_git-pull-request-arrow" -> "GitPullRequestArrowIcon"
    "lucide_git-pull-request-closed" -> "GitPullRequestClosedIcon"
    "lucide_git-pull-request-create-arrow" -> "GitPullRequestCreateArrowIcon"
    "lucide_git-pull-request-create" -> "GitPullRequestCreateIcon"
    "lucide_git-pull-request-draft" -> "GitPullRequestDraftIcon"
    "lucide_git-pull-request" -> "GitPullRequestIcon"
    "lucide_github" -> "GithubIcon"
    "lucide_gitlab" -> "GitlabIcon"
    "lucide_globe" -> "GlobeIcon"
    "lucide_globe-lock" -> "GlobeLockIcon"
    "lucide_graduation-cap" -> "GraduationCapIcon"
    "lucide_grid-2x2-check" -> "Grid2x2CheckIcon"
    "lucide_grid-2x2" -> "Grid2x2Icon"
    "lucide_grid-2x2-plus" -> "Grid2x2PlusIcon"
    "lucide_grid-2x2-x" -> "Grid2x2XIcon"
    "lucide_grid-3x2" -> "Grid3x2Icon"
    "lucide_grid-3x3" -> "Grid3x3Icon"
    "lucide_grip-vertical" -> "GripVerticalIcon"
    "lucide_guitar" -> "GuitarIcon"
    "lucide_hammer" -> "HammerIcon"
    "lucide_hand-coins" -> "HandCoinsIcon"
    "lucide_hand-heart" -> "HandHeartIcon"
    "lucide_hand" -> "HandIcon"
    "lucide_handshake" -> "HandshakeIcon"
    "lucide_hard-drive-download" -> "HardDriveDownloadIcon"
    "lucide_hard-drive" -> "HardDriveIcon"
    "lucide_hard-drive-upload" -> "HardDriveUploadIcon"
    "lucide_hash" -> "HashIcon"
    "lucide_haze" -> "HazeIcon"
    "lucide_heading-1" -> "Heading1Icon"
    "lucide_heading-2" -> "Heading2Icon"
    "lucide_heading-3" -> "Heading3Icon"
    "lucide_heading-4" -> "Heading4Icon"
    "lucide_heading-5" -> "Heading5Icon"
    "lucide_heading-6" -> "Heading6Icon"
    "lucide_heading" -> "HeadingIcon"
    "lucide_headphone-off" -> "HeadphoneOffIcon"
    "lucide_headphones" -> "HeadphonesIcon"
    "lucide_headset" -> "HeadsetIcon"
    "lucide_heart-crack" -> "HeartCrackIcon"
    "lucide_heart-handshake" -> "HeartHandshakeIcon"
    "lucide_heart" -> "HeartIcon"
    "lucide_heart-minus" -> "HeartMinusIcon"
    "lucide_heart-off" -> "HeartOffIcon"
    "lucide_heart-plus" -> "HeartPlusIcon"
    "lucide_heart-pulse" -> "HeartPulseIcon"
    "lucide_heart-x" -> "HeartXIcon"
    "lucide_hexagon" -> "HexagonIcon"
    "lucide_highlighter" -> "HighlighterIcon"
    "lucide_history" -> "HistoryIcon"
    "lucide_hospital" -> "HospitalIcon"
    "lucide_hotel" -> "HotelIcon"
    "lucide_hourglass" -> "HourglassIcon"
    "lucide_house-cog" -> "HouseCogIcon"
    "lucide_house-heart" -> "HouseHeartIcon"
    "lucide_house" -> "HouseIcon"
    "lucide_house-plug" -> "HousePlugIcon"
    "lucide_house-plus" -> "HousePlusIcon"
    "lucide_house-wifi" -> "HouseWifiIcon"
    "lucide_image" -> "ImageIcon"
    "lucide_image-off" -> "ImageOffIcon"
    "lucide_image-plus" -> "ImagePlusIcon"
    "lucide_inbox" -> "InboxIcon"
    "lucide_indent-decrease" -> "IndentDecreaseIcon"
    "lucide_indent-increase" -> "IndentIncreaseIcon"
    "lucide_indian-rupee" -> "IndianRupeeIcon"
    "lucide_infinity" -> "InfinityIcon"
    "lucide_info" -> "InfoIcon"
    "lucide_instagram" -> "InstagramIcon"
    "lucide_italic" -> "ItalicIcon"
    "lucide_japanese-yen" -> "JapaneseYenIcon"
    "lucide_kanban" -> "KanbanIcon"
    "lucide_key" -> "KeyIcon"
    "lucide_key-round" -> "KeyRoundIcon"
    "lucide_key-square" -> "KeySquareIcon"
    "lucide_keyboard" -> "KeyboardIcon"
    "lucide_landmark" -> "LandmarkIcon"
    "lucide_languages" -> "LanguagesIcon"
    "lucide_laptop" -> "LaptopIcon"
    "lucide_laptop-minimal-check" -> "LaptopMinimalCheckIcon"
    "lucide_laptop-minimal" -> "LaptopMinimalIcon"
    "lucide_layers" -> "LayersIcon"
    "lucide_layout-dashboard" -> "LayoutDashboardIcon"
    "lucide_layout-grid" -> "LayoutGridIcon"
    "lucide_layout-list" -> "LayoutListIcon"
    "lucide_layout-panel-left" -> "LayoutPanelLeftIcon"
    "lucide_layout-panel-top" -> "LayoutPanelTopIcon"
    "lucide_layout-template" -> "LayoutTemplateIcon"
    "lucide_leaf" -> "LeafIcon"
    "lucide_life-buoy" -> "LifeBuoyIcon"
    "lucide_lightbulb" -> "LightbulbIcon"
    "lucide_link" -> "LinkIcon"
    "lucide_linkedin" -> "LinkedinIcon"
    "lucide_list-check" -> "ListCheckIcon"
    "lucide_list-checks" -> "ListChecksIcon"
    "lucide_list-chevrons-down-up" -> "ListChevronsDownUpIcon"
    else -> null
}

private fun lucideTitleChunk5(id: String): String? = when (id) {
    "lucide_list-chevrons-up-down" -> "ListChevronsUpDownIcon"
    "lucide_list-collapse" -> "ListCollapseIcon"
    "lucide_list-end" -> "ListEndIcon"
    "lucide_list-filter" -> "ListFilterIcon"
    "lucide_list-filter-plus" -> "ListFilterPlusIcon"
    "lucide_list" -> "ListIcon"
    "lucide_list-minus" -> "ListMinusIcon"
    "lucide_list-ordered" -> "ListOrderedIcon"
    "lucide_list-plus" -> "ListPlusIcon"
    "lucide_list-start" -> "ListStartIcon"
    "lucide_list-todo" -> "ListTodoIcon"
    "lucide_list-tree" -> "ListTreeIcon"
    "lucide_list-x" -> "ListXIcon"
    "lucide_loader-circle" -> "LoaderCircleIcon"
    "lucide_loader" -> "LoaderIcon"
    "lucide_locate-fixed" -> "LocateFixedIcon"
    "lucide_locate" -> "LocateIcon"
    "lucide_locate-off" -> "LocateOffIcon"
    "lucide_lock" -> "LockIcon"
    "lucide_lock-keyhole" -> "LockKeyholeIcon"
    "lucide_lock-keyhole-open" -> "LockKeyholeOpenIcon"
    "lucide_lock-open" -> "LockOpenIcon"
    "lucide_log-in" -> "LogInIcon"
    "lucide_log-out" -> "LogOutIcon"
    "lucide_magnet" -> "MagnetIcon"
    "lucide_mail-check" -> "MailCheckIcon"
    "lucide_mail-clock" -> "MailClockIcon"
    "lucide_mail" -> "MailIcon"
    "lucide_mail-minus" -> "MailMinusIcon"
    "lucide_mail-open" -> "MailOpenIcon"
    "lucide_mail-plus" -> "MailPlusIcon"
    "lucide_mail-search" -> "MailSearchIcon"
    "lucide_mail-warning" -> "MailWarningIcon"
    "lucide_mail-x" -> "MailXIcon"
    "lucide_mailbox" -> "MailboxIcon"
    "lucide_mails" -> "MailsIcon"
    "lucide_map" -> "MapIcon"
    "lucide_map-minus" -> "MapMinusIcon"
    "lucide_map-pin-check" -> "MapPinCheckIcon"
    "lucide_map-pin-check-inside" -> "MapPinCheckInsideIcon"
    "lucide_map-pin-house" -> "MapPinHouseIcon"
    "lucide_map-pin" -> "MapPinIcon"
    "lucide_map-pin-minus" -> "MapPinMinusIcon"
    "lucide_map-pin-minus-inside" -> "MapPinMinusInsideIcon"
    "lucide_map-pin-off" -> "MapPinOffIcon"
    "lucide_map-pin-pen" -> "MapPinPenIcon"
    "lucide_map-pin-plus" -> "MapPinPlusIcon"
    "lucide_map-pin-plus-inside" -> "MapPinPlusInsideIcon"
    "lucide_map-pin-search" -> "MapPinSearchIcon"
    "lucide_map-pin-x" -> "MapPinXIcon"
    "lucide_map-pin-x-inside" -> "MapPinXInsideIcon"
    "lucide_map-pinned" -> "MapPinnedIcon"
    "lucide_map-plus" -> "MapPlusIcon"
    "lucide_mars" -> "MarsIcon"
    "lucide_maximize-2" -> "Maximize2Icon"
    "lucide_maximize" -> "MaximizeIcon"
    "lucide_medal" -> "MedalIcon"
    "lucide_megaphone" -> "MegaphoneIcon"
    "lucide_memory-stick" -> "MemoryStickIcon"
    "lucide_menu" -> "MenuIcon"
    "lucide_message-circle-check" -> "MessageCircleCheckIcon"
    "lucide_message-circle-code" -> "MessageCircleCodeIcon"
    "lucide_message-circle-dashed-check" -> "MessageCircleDashedCheckIcon"
    "lucide_message-circle-dashed" -> "MessageCircleDashedIcon"
    "lucide_message-circle-heart" -> "MessageCircleHeartIcon"
    "lucide_message-circle" -> "MessageCircleIcon"
    "lucide_message-circle-more" -> "MessageCircleMoreIcon"
    "lucide_message-circle-off" -> "MessageCircleOffIcon"
    "lucide_message-circle-plus" -> "MessageCirclePlusIcon"
    "lucide_message-circle-question-mark" -> "MessageCircleQuestionMarkIcon"
    "lucide_message-circle-reply" -> "MessageCircleReplyIcon"
    "lucide_message-circle-warning" -> "MessageCircleWarningIcon"
    "lucide_message-circle-x" -> "MessageCircleXIcon"
    "lucide_message-square-check" -> "MessageSquareCheckIcon"
    "lucide_message-square-code" -> "MessageSquareCodeIcon"
    "lucide_message-square-dashed" -> "MessageSquareDashedIcon"
    "lucide_message-square-diff" -> "MessageSquareDiffIcon"
    "lucide_message-square-dot" -> "MessageSquareDotIcon"
    "lucide_message-square-heart" -> "MessageSquareHeartIcon"
    "lucide_message-square" -> "MessageSquareIcon"
    "lucide_message-square-lock" -> "MessageSquareLockIcon"
    "lucide_message-square-more" -> "MessageSquareMoreIcon"
    "lucide_message-square-off" -> "MessageSquareOffIcon"
    "lucide_message-square-plus" -> "MessageSquarePlusIcon"
    "lucide_message-square-quote" -> "MessageSquareQuoteIcon"
    "lucide_message-square-reply" -> "MessageSquareReplyIcon"
    "lucide_message-square-share" -> "MessageSquareShareIcon"
    "lucide_message-square-text" -> "MessageSquareTextIcon"
    "lucide_message-square-warning" -> "MessageSquareWarningIcon"
    "lucide_message-square-x" -> "MessageSquareXIcon"
    "lucide_messages-circle" -> "MessagesCircleIcon"
    "lucide_messages-square" -> "MessagesSquareIcon"
    "lucide_mic-audio-lines" -> "MicAudioLinesIcon"
    "lucide_mic" -> "MicIcon"
    "lucide_mic-off" -> "MicOffIcon"
    "lucide_mic-signal" -> "MicSignalIcon"
    "lucide_mic-vocal" -> "MicVocalIcon"
    "lucide_microchip" -> "MicrochipIcon"
    "lucide_milestone" -> "MilestoneIcon"
    "lucide_minimize-2" -> "Minimize2Icon"
    else -> null
}

private fun lucideTitleChunk6(id: String): String? = when (id) {
    "lucide_minimize" -> "MinimizeIcon"
    "lucide_minus" -> "MinusIcon"
    "lucide_monitor-check" -> "MonitorCheckIcon"
    "lucide_monitor-cloud" -> "MonitorCloudIcon"
    "lucide_monitor-cog" -> "MonitorCogIcon"
    "lucide_monitor-dot" -> "MonitorDotIcon"
    "lucide_monitor-down" -> "MonitorDownIcon"
    "lucide_monitor" -> "MonitorIcon"
    "lucide_monitor-off" -> "MonitorOffIcon"
    "lucide_monitor-pause" -> "MonitorPauseIcon"
    "lucide_monitor-pc" -> "MonitorPcIcon"
    "lucide_monitor-play" -> "MonitorPlayIcon"
    "lucide_monitor-smartphone" -> "MonitorSmartphoneIcon"
    "lucide_monitor-speaker" -> "MonitorSpeakerIcon"
    "lucide_monitor-stop" -> "MonitorStopIcon"
    "lucide_monitor-up" -> "MonitorUpIcon"
    "lucide_monitor-x" -> "MonitorXIcon"
    "lucide_moon" -> "MoonIcon"
    "lucide_moon-star" -> "MoonStarIcon"
    "lucide_mountain" -> "MountainIcon"
    "lucide_mouse" -> "MouseIcon"
    "lucide_mouse-pointer-click" -> "MousePointerClickIcon"
    "lucide_mouse-pointer" -> "MousePointerIcon"
    "lucide_move-diagonal-2" -> "MoveDiagonal2Icon"
    "lucide_move-diagonal" -> "MoveDiagonalIcon"
    "lucide_move-down" -> "MoveDownIcon"
    "lucide_move-down-left" -> "MoveDownLeftIcon"
    "lucide_move-down-right" -> "MoveDownRightIcon"
    "lucide_move-horizontal" -> "MoveHorizontalIcon"
    "lucide_move" -> "MoveIcon"
    "lucide_move-left" -> "MoveLeftIcon"
    "lucide_move-right" -> "MoveRightIcon"
    "lucide_move-up" -> "MoveUpIcon"
    "lucide_move-up-left" -> "MoveUpLeftIcon"
    "lucide_move-up-right" -> "MoveUpRightIcon"
    "lucide_move-vertical" -> "MoveVerticalIcon"
    "lucide_music-2" -> "Music2Icon"
    "lucide_music-3" -> "Music3Icon"
    "lucide_music-4" -> "Music4Icon"
    "lucide_music" -> "MusicIcon"
    "lucide_navigation-2" -> "Navigation2Icon"
    "lucide_navigation" -> "NavigationIcon"
    "lucide_network" -> "NetworkIcon"
    "lucide_nfc" -> "NfcIcon"
    "lucide_notebook-pen" -> "NotebookPenIcon"
    "lucide_octagon-alert" -> "OctagonAlertIcon"
    "lucide_octagon" -> "OctagonIcon"
    "lucide_octagon-minus" -> "OctagonMinusIcon"
    "lucide_octagon-pause" -> "OctagonPauseIcon"
    "lucide_octagon-x" -> "OctagonXIcon"
    "lucide_orbit" -> "OrbitIcon"
    "lucide_package-check" -> "PackageCheckIcon"
    "lucide_package" -> "PackageIcon"
    "lucide_package-open" -> "PackageOpenIcon"
    "lucide_package-plus" -> "PackagePlusIcon"
    "lucide_package-search" -> "PackageSearchIcon"
    "lucide_package-x" -> "PackageXIcon"
    "lucide_paint-bucket" -> "PaintBucketIcon"
    "lucide_paintbrush" -> "PaintbrushIcon"
    "lucide_palette" -> "PaletteIcon"
    "lucide_panel-bottom-close" -> "PanelBottomCloseIcon"
    "lucide_panel-bottom-dashed" -> "PanelBottomDashedIcon"
    "lucide_panel-bottom" -> "PanelBottomIcon"
    "lucide_panel-bottom-open" -> "PanelBottomOpenIcon"
    "lucide_panel-left-close" -> "PanelLeftCloseIcon"
    "lucide_panel-left-dashed" -> "PanelLeftDashedIcon"
    "lucide_panel-left" -> "PanelLeftIcon"
    "lucide_panel-left-open" -> "PanelLeftOpenIcon"
    "lucide_panel-left-right-dashed" -> "PanelLeftRightDashedIcon"
    "lucide_panel-right-close" -> "PanelRightCloseIcon"
    "lucide_panel-right-dashed" -> "PanelRightDashedIcon"
    "lucide_panel-right" -> "PanelRightIcon"
    "lucide_panel-right-open" -> "PanelRightOpenIcon"
    "lucide_panel-top-bottom-dashed" -> "PanelTopBottomDashedIcon"
    "lucide_panel-top-close" -> "PanelTopCloseIcon"
    "lucide_panel-top-dashed" -> "PanelTopDashedIcon"
    "lucide_panel-top" -> "PanelTopIcon"
    "lucide_panel-top-open" -> "PanelTopOpenIcon"
    "lucide_paperclip" -> "PaperclipIcon"
    "lucide_party-popper" -> "PartyPopperIcon"
    "lucide_pause" -> "LucidePauseIcon"
    "lucide_paw-print" -> "PawPrintIcon"
    "lucide_pen-tool" -> "PenToolIcon"
    "lucide_pencil" -> "PencilIcon"
    "lucide_pentagon" -> "PentagonIcon"
    "lucide_percent" -> "PercentIcon"
    "lucide_philippine-peso" -> "PhilippinePesoIcon"
    "lucide_phone-call" -> "PhoneCallIcon"
    "lucide_phone-forwarded" -> "PhoneForwardedIcon"
    "lucide_phone" -> "PhoneIcon"
    "lucide_phone-incoming" -> "PhoneIncomingIcon"
    "lucide_phone-missed" -> "PhoneMissedIcon"
    "lucide_phone-off" -> "PhoneOffIcon"
    "lucide_piano" -> "PianoIcon"
    "lucide_picture-in-picture" -> "PictureInPictureIcon"
    "lucide_piggy-bank" -> "PiggyBankIcon"
    "lucide_pilcrow" -> "PilcrowIcon"
    "lucide_pilcrow-left" -> "PilcrowLeftIcon"
    "lucide_pilcrow-right" -> "PilcrowRightIcon"
    "lucide_pill" -> "PillIcon"
    else -> null
}

private fun lucideTitleChunk7(id: String): String? = when (id) {
    "lucide_pin" -> "PinIcon"
    "lucide_pin-off" -> "PinOffIcon"
    "lucide_pipette" -> "PipetteIcon"
    "lucide_plane" -> "PlaneIcon"
    "lucide_play" -> "LucidePlayIcon"
    "lucide_play-off" -> "PlayOffIcon"
    "lucide_plug-2" -> "Plug2Icon"
    "lucide_plug" -> "PlugIcon"
    "lucide_plug-zap" -> "PlugZapIcon"
    "lucide_plus" -> "PlusIcon"
    "lucide_podcast" -> "PodcastIcon"
    "lucide_pound-sterling" -> "PoundSterlingIcon"
    "lucide_power" -> "PowerIcon"
    "lucide_power-off" -> "PowerOffIcon"
    "lucide_presentation" -> "PresentationIcon"
    "lucide_printer" -> "PrinterIcon"
    "lucide_puzzle" -> "PuzzleIcon"
    "lucide_qr-code" -> "QrCodeIcon"
    "lucide_quote" -> "QuoteIcon"
    "lucide_rabbit" -> "RabbitIcon"
    "lucide_radar" -> "RadarIcon"
    "lucide_radio" -> "RadioIcon"
    "lucide_radio-off" -> "RadioOffIcon"
    "lucide_radio-receiver" -> "RadioReceiverIcon"
    "lucide_rainbow" -> "RainbowIcon"
    "lucide_receipt" -> "ReceiptIcon"
    "lucide_receipt-text" -> "ReceiptTextIcon"
    "lucide_recycle" -> "RecycleIcon"
    "lucide_redo" -> "RedoIcon"
    "lucide_refresh-ccw-dot" -> "RefreshCcwDotIcon"
    "lucide_refresh-ccw" -> "RefreshCcwIcon"
    "lucide_refresh-cw" -> "RefreshCwIcon"
    "lucide_refresh-cw-off" -> "RefreshCwOffIcon"
    "lucide_regex" -> "RegexIcon"
    "lucide_remove-formatting" -> "RemoveFormattingIcon"
    "lucide_repeat-1" -> "Repeat1Icon"
    "lucide_repeat-2" -> "Repeat2Icon"
    "lucide_repeat" -> "RepeatIcon"
    "lucide_reply-all" -> "ReplyAllIcon"
    "lucide_reply" -> "ReplyIcon"
    "lucide_rewind" -> "RewindIcon"
    "lucide_rocket" -> "RocketIcon"
    "lucide_rotate-ccw" -> "RotateCcwIcon"
    "lucide_rotate-cw" -> "RotateCwIcon"
    "lucide_route" -> "RouteIcon"
    "lucide_route-off" -> "RouteOffIcon"
    "lucide_router" -> "RouterIcon"
    "lucide_rows-2" -> "Rows2Icon"
    "lucide_rows-3" -> "Rows3Icon"
    "lucide_rows-4" -> "Rows4Icon"
    "lucide_rss" -> "RssIcon"
    "lucide_ruler" -> "RulerIcon"
    "lucide_russian-ruble" -> "RussianRubleIcon"
    "lucide_saudi-riyal" -> "SaudiRiyalIcon"
    "lucide_save" -> "SaveIcon"
    "lucide_scan" -> "ScanIcon"
    "lucide_scan-line" -> "ScanLineIcon"
    "lucide_scan-qr-code" -> "ScanQrCodeIcon"
    "lucide_school" -> "SchoolIcon"
    "lucide_scissors" -> "ScissorsIcon"
    "lucide_screen-share" -> "ScreenShareIcon"
    "lucide_scroll-text" -> "ScrollTextIcon"
    "lucide_search" -> "SearchIcon"
    "lucide_search-x" -> "SearchXIcon"
    "lucide_send-horizontal" -> "SendHorizontalIcon"
    "lucide_send" -> "SendIcon"
    "lucide_server-cog" -> "ServerCogIcon"
    "lucide_server-crash" -> "ServerCrashIcon"
    "lucide_server" -> "ServerIcon"
    "lucide_server-off" -> "ServerOffIcon"
    "lucide_server-plus" -> "ServerPlusIcon"
    "lucide_settings" -> "SettingsIcon"
    "lucide_shapes" -> "ShapesIcon"
    "lucide_share" -> "ShareIcon"
    "lucide_shield-alert" -> "ShieldAlertIcon"
    "lucide_shield-ban" -> "ShieldBanIcon"
    "lucide_shield-check" -> "ShieldCheckIcon"
    "lucide_shield-cog-corner" -> "ShieldCogCornerIcon"
    "lucide_shield-cog" -> "ShieldCogIcon"
    "lucide_shield-ellipsis" -> "ShieldEllipsisIcon"
    "lucide_shield-half" -> "ShieldHalfIcon"
    "lucide_shield" -> "ShieldIcon"
    "lucide_shield-keyhole" -> "ShieldKeyholeIcon"
    "lucide_shield-lock" -> "ShieldLockIcon"
    "lucide_shield-minus" -> "ShieldMinusIcon"
    "lucide_shield-off" -> "ShieldOffIcon"
    "lucide_shield-plus" -> "ShieldPlusIcon"
    "lucide_shield-question-mark" -> "ShieldQuestionMarkIcon"
    "lucide_shield-user" -> "ShieldUserIcon"
    "lucide_shield-x" -> "ShieldXIcon"
    "lucide_shopping-bag" -> "ShoppingBagIcon"
    "lucide_shopping-basket" -> "ShoppingBasketIcon"
    "lucide_shopping-cart" -> "ShoppingCartIcon"
    "lucide_shopping-cart-minus" -> "ShoppingCartMinusIcon"
    "lucide_shopping-cart-plus" -> "ShoppingCartPlusIcon"
    "lucide_shrink" -> "ShrinkIcon"
    "lucide_shuffle" -> "ShuffleIcon"
    "lucide_sigma" -> "SigmaIcon"
    "lucide_signal-high" -> "SignalHighIcon"
    "lucide_signal" -> "SignalIcon"
    else -> null
}

private fun lucideTitleChunk8(id: String): String? = when (id) {
    "lucide_signal-low" -> "SignalLowIcon"
    "lucide_signal-medium" -> "SignalMediumIcon"
    "lucide_signal-zero" -> "SignalZeroIcon"
    "lucide_signature" -> "SignatureIcon"
    "lucide_signpost-big" -> "SignpostBigIcon"
    "lucide_signpost" -> "SignpostIcon"
    "lucide_siren" -> "SirenIcon"
    "lucide_skip-back" -> "SkipBackIcon"
    "lucide_skip-forward" -> "SkipForwardIcon"
    "lucide_sliders-horizontal" -> "SlidersHorizontalIcon"
    "lucide_smartphone-charging" -> "SmartphoneChargingIcon"
    "lucide_smartphone" -> "SmartphoneIcon"
    "lucide_smartphone-nfc" -> "SmartphoneNfcIcon"
    "lucide_snowflake" -> "SnowflakeIcon"
    "lucide_sparkles" -> "SparklesIcon"
    "lucide_speaker" -> "SpeakerIcon"
    "lucide_spell-check" -> "SpellCheckIcon"
    "lucide_spray-can" -> "SprayCanIcon"
    "lucide_sprout" -> "SproutIcon"
    "lucide_square-arrow-down" -> "SquareArrowDownIcon"
    "lucide_square-arrow-down-left" -> "SquareArrowDownLeftIcon"
    "lucide_square-arrow-down-right" -> "SquareArrowDownRightIcon"
    "lucide_square-arrow-left" -> "SquareArrowLeftIcon"
    "lucide_square-arrow-out-down-left" -> "SquareArrowOutDownLeftIcon"
    "lucide_square-arrow-out-down-right" -> "SquareArrowOutDownRightIcon"
    "lucide_square-arrow-out-up-left" -> "SquareArrowOutUpLeftIcon"
    "lucide_square-arrow-out-up-right" -> "SquareArrowOutUpRightIcon"
    "lucide_square-arrow-right-enter" -> "SquareArrowRightEnterIcon"
    "lucide_square-arrow-right-exit" -> "SquareArrowRightExitIcon"
    "lucide_square-arrow-right" -> "SquareArrowRightIcon"
    "lucide_square-arrow-up" -> "SquareArrowUpIcon"
    "lucide_square-arrow-up-left" -> "SquareArrowUpLeftIcon"
    "lucide_square-arrow-up-right" -> "SquareArrowUpRightIcon"
    "lucide_square-check-big" -> "SquareCheckBigIcon"
    "lucide_square-check" -> "SquareCheckIcon"
    "lucide_square-chevron-down" -> "SquareChevronDownIcon"
    "lucide_square-chevron-left" -> "SquareChevronLeftIcon"
    "lucide_square-chevron-right" -> "SquareChevronRightIcon"
    "lucide_square-chevron-up" -> "SquareChevronUpIcon"
    "lucide_square-code" -> "SquareCodeIcon"
    "lucide_square-dashed" -> "SquareDashedIcon"
    "lucide_square-dot" -> "SquareDotIcon"
    "lucide_square" -> "SquareIcon"
    "lucide_square-minus" -> "SquareMinusIcon"
    "lucide_square-pause" -> "SquarePauseIcon"
    "lucide_square-pen" -> "SquarePenIcon"
    "lucide_square-play" -> "SquarePlayIcon"
    "lucide_square-plus" -> "SquarePlusIcon"
    "lucide_square-slash" -> "SquareSlashIcon"
    "lucide_square-stop" -> "SquareStopIcon"
    "lucide_square-terminal" -> "SquareTerminalIcon"
    "lucide_square-user" -> "SquareUserIcon"
    "lucide_square-user-round" -> "SquareUserRoundIcon"
    "lucide_square-x" -> "SquareXIcon"
    "lucide_stamp" -> "StampIcon"
    "lucide_star-check" -> "StarCheckIcon"
    "lucide_star-half" -> "StarHalfIcon"
    "lucide_star" -> "StarIcon"
    "lucide_star-minus" -> "StarMinusIcon"
    "lucide_star-off" -> "StarOffIcon"
    "lucide_star-plus" -> "StarPlusIcon"
    "lucide_star-x" -> "StarXIcon"
    "lucide_step-back" -> "StepBackIcon"
    "lucide_step-forward" -> "StepForwardIcon"
    "lucide_stethoscope" -> "StethoscopeIcon"
    "lucide_sticky-note" -> "StickyNoteIcon"
    "lucide_store" -> "StoreIcon"
    "lucide_strikethrough" -> "StrikethroughIcon"
    "lucide_subscript" -> "SubscriptIcon"
    "lucide_sun-dim" -> "SunDimIcon"
    "lucide_sun" -> "SunIcon"
    "lucide_sun-medium" -> "SunMediumIcon"
    "lucide_sun-moon" -> "SunMoonIcon"
    "lucide_sun-snow" -> "SunSnowIcon"
    "lucide_sunrise" -> "SunriseIcon"
    "lucide_sunset" -> "SunsetIcon"
    "lucide_superscript" -> "SuperscriptIcon"
    "lucide_swiss-franc" -> "SwissFrancIcon"
    "lucide_swords" -> "SwordsIcon"
    "lucide_table" -> "TableIcon"
    "lucide_tablet" -> "TabletIcon"
    "lucide_tag" -> "TagIcon"
    "lucide_tag-plus" -> "TagPlusIcon"
    "lucide_tag-x" -> "TagXIcon"
    "lucide_tags" -> "TagsIcon"
    "lucide_target" -> "TargetIcon"
    "lucide_telescope" -> "TelescopeIcon"
    "lucide_tent" -> "TentIcon"
    "lucide_terminal" -> "TerminalIcon"
    "lucide_text-cursor" -> "TextCursorIcon"
    "lucide_text-initial" -> "TextInitialIcon"
    "lucide_text-quote" -> "TextQuoteIcon"
    "lucide_text-search" -> "TextSearchIcon"
    "lucide_thermometer" -> "ThermometerIcon"
    "lucide_thermometer-sun" -> "ThermometerSunIcon"
    "lucide_thumbs-down" -> "ThumbsDownIcon"
    "lucide_thumbs-up" -> "ThumbsUpIcon"
    "lucide_ticket-check" -> "TicketCheckIcon"
    "lucide_ticket" -> "TicketIcon"
    "lucide_ticket-minus" -> "TicketMinusIcon"
    else -> null
}

private fun lucideTitleChunk9(id: String): String? = when (id) {
    "lucide_ticket-percent" -> "TicketPercentIcon"
    "lucide_ticket-plus" -> "TicketPlusIcon"
    "lucide_ticket-slash" -> "TicketSlashIcon"
    "lucide_ticket-x" -> "TicketXIcon"
    "lucide_timer" -> "TimerIcon"
    "lucide_timer-off" -> "TimerOffIcon"
    "lucide_timer-reset" -> "TimerResetIcon"
    "lucide_toggle-left" -> "ToggleLeftIcon"
    "lucide_toggle-right" -> "ToggleRightIcon"
    "lucide_tornado" -> "TornadoIcon"
    "lucide_traffic-cone" -> "TrafficConeIcon"
    "lucide_trash-2" -> "Trash2Icon"
    "lucide_trash" -> "TrashIcon"
    "lucide_trending-down" -> "TrendingDownIcon"
    "lucide_trending-up-down" -> "TrendingUpDownIcon"
    "lucide_trending-up" -> "TrendingUpIcon"
    "lucide_triangle-alert" -> "TriangleAlertIcon"
    "lucide_triangle-dashed" -> "TriangleDashedIcon"
    "lucide_triangle" -> "TriangleIcon"
    "lucide_triangle-right" -> "TriangleRightIcon"
    "lucide_trophy" -> "TrophyIcon"
    "lucide_truck" -> "TruckIcon"
    "lucide_turkish-lira" -> "TurkishLiraIcon"
    "lucide_tv" -> "TvIcon"
    "lucide_tv-minimal" -> "TvMinimalIcon"
    "lucide_twitter" -> "TwitterIcon"
    "lucide_type" -> "TypeIcon"
    "lucide_umbrella" -> "UmbrellaIcon"
    "lucide_underline" -> "UnderlineIcon"
    "lucide_undo" -> "UndoIcon"
    "lucide_unfold-vertical" -> "UnfoldVerticalIcon"
    "lucide_university" -> "UniversityIcon"
    "lucide_unlink" -> "UnlinkIcon"
    "lucide_upload" -> "UploadIcon"
    "lucide_usb" -> "UsbIcon"
    "lucide_user-check" -> "UserCheckIcon"
    "lucide_user-cog" -> "UserCogIcon"
    "lucide_user-group" -> "UserGroupIcon"
    "lucide_user" -> "UserIcon"
    "lucide_user-key" -> "UserKeyIcon"
    "lucide_user-lock" -> "UserLockIcon"
    "lucide_user-minus" -> "UserMinusIcon"
    "lucide_user-pen" -> "UserPenIcon"
    "lucide_user-plus" -> "UserPlusIcon"
    "lucide_user-round-arrow-left" -> "UserRoundArrowLeftIcon"
    "lucide_user-round-check" -> "UserRoundCheckIcon"
    "lucide_user-round-cog" -> "UserRoundCogIcon"
    "lucide_user-round-group" -> "UserRoundGroupIcon"
    "lucide_user-round" -> "UserRoundIcon"
    "lucide_user-round-key" -> "UserRoundKeyIcon"
    "lucide_user-round-minus" -> "UserRoundMinusIcon"
    "lucide_user-round-pen" -> "UserRoundPenIcon"
    "lucide_user-round-plus" -> "UserRoundPlusIcon"
    "lucide_user-round-search" -> "UserRoundSearchIcon"
    "lucide_user-round-x" -> "UserRoundXIcon"
    "lucide_user-search" -> "UserSearchIcon"
    "lucide_user-shield" -> "UserShieldIcon"
    "lucide_user-star" -> "UserStarIcon"
    "lucide_user-x" -> "UserXIcon"
    "lucide_users" -> "UsersIcon"
    "lucide_users-round" -> "UsersRoundIcon"
    "lucide_utensils" -> "UtensilsIcon"
    "lucide_variable" -> "VariableIcon"
    "lucide_vault" -> "VaultIcon"
    "lucide_venus" -> "VenusIcon"
    "lucide_video" -> "VideoIcon"
    "lucide_video-off" -> "VideoOffIcon"
    "lucide_volume-1" -> "Volume1Icon"
    "lucide_volume-2" -> "Volume2Icon"
    "lucide_volume" -> "VolumeIcon"
    "lucide_volume-off" -> "VolumeOffIcon"
    "lucide_volume-x" -> "VolumeXIcon"
    "lucide_wallet-cards" -> "WalletCardsIcon"
    "lucide_wallet" -> "WalletIcon"
    "lucide_wallet-minimal" -> "WalletMinimalIcon"
    "lucide_wand-sparkles" -> "WandSparklesIcon"
    "lucide_warehouse" -> "WarehouseIcon"
    "lucide_watch" -> "WatchIcon"
    "lucide_waypoints" -> "WaypointsIcon"
    "lucide_webcam" -> "WebcamIcon"
    "lucide_webcam-off" -> "WebcamOffIcon"
    "lucide_webhook" -> "WebhookIcon"
    "lucide_webhook-off" -> "WebhookOffIcon"
    "lucide_wifi-cog" -> "WifiCogIcon"
    "lucide_wifi-high" -> "WifiHighIcon"
    "lucide_wifi" -> "WifiIcon"
    "lucide_wifi-low" -> "WifiLowIcon"
    "lucide_wifi-off" -> "WifiOffIcon"
    "lucide_wifi-pen" -> "WifiPenIcon"
    "lucide_wifi-sync" -> "WifiSyncIcon"
    "lucide_wifi-zero" -> "WifiZeroIcon"
    "lucide_wind" -> "WindIcon"
    "lucide_workflow" -> "WorkflowIcon"
    "lucide_wrap-text" -> "WrapTextIcon"
    "lucide_wrench" -> "WrenchIcon"
    "lucide_x" -> "XIcon"
    "lucide_zap" -> "ZapIcon"
    "lucide_zoom-in" -> "ZoomInIcon"
    "lucide_zoom-out" -> "ZoomOutIcon"
    else -> null
}

@Composable
internal fun LucideDetailIcon(
    id: String,
    size: Dp,
    color: Color,
    duration: Float,
    states: LucideIconStates,
) {
    LucideDetailChunk0(id, size, color, duration, states)
    LucideDetailChunk1(id, size, color, duration, states)
    LucideDetailChunk2(id, size, color, duration, states)
    LucideDetailChunk3(id, size, color, duration, states)
    LucideDetailChunk4(id, size, color, duration, states)
    LucideDetailChunk5(id, size, color, duration, states)
    LucideDetailChunk6(id, size, color, duration, states)
    LucideDetailChunk7(id, size, color, duration, states)
    LucideDetailChunk8(id, size, color, duration, states)
    LucideDetailChunk9(id, size, color, duration, states)
}

@Composable
private fun LucideDetailChunk0(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_a-arrow-down" -> AArrowDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_a-arrow-down"] as AArrowDownIconState?,
        )
        "lucide_a-arrow-up" -> AArrowUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_a-arrow-up"] as AArrowUpIconState?,
        )
        "lucide_accessibility" -> AccessibilityIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_accessibility"] as AccessibilityIconState?,
        )
        "lucide_activity" -> ActivityIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_activity"] as ActivityIconState?,
        )
        "lucide_alarm-clock-check" -> AlarmClockCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_alarm-clock-check"] as AlarmClockCheckIconState?,
        )
        "lucide_alarm-clock" -> AlarmClockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_alarm-clock"] as AlarmClockIconState?,
        )
        "lucide_alarm-clock-minus" -> AlarmClockMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_alarm-clock-minus"] as AlarmClockMinusIconState?,
        )
        "lucide_alarm-clock-off" -> AlarmClockOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_alarm-clock-off"] as AlarmClockOffIconState?,
        )
        "lucide_alarm-clock-plus" -> AlarmClockPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_alarm-clock-plus"] as AlarmClockPlusIconState?,
        )
        "lucide_align-center" -> AlignCenterIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_align-center"] as AlignCenterIconState?,
        )
        "lucide_align-justify" -> AlignJustifyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_align-justify"] as AlignJustifyIconState?,
        )
        "lucide_align-left" -> AlignLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_align-left"] as AlignLeftIconState?,
        )
        "lucide_align-right" -> AlignRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_align-right"] as AlignRightIconState?,
        )
        "lucide_anchor" -> AnchorIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_anchor"] as AnchorIconState?,
        )
        "lucide_angry" -> AngryIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_angry"] as AngryIconState?,
        )
        "lucide_annoyed" -> AnnoyedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_annoyed"] as AnnoyedIconState?,
        )
        "lucide_app-window" -> AppWindowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_app-window"] as AppWindowIconState?,
        )
        "lucide_archive" -> ArchiveIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_archive"] as ArchiveIconState?,
        )
        "lucide_arrow-big-down-dash" -> ArrowBigDownDashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-big-down-dash"] as ArrowBigDownDashIconState?,
        )
        "lucide_arrow-big-down" -> ArrowBigDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-big-down"] as ArrowBigDownIconState?,
        )
        "lucide_arrow-big-left-dash" -> ArrowBigLeftDashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-big-left-dash"] as ArrowBigLeftDashIconState?,
        )
        "lucide_arrow-big-left" -> ArrowBigLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-big-left"] as ArrowBigLeftIconState?,
        )
        "lucide_arrow-big-right-dash" -> ArrowBigRightDashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-big-right-dash"] as ArrowBigRightDashIconState?,
        )
        "lucide_arrow-big-right" -> ArrowBigRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-big-right"] as ArrowBigRightIconState?,
        )
        "lucide_arrow-big-up-dash" -> ArrowBigUpDashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-big-up-dash"] as ArrowBigUpDashIconState?,
        )
        "lucide_arrow-big-up" -> ArrowBigUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-big-up"] as ArrowBigUpIconState?,
        )
        "lucide_arrow-down-0-1" -> ArrowDown01Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-0-1"] as ArrowDown01IconState?,
        )
        "lucide_arrow-down-1-0" -> ArrowDown10Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-1-0"] as ArrowDown10IconState?,
        )
        "lucide_arrow-down-a-z" -> ArrowDownAZIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-a-z"] as ArrowDownAZIconState?,
        )
        "lucide_arrow-down-from-line" -> ArrowDownFromLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-from-line"] as ArrowDownFromLineIconState?,
        )
        "lucide_arrow-down" -> ArrowDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down"] as ArrowDownIconState?,
        )
        "lucide_arrow-down-left" -> ArrowDownLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-left"] as ArrowDownLeftIconState?,
        )
        "lucide_arrow-down-narrow-wide" -> ArrowDownNarrowWideIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-narrow-wide"] as ArrowDownNarrowWideIconState?,
        )
        "lucide_arrow-down-right" -> ArrowDownRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-right"] as ArrowDownRightIconState?,
        )
        "lucide_arrow-down-to-dot" -> ArrowDownToDotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-to-dot"] as ArrowDownToDotIconState?,
        )
        "lucide_arrow-down-to-line" -> ArrowDownToLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-to-line"] as ArrowDownToLineIconState?,
        )
        "lucide_arrow-down-up" -> ArrowDownUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-up"] as ArrowDownUpIconState?,
        )
        "lucide_arrow-down-wide-narrow" -> ArrowDownWideNarrowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-wide-narrow"] as ArrowDownWideNarrowIconState?,
        )
        "lucide_arrow-down-z-a" -> ArrowDownZAIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-down-z-a"] as ArrowDownZAIconState?,
        )
        "lucide_arrow-left-from-line" -> ArrowLeftFromLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-left-from-line"] as ArrowLeftFromLineIconState?,
        )
        "lucide_arrow-left" -> ArrowLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-left"] as ArrowLeftIconState?,
        )
        "lucide_arrow-left-right" -> ArrowLeftRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-left-right"] as ArrowLeftRightIconState?,
        )
        "lucide_arrow-left-to-line" -> ArrowLeftToLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-left-to-line"] as ArrowLeftToLineIconState?,
        )
        "lucide_arrow-right-from-line" -> ArrowRightFromLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-right-from-line"] as ArrowRightFromLineIconState?,
        )
        "lucide_arrow-right" -> ArrowRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-right"] as ArrowRightIconState?,
        )
        "lucide_arrow-right-left" -> ArrowRightLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-right-left"] as ArrowRightLeftIconState?,
        )
        "lucide_arrow-right-to-line" -> ArrowRightToLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-right-to-line"] as ArrowRightToLineIconState?,
        )
        "lucide_arrow-up-0-1" -> ArrowUp01Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-0-1"] as ArrowUp01IconState?,
        )
        "lucide_arrow-up-1-0" -> ArrowUp10Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-1-0"] as ArrowUp10IconState?,
        )
        "lucide_arrow-up-a-z" -> ArrowUpAZIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-a-z"] as ArrowUpAZIconState?,
        )
        "lucide_arrow-up-down" -> ArrowUpDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-down"] as ArrowUpDownIconState?,
        )
        "lucide_arrow-up-from-dot" -> ArrowUpFromDotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-from-dot"] as ArrowUpFromDotIconState?,
        )
        "lucide_arrow-up-from-line" -> ArrowUpFromLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-from-line"] as ArrowUpFromLineIconState?,
        )
        "lucide_arrow-up" -> ArrowUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up"] as ArrowUpIconState?,
        )
        "lucide_arrow-up-left" -> ArrowUpLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-left"] as ArrowUpLeftIconState?,
        )
        "lucide_arrow-up-narrow-wide" -> ArrowUpNarrowWideIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-narrow-wide"] as ArrowUpNarrowWideIconState?,
        )
        "lucide_arrow-up-right" -> ArrowUpRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-right"] as ArrowUpRightIconState?,
        )
        "lucide_arrow-up-to-line" -> ArrowUpToLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-to-line"] as ArrowUpToLineIconState?,
        )
        "lucide_arrow-up-wide-narrow" -> ArrowUpWideNarrowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-wide-narrow"] as ArrowUpWideNarrowIconState?,
        )
        "lucide_arrow-up-z-a" -> ArrowUpZAIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_arrow-up-z-a"] as ArrowUpZAIconState?,
        )
        "lucide_at-sign" -> AtSignIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_at-sign"] as AtSignIconState?,
        )
        "lucide_atom" -> AtomIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_atom"] as AtomIconState?,
        )
        "lucide_audio-lines" -> AudioLinesIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_audio-lines"] as AudioLinesIconState?,
        )
        "lucide_audio-lines-off" -> AudioLinesOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_audio-lines-off"] as AudioLinesOffIconState?,
        )
        "lucide_audio-lines-x" -> AudioLinesXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_audio-lines-x"] as AudioLinesXIconState?,
        )
        "lucide_audio-waveform" -> AudioWaveformIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_audio-waveform"] as AudioWaveformIconState?,
        )
        "lucide_badge-cent" -> BadgeCentIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_badge-cent"] as BadgeCentIconState?,
        )
        "lucide_badge-check" -> BadgeCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_badge-check"] as BadgeCheckIconState?,
        )
        "lucide_badge-dollar-sign" -> BadgeDollarSignIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_badge-dollar-sign"] as BadgeDollarSignIconState?,
        )
        "lucide_badge-info" -> BadgeInfoIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_badge-info"] as BadgeInfoIconState?,
        )
        "lucide_badge-percent" -> BadgePercentIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_badge-percent"] as BadgePercentIconState?,
        )
        "lucide_baggage-claim" -> BaggageClaimIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_baggage-claim"] as BaggageClaimIconState?,
        )
        "lucide_ban" -> BanIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ban"] as BanIconState?,
        )
        "lucide_banknote-arrow-down" -> BanknoteArrowDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_banknote-arrow-down"] as BanknoteArrowDownIconState?,
        )
        "lucide_banknote-arrow-up" -> BanknoteArrowUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_banknote-arrow-up"] as BanknoteArrowUpIconState?,
        )
        "lucide_banknote-check" -> BanknoteCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_banknote-check"] as BanknoteCheckIconState?,
        )
        "lucide_banknote" -> BanknoteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_banknote"] as BanknoteIconState?,
        )
        "lucide_banknote-x" -> BanknoteXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_banknote-x"] as BanknoteXIconState?,
        )
        "lucide_barcode" -> BarcodeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_barcode"] as BarcodeIconState?,
        )
        "lucide_battery-charging" -> BatteryChargingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_battery-charging"] as BatteryChargingIconState?,
        )
        "lucide_battery-full" -> BatteryFullIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_battery-full"] as BatteryFullIconState?,
        )
        "lucide_battery" -> BatteryIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_battery"] as BatteryIconState?,
        )
        "lucide_battery-low" -> BatteryLowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_battery-low"] as BatteryLowIconState?,
        )
        "lucide_battery-medium" -> BatteryMediumIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_battery-medium"] as BatteryMediumIconState?,
        )
        "lucide_battery-plus" -> BatteryPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_battery-plus"] as BatteryPlusIconState?,
        )
        "lucide_battery-warning" -> BatteryWarningIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_battery-warning"] as BatteryWarningIconState?,
        )
        "lucide_bell-dot" -> BellDotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bell-dot"] as BellDotIconState?,
        )
        "lucide_bell-electric" -> BellElectricIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bell-electric"] as BellElectricIconState?,
        )
        "lucide_bell" -> BellIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bell"] as BellIconState?,
        )
        "lucide_bell-minus" -> BellMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bell-minus"] as BellMinusIconState?,
        )
        "lucide_bell-off" -> BellOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bell-off"] as BellOffIconState?,
        )
        "lucide_bell-plus" -> BellPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bell-plus"] as BellPlusIconState?,
        )
        "lucide_bell-ring" -> BellRingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bell-ring"] as BellRingIconState?,
        )
        "lucide_biceps-flexed" -> BicepsFlexedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_biceps-flexed"] as BicepsFlexedIconState?,
        )
        "lucide_bike" -> BikeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bike"] as BikeIconState?,
        )
        "lucide_bitcoin" -> BitcoinIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bitcoin"] as BitcoinIconState?,
        )
        "lucide_blend" -> BlendIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_blend"] as BlendIconState?,
        )
        "lucide_blocks" -> BlocksIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_blocks"] as BlocksIconState?,
        )
        "lucide_bluetooth" -> BluetoothIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bluetooth"] as BluetoothIconState?,
        )
        "lucide_bold" -> BoldIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bold"] as BoldIconState?,
        )
        else -> Unit
    }
}

@Composable
private fun LucideDetailChunk1(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_bolt" -> BoltIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bolt"] as BoltIconState?,
        )
        "lucide_book-open-check" -> BookOpenCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_book-open-check"] as BookOpenCheckIconState?,
        )
        "lucide_book-open" -> BookOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_book-open"] as BookOpenIconState?,
        )
        "lucide_book-open-text" -> BookOpenTextIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_book-open-text"] as BookOpenTextIconState?,
        )
        "lucide_bookmark-check" -> BookmarkCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bookmark-check"] as BookmarkCheckIconState?,
        )
        "lucide_bookmark" -> BookmarkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bookmark"] as BookmarkIconState?,
        )
        "lucide_bookmark-minus" -> BookmarkMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bookmark-minus"] as BookmarkMinusIconState?,
        )
        "lucide_bookmark-off" -> BookmarkOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bookmark-off"] as BookmarkOffIconState?,
        )
        "lucide_bookmark-plus" -> BookmarkPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bookmark-plus"] as BookmarkPlusIconState?,
        )
        "lucide_bookmark-x" -> BookmarkXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bookmark-x"] as BookmarkXIconState?,
        )
        "lucide_bot" -> LucideBotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bot"] as LucideBotIconState?,
        )
        "lucide_box" -> BoxIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_box"] as BoxIconState?,
        )
        "lucide_boxes" -> BoxesIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_boxes"] as BoxesIconState?,
        )
        "lucide_brain" -> BrainIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_brain"] as BrainIconState?,
        )
        "lucide_briefcase" -> BriefcaseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_briefcase"] as BriefcaseIconState?,
        )
        "lucide_bring-to-front" -> BringToFrontIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bring-to-front"] as BringToFrontIconState?,
        )
        "lucide_bug" -> BugIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bug"] as BugIconState?,
        )
        "lucide_bug-off" -> BugOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bug-off"] as BugOffIconState?,
        )
        "lucide_bug-play" -> BugPlayIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_bug-play"] as BugPlayIconState?,
        )
        "lucide_building-2" -> Building2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_building-2"] as Building2IconState?,
        )
        "lucide_building-complex" -> BuildingComplexIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_building-complex"] as BuildingComplexIconState?,
        )
        "lucide_building-complex-plus" -> BuildingComplexPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_building-complex-plus"] as BuildingComplexPlusIconState?,
        )
        "lucide_building" -> BuildingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_building"] as BuildingIconState?,
        )
        "lucide_calculator" -> CalculatorIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calculator"] as CalculatorIconState?,
        )
        "lucide_calendar-arrow-down" -> CalendarArrowDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-arrow-down"] as CalendarArrowDownIconState?,
        )
        "lucide_calendar-arrow-up" -> CalendarArrowUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-arrow-up"] as CalendarArrowUpIconState?,
        )
        "lucide_calendar-check" -> CalendarCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-check"] as CalendarCheckIconState?,
        )
        "lucide_calendar-chevrons-right" -> CalendarChevronsRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-chevrons-right"] as CalendarChevronsRightIconState?,
        )
        "lucide_calendar-clock" -> CalendarClockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-clock"] as CalendarClockIconState?,
        )
        "lucide_calendar-cog" -> CalendarCogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-cog"] as CalendarCogIconState?,
        )
        "lucide_calendar-days" -> CalendarDaysIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-days"] as CalendarDaysIconState?,
        )
        "lucide_calendar-fold" -> CalendarFoldIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-fold"] as CalendarFoldIconState?,
        )
        "lucide_calendar-heart" -> CalendarHeartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-heart"] as CalendarHeartIconState?,
        )
        "lucide_calendar" -> CalendarIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar"] as CalendarIconState?,
        )
        "lucide_calendar-minus" -> CalendarMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-minus"] as CalendarMinusIconState?,
        )
        "lucide_calendar-off" -> CalendarOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-off"] as CalendarOffIconState?,
        )
        "lucide_calendar-plus" -> CalendarPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-plus"] as CalendarPlusIconState?,
        )
        "lucide_calendar-range" -> CalendarRangeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-range"] as CalendarRangeIconState?,
        )
        "lucide_calendar-search" -> CalendarSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-search"] as CalendarSearchIconState?,
        )
        "lucide_calendar-sync" -> CalendarSyncIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-sync"] as CalendarSyncIconState?,
        )
        "lucide_calendar-x" -> CalendarXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendar-x"] as CalendarXIconState?,
        )
        "lucide_calendars" -> CalendarsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_calendars"] as CalendarsIconState?,
        )
        "lucide_camera" -> CameraIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_camera"] as CameraIconState?,
        )
        "lucide_camera-off" -> CameraOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_camera-off"] as CameraOffIconState?,
        )
        "lucide_captions" -> CaptionsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_captions"] as CaptionsIconState?,
        )
        "lucide_car" -> CarIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_car"] as CarIconState?,
        )
        "lucide_case-lower" -> CaseLowerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_case-lower"] as CaseLowerIconState?,
        )
        "lucide_case-sensitive" -> CaseSensitiveIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_case-sensitive"] as CaseSensitiveIconState?,
        )
        "lucide_case-upper" -> CaseUpperIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_case-upper"] as CaseUpperIconState?,
        )
        "lucide_cast" -> CastIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cast"] as CastIconState?,
        )
        "lucide_cat" -> CatIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cat"] as CatIconState?,
        )
        "lucide_cctv" -> CctvIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cctv"] as CctvIconState?,
        )
        "lucide_cctv-off" -> CctvOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cctv-off"] as CctvOffIconState?,
        )
        "lucide_chart-area" -> ChartAreaIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-area"] as ChartAreaIconState?,
        )
        "lucide_chart-bar-big" -> ChartBarBigIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-bar-big"] as ChartBarBigIconState?,
        )
        "lucide_chart-bar-decreasing" -> ChartBarDecreasingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-bar-decreasing"] as ChartBarDecreasingIconState?,
        )
        "lucide_chart-bar" -> ChartBarIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-bar"] as ChartBarIconState?,
        )
        "lucide_chart-bar-increasing" -> ChartBarIncreasingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-bar-increasing"] as ChartBarIncreasingIconState?,
        )
        "lucide_chart-candlestick" -> ChartCandlestickIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-candlestick"] as ChartCandlestickIconState?,
        )
        "lucide_chart-column-big" -> ChartColumnBigIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-column-big"] as ChartColumnBigIconState?,
        )
        "lucide_chart-column-decreasing" -> ChartColumnDecreasingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-column-decreasing"] as ChartColumnDecreasingIconState?,
        )
        "lucide_chart-column" -> ChartColumnIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-column"] as ChartColumnIconState?,
        )
        "lucide_chart-column-increasing" -> ChartColumnIncreasingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-column-increasing"] as ChartColumnIncreasingIconState?,
        )
        "lucide_chart-gantt" -> ChartGanttIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-gantt"] as ChartGanttIconState?,
        )
        "lucide_chart-line" -> ChartLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-line"] as ChartLineIconState?,
        )
        "lucide_chart-network" -> ChartNetworkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-network"] as ChartNetworkIconState?,
        )
        "lucide_chart-no-axes-column" -> ChartNoAxesColumnIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-no-axes-column"] as ChartNoAxesColumnIconState?,
        )
        "lucide_chart-no-axes-combined" -> ChartNoAxesCombinedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-no-axes-combined"] as ChartNoAxesCombinedIconState?,
        )
        "lucide_chart-pie" -> ChartPieIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-pie"] as ChartPieIconState?,
        )
        "lucide_chart-scatter" -> ChartScatterIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-scatter"] as ChartScatterIconState?,
        )
        "lucide_chart-spline" -> ChartSplineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chart-spline"] as ChartSplineIconState?,
        )
        "lucide_check-check" -> CheckCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_check-check"] as CheckCheckIconState?,
        )
        "lucide_check" -> CheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_check"] as CheckIconState?,
        )
        "lucide_chevron-down" -> ChevronDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevron-down"] as ChevronDownIconState?,
        )
        "lucide_chevron-first" -> ChevronFirstIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevron-first"] as ChevronFirstIconState?,
        )
        "lucide_chevron-last" -> ChevronLastIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevron-last"] as ChevronLastIconState?,
        )
        "lucide_chevron-left" -> ChevronLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevron-left"] as ChevronLeftIconState?,
        )
        "lucide_chevron-right" -> ChevronRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevron-right"] as ChevronRightIconState?,
        )
        "lucide_chevron-up" -> ChevronUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevron-up"] as ChevronUpIconState?,
        )
        "lucide_chevrons-down" -> ChevronsDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevrons-down"] as ChevronsDownIconState?,
        )
        "lucide_chevrons-down-up" -> ChevronsDownUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevrons-down-up"] as ChevronsDownUpIconState?,
        )
        "lucide_chevrons-left" -> ChevronsLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevrons-left"] as ChevronsLeftIconState?,
        )
        "lucide_chevrons-left-right-ellipsis" -> ChevronsLeftRightEllipsisIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevrons-left-right-ellipsis"] as ChevronsLeftRightEllipsisIconState?,
        )
        "lucide_chevrons-left-right" -> ChevronsLeftRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevrons-left-right"] as ChevronsLeftRightIconState?,
        )
        "lucide_chevrons-right" -> ChevronsRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevrons-right"] as ChevronsRightIconState?,
        )
        "lucide_chevrons-right-left" -> ChevronsRightLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevrons-right-left"] as ChevronsRightLeftIconState?,
        )
        "lucide_chevrons-up-down" -> ChevronsUpDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevrons-up-down"] as ChevronsUpDownIconState?,
        )
        "lucide_chevrons-up" -> ChevronsUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chevrons-up"] as ChevronsUpIconState?,
        )
        "lucide_chrome" -> ChromeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_chrome"] as ChromeIconState?,
        )
        "lucide_circle-alert" -> CircleAlertIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-alert"] as CircleAlertIconState?,
        )
        "lucide_circle-arrow-down" -> CircleArrowDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-arrow-down"] as CircleArrowDownIconState?,
        )
        "lucide_circle-arrow-left" -> CircleArrowLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-arrow-left"] as CircleArrowLeftIconState?,
        )
        "lucide_circle-arrow-out-down-left" -> CircleArrowOutDownLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-arrow-out-down-left"] as CircleArrowOutDownLeftIconState?,
        )
        "lucide_circle-arrow-out-down-right" -> CircleArrowOutDownRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-arrow-out-down-right"] as CircleArrowOutDownRightIconState?,
        )
        "lucide_circle-arrow-out-up-left" -> CircleArrowOutUpLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-arrow-out-up-left"] as CircleArrowOutUpLeftIconState?,
        )
        "lucide_circle-arrow-out-up-right" -> CircleArrowOutUpRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-arrow-out-up-right"] as CircleArrowOutUpRightIconState?,
        )
        "lucide_circle-arrow-right" -> CircleArrowRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-arrow-right"] as CircleArrowRightIconState?,
        )
        "lucide_circle-arrow-up" -> CircleArrowUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-arrow-up"] as CircleArrowUpIconState?,
        )
        "lucide_circle-check-big" -> CircleCheckBigIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-check-big"] as CircleCheckBigIconState?,
        )
        "lucide_circle-check" -> CircleCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-check"] as CircleCheckIconState?,
        )
        else -> Unit
    }
}

@Composable
private fun LucideDetailChunk2(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_circle-chevron-down" -> CircleChevronDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-chevron-down"] as CircleChevronDownIconState?,
        )
        "lucide_circle-chevron-left" -> CircleChevronLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-chevron-left"] as CircleChevronLeftIconState?,
        )
        "lucide_circle-chevron-right" -> CircleChevronRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-chevron-right"] as CircleChevronRightIconState?,
        )
        "lucide_circle-chevron-up" -> CircleChevronUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-chevron-up"] as CircleChevronUpIconState?,
        )
        "lucide_circle-dashed" -> CircleDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-dashed"] as CircleDashedIconState?,
        )
        "lucide_circle-dollar-sign" -> CircleDollarSignIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-dollar-sign"] as CircleDollarSignIconState?,
        )
        "lucide_circle-dot" -> CircleDotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-dot"] as CircleDotIconState?,
        )
        "lucide_circle-ellipsis" -> CircleEllipsisIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-ellipsis"] as CircleEllipsisIconState?,
        )
        "lucide_circle-euro" -> CircleEuroIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-euro"] as CircleEuroIconState?,
        )
        "lucide_circle" -> CircleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle"] as CircleIconState?,
        )
        "lucide_circle-minus" -> CircleMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-minus"] as CircleMinusIconState?,
        )
        "lucide_circle-off" -> CircleOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-off"] as CircleOffIconState?,
        )
        "lucide_circle-pause" -> CirclePauseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-pause"] as CirclePauseIconState?,
        )
        "lucide_circle-percent" -> CirclePercentIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-percent"] as CirclePercentIconState?,
        )
        "lucide_circle-play" -> CirclePlayIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-play"] as CirclePlayIconState?,
        )
        "lucide_circle-plus" -> CirclePlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-plus"] as CirclePlusIconState?,
        )
        "lucide_circle-pound-sterling" -> CirclePoundSterlingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-pound-sterling"] as CirclePoundSterlingIconState?,
        )
        "lucide_circle-power" -> CirclePowerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-power"] as CirclePowerIconState?,
        )
        "lucide_circle-question-mark" -> CircleQuestionMarkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-question-mark"] as CircleQuestionMarkIconState?,
        )
        "lucide_circle-slash-2" -> CircleSlash2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-slash-2"] as CircleSlash2IconState?,
        )
        "lucide_circle-slash" -> CircleSlashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-slash"] as CircleSlashIconState?,
        )
        "lucide_circle-star" -> CircleStarIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-star"] as CircleStarIconState?,
        )
        "lucide_circle-stop" -> CircleStopIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-stop"] as CircleStopIconState?,
        )
        "lucide_circle-user" -> CircleUserIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-user"] as CircleUserIconState?,
        )
        "lucide_circle-user-round" -> CircleUserRoundIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-user-round"] as CircleUserRoundIconState?,
        )
        "lucide_circle-x" -> CircleXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_circle-x"] as CircleXIconState?,
        )
        "lucide_clapperboard" -> ClapperboardIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clapperboard"] as ClapperboardIconState?,
        )
        "lucide_clipboard-check" -> ClipboardCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clipboard-check"] as ClipboardCheckIconState?,
        )
        "lucide_clipboard-clock" -> ClipboardClockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clipboard-clock"] as ClipboardClockIconState?,
        )
        "lucide_clipboard" -> ClipboardIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clipboard"] as ClipboardIconState?,
        )
        "lucide_clipboard-list" -> ClipboardListIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clipboard-list"] as ClipboardListIconState?,
        )
        "lucide_clipboard-paste" -> ClipboardPasteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clipboard-paste"] as ClipboardPasteIconState?,
        )
        "lucide_clock-alert" -> ClockAlertIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clock-alert"] as ClockAlertIconState?,
        )
        "lucide_clock-arrow-down" -> ClockArrowDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clock-arrow-down"] as ClockArrowDownIconState?,
        )
        "lucide_clock-arrow-left" -> ClockArrowLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clock-arrow-left"] as ClockArrowLeftIconState?,
        )
        "lucide_clock-arrow-right" -> ClockArrowRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clock-arrow-right"] as ClockArrowRightIconState?,
        )
        "lucide_clock-arrow-up" -> ClockArrowUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clock-arrow-up"] as ClockArrowUpIconState?,
        )
        "lucide_clock-check" -> ClockCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clock-check"] as ClockCheckIconState?,
        )
        "lucide_clock-fading" -> ClockFadingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clock-fading"] as ClockFadingIconState?,
        )
        "lucide_clock" -> ClockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clock"] as ClockIconState?,
        )
        "lucide_clock-plus" -> ClockPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_clock-plus"] as ClockPlusIconState?,
        )
        "lucide_cloud-check" -> CloudCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-check"] as CloudCheckIconState?,
        )
        "lucide_cloud-download" -> CloudDownloadIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-download"] as CloudDownloadIconState?,
        )
        "lucide_cloud-drizzle" -> CloudDrizzleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-drizzle"] as CloudDrizzleIconState?,
        )
        "lucide_cloud-fog" -> CloudFogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-fog"] as CloudFogIconState?,
        )
        "lucide_cloud-hail" -> CloudHailIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-hail"] as CloudHailIconState?,
        )
        "lucide_cloud" -> CloudIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud"] as CloudIconState?,
        )
        "lucide_cloud-lightning" -> CloudLightningIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-lightning"] as CloudLightningIconState?,
        )
        "lucide_cloud-moon" -> CloudMoonIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-moon"] as CloudMoonIconState?,
        )
        "lucide_cloud-moon-rain" -> CloudMoonRainIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-moon-rain"] as CloudMoonRainIconState?,
        )
        "lucide_cloud-off" -> CloudOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-off"] as CloudOffIconState?,
        )
        "lucide_cloud-rain" -> CloudRainIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-rain"] as CloudRainIconState?,
        )
        "lucide_cloud-rain-wind" -> CloudRainWindIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-rain-wind"] as CloudRainWindIconState?,
        )
        "lucide_cloud-snow" -> CloudSnowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-snow"] as CloudSnowIconState?,
        )
        "lucide_cloud-sun" -> CloudSunIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-sun"] as CloudSunIconState?,
        )
        "lucide_cloud-sun-rain" -> CloudSunRainIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-sun-rain"] as CloudSunRainIconState?,
        )
        "lucide_cloud-sync" -> LucideCloudSyncIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-sync"] as LucideCloudSyncIconState?,
        )
        "lucide_cloud-upload" -> CloudUploadIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloud-upload"] as CloudUploadIconState?,
        )
        "lucide_cloudy" -> CloudyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cloudy"] as CloudyIconState?,
        )
        "lucide_code" -> CodeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_code"] as CodeIconState?,
        )
        "lucide_code-xml" -> CodeXmlIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_code-xml"] as CodeXmlIconState?,
        )
        "lucide_coffee" -> CoffeeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_coffee"] as CoffeeIconState?,
        )
        "lucide_coins" -> CoinsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_coins"] as CoinsIconState?,
        )
        "lucide_columns-2" -> Columns2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_columns-2"] as Columns2IconState?,
        )
        "lucide_columns-3" -> Columns3Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_columns-3"] as Columns3IconState?,
        )
        "lucide_columns-4" -> Columns4Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_columns-4"] as Columns4IconState?,
        )
        "lucide_command" -> CommandIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_command"] as CommandIconState?,
        )
        "lucide_compass" -> CompassIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_compass"] as CompassIconState?,
        )
        "lucide_component" -> ComponentIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_component"] as ComponentIconState?,
        )
        "lucide_contact" -> ContactIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_contact"] as ContactIconState?,
        )
        "lucide_contact-round" -> ContactRoundIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_contact-round"] as ContactRoundIconState?,
        )
        "lucide_contrast" -> ContrastIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_contrast"] as ContrastIconState?,
        )
        "lucide_cookie" -> CookieIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cookie"] as CookieIconState?,
        )
        "lucide_copy-check" -> CopyCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_copy-check"] as CopyCheckIconState?,
        )
        "lucide_copy" -> CopyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_copy"] as CopyIconState?,
        )
        "lucide_copy-plus" -> CopyPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_copy-plus"] as CopyPlusIconState?,
        )
        "lucide_corner-down-left" -> CornerDownLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_corner-down-left"] as CornerDownLeftIconState?,
        )
        "lucide_corner-down-right" -> CornerDownRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_corner-down-right"] as CornerDownRightIconState?,
        )
        "lucide_corner-left-down" -> CornerLeftDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_corner-left-down"] as CornerLeftDownIconState?,
        )
        "lucide_corner-left-up" -> CornerLeftUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_corner-left-up"] as CornerLeftUpIconState?,
        )
        "lucide_corner-right-down" -> CornerRightDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_corner-right-down"] as CornerRightDownIconState?,
        )
        "lucide_corner-right-up" -> CornerRightUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_corner-right-up"] as CornerRightUpIconState?,
        )
        "lucide_corner-up-left" -> CornerUpLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_corner-up-left"] as CornerUpLeftIconState?,
        )
        "lucide_corner-up-right" -> CornerUpRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_corner-up-right"] as CornerUpRightIconState?,
        )
        "lucide_cpu" -> CpuIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_cpu"] as CpuIconState?,
        )
        "lucide_credit-card-check" -> CreditCardCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_credit-card-check"] as CreditCardCheckIconState?,
        )
        "lucide_credit-card" -> CreditCardIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_credit-card"] as CreditCardIconState?,
        )
        "lucide_credit-card-minus" -> CreditCardMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_credit-card-minus"] as CreditCardMinusIconState?,
        )
        "lucide_credit-card-plus" -> CreditCardPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_credit-card-plus"] as CreditCardPlusIconState?,
        )
        "lucide_credit-card-reader" -> CreditCardReaderIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_credit-card-reader"] as CreditCardReaderIconState?,
        )
        "lucide_credit-card-x" -> CreditCardXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_credit-card-x"] as CreditCardXIconState?,
        )
        "lucide_crop" -> CropIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_crop"] as CropIconState?,
        )
        "lucide_crown" -> CrownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_crown"] as CrownIconState?,
        )
        "lucide_database-arrow-down" -> DatabaseArrowDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database-arrow-down"] as DatabaseArrowDownIconState?,
        )
        "lucide_database-arrow-up" -> DatabaseArrowUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database-arrow-up"] as DatabaseArrowUpIconState?,
        )
        "lucide_database-backup" -> DatabaseBackupIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database-backup"] as DatabaseBackupIconState?,
        )
        "lucide_database-check" -> DatabaseCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database-check"] as DatabaseCheckIconState?,
        )
        "lucide_database" -> DatabaseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database"] as DatabaseIconState?,
        )
        "lucide_database-minus" -> DatabaseMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database-minus"] as DatabaseMinusIconState?,
        )
        "lucide_database-plus" -> DatabasePlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database-plus"] as DatabasePlusIconState?,
        )
        else -> Unit
    }
}

@Composable
private fun LucideDetailChunk3(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_database-search" -> DatabaseSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database-search"] as DatabaseSearchIconState?,
        )
        "lucide_database-x" -> DatabaseXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database-x"] as DatabaseXIconState?,
        )
        "lucide_database-zap" -> DatabaseZapIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_database-zap"] as DatabaseZapIconState?,
        )
        "lucide_delete" -> DeleteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_delete"] as DeleteIconState?,
        )
        "lucide_diamond" -> DiamondIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_diamond"] as DiamondIconState?,
        )
        "lucide_diamond-minus" -> DiamondMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_diamond-minus"] as DiamondMinusIconState?,
        )
        "lucide_diamond-percent" -> DiamondPercentIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_diamond-percent"] as DiamondPercentIconState?,
        )
        "lucide_diamond-plus" -> DiamondPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_diamond-plus"] as DiamondPlusIconState?,
        )
        "lucide_dice-5" -> Dice5Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_dice-5"] as Dice5IconState?,
        )
        "lucide_diff" -> DiffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_diff"] as DiffIconState?,
        )
        "lucide_disc-2" -> Disc2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_disc-2"] as Disc2IconState?,
        )
        "lucide_disc-3" -> Disc3Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_disc-3"] as Disc3IconState?,
        )
        "lucide_disc-album" -> DiscAlbumIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_disc-album"] as DiscAlbumIconState?,
        )
        "lucide_disc" -> DiscIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_disc"] as DiscIconState?,
        )
        "lucide_dog" -> DogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_dog"] as DogIconState?,
        )
        "lucide_dollar-sign" -> DollarSignIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_dollar-sign"] as DollarSignIconState?,
        )
        "lucide_door-open" -> DoorOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_door-open"] as DoorOpenIconState?,
        )
        "lucide_download" -> DownloadIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_download"] as DownloadIconState?,
        )
        "lucide_droplet" -> DropletIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_droplet"] as DropletIconState?,
        )
        "lucide_droplets" -> DropletsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_droplets"] as DropletsIconState?,
        )
        "lucide_drum" -> DrumIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_drum"] as DrumIconState?,
        )
        "lucide_dumbbell" -> DumbbellIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_dumbbell"] as DumbbellIconState?,
        )
        "lucide_ellipsis" -> EllipsisIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ellipsis"] as EllipsisIconState?,
        )
        "lucide_ellipsis-vertical" -> EllipsisVerticalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ellipsis-vertical"] as EllipsisVerticalIconState?,
        )
        "lucide_eraser" -> EraserIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_eraser"] as EraserIconState?,
        )
        "lucide_euro" -> EuroIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_euro"] as EuroIconState?,
        )
        "lucide_expand" -> ExpandIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_expand"] as ExpandIconState?,
        )
        "lucide_external-link" -> ExternalLinkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_external-link"] as ExternalLinkIconState?,
        )
        "lucide_eye-closed" -> EyeClosedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_eye-closed"] as EyeClosedIconState?,
        )
        "lucide_eye" -> EyeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_eye"] as EyeIconState?,
        )
        "lucide_eye-off" -> EyeOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_eye-off"] as EyeOffIconState?,
        )
        "lucide_facebook" -> FacebookIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_facebook"] as FacebookIconState?,
        )
        "lucide_factory" -> FactoryIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_factory"] as FactoryIconState?,
        )
        "lucide_fast-forward" -> FastForwardIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_fast-forward"] as FastForwardIconState?,
        )
        "lucide_feather" -> FeatherIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_feather"] as FeatherIconState?,
        )
        "lucide_figma" -> FigmaIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_figma"] as FigmaIconState?,
        )
        "lucide_file-archive" -> FileArchiveIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-archive"] as FileArchiveIconState?,
        )
        "lucide_file-check" -> FileCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-check"] as FileCheckIconState?,
        )
        "lucide_file-clock" -> FileClockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-clock"] as FileClockIconState?,
        )
        "lucide_file-code" -> FileCodeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-code"] as FileCodeIconState?,
        )
        "lucide_file-down" -> FileDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-down"] as FileDownIconState?,
        )
        "lucide_file-heart" -> FileHeartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-heart"] as FileHeartIconState?,
        )
        "lucide_file" -> FileIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file"] as FileIconState?,
        )
        "lucide_file-image" -> FileImageIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-image"] as FileImageIconState?,
        )
        "lucide_file-key" -> FileKeyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-key"] as FileKeyIconState?,
        )
        "lucide_file-lock" -> FileLockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-lock"] as FileLockIconState?,
        )
        "lucide_file-minus" -> FileMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-minus"] as FileMinusIconState?,
        )
        "lucide_file-music" -> FileMusicIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-music"] as FileMusicIconState?,
        )
        "lucide_file-pen" -> FilePenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-pen"] as FilePenIconState?,
        )
        "lucide_file-plus" -> FilePlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-plus"] as FilePlusIconState?,
        )
        "lucide_file-scan" -> FileScanIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-scan"] as FileScanIconState?,
        )
        "lucide_file-search" -> FileSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-search"] as FileSearchIconState?,
        )
        "lucide_file-spreadsheet" -> FileSpreadsheetIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-spreadsheet"] as FileSpreadsheetIconState?,
        )
        "lucide_file-terminal" -> FileTerminalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-terminal"] as FileTerminalIconState?,
        )
        "lucide_file-text" -> FileTextIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-text"] as FileTextIconState?,
        )
        "lucide_file-type" -> FileTypeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-type"] as FileTypeIconState?,
        )
        "lucide_file-up" -> FileUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-up"] as FileUpIconState?,
        )
        "lucide_file-x" -> FileXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_file-x"] as FileXIconState?,
        )
        "lucide_files" -> FilesIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_files"] as FilesIconState?,
        )
        "lucide_film" -> FilmIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_film"] as FilmIconState?,
        )
        "lucide_filter" -> FilterIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_filter"] as FilterIconState?,
        )
        "lucide_fingerprint-pattern" -> FingerprintPatternIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_fingerprint-pattern"] as FingerprintPatternIconState?,
        )
        "lucide_fish" -> FishIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_fish"] as FishIconState?,
        )
        "lucide_flag" -> FlagIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_flag"] as FlagIconState?,
        )
        "lucide_flag-off" -> FlagOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_flag-off"] as FlagOffIconState?,
        )
        "lucide_flag-triangle-right" -> FlagTriangleRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_flag-triangle-right"] as FlagTriangleRightIconState?,
        )
        "lucide_flame" -> LucideFlameIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_flame"] as LucideFlameIconState?,
        )
        "lucide_flask-conical" -> FlaskConicalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_flask-conical"] as FlaskConicalIconState?,
        )
        "lucide_fold-vertical" -> FoldVerticalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_fold-vertical"] as FoldVerticalIconState?,
        )
        "lucide_folder-check" -> FolderCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-check"] as FolderCheckIconState?,
        )
        "lucide_folder-closed" -> FolderClosedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-closed"] as FolderClosedIconState?,
        )
        "lucide_folder-dot" -> FolderDotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-dot"] as FolderDotIconState?,
        )
        "lucide_folder-heart" -> FolderHeartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-heart"] as FolderHeartIconState?,
        )
        "lucide_folder" -> FolderIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder"] as FolderIconState?,
        )
        "lucide_folder-key" -> FolderKeyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-key"] as FolderKeyIconState?,
        )
        "lucide_folder-lock" -> FolderLockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-lock"] as FolderLockIconState?,
        )
        "lucide_folder-minus" -> FolderMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-minus"] as FolderMinusIconState?,
        )
        "lucide_folder-open" -> FolderOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-open"] as FolderOpenIconState?,
        )
        "lucide_folder-plus" -> FolderPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-plus"] as FolderPlusIconState?,
        )
        "lucide_folder-search" -> FolderSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-search"] as FolderSearchIconState?,
        )
        "lucide_folder-tree" -> FolderTreeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-tree"] as FolderTreeIconState?,
        )
        "lucide_folder-x" -> FolderXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_folder-x"] as FolderXIconState?,
        )
        "lucide_footprints" -> FootprintsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_footprints"] as FootprintsIconState?,
        )
        "lucide_framer" -> FramerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_framer"] as FramerIconState?,
        )
        "lucide_fullscreen" -> FullscreenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_fullscreen"] as FullscreenIconState?,
        )
        "lucide_funnel-x" -> FunnelXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_funnel-x"] as FunnelXIconState?,
        )
        "lucide_gallery-horizontal" -> GalleryHorizontalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_gallery-horizontal"] as GalleryHorizontalIconState?,
        )
        "lucide_gamepad" -> GamepadIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_gamepad"] as GamepadIconState?,
        )
        "lucide_gauge" -> GaugeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_gauge"] as GaugeIconState?,
        )
        "lucide_gem" -> GemIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_gem"] as GemIconState?,
        )
        "lucide_georgian-lari" -> GeorgianLariIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_georgian-lari"] as GeorgianLariIconState?,
        )
        "lucide_ghost" -> GhostIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ghost"] as GhostIconState?,
        )
        "lucide_gift" -> GiftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_gift"] as GiftIconState?,
        )
        "lucide_git-branch" -> GitBranchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-branch"] as GitBranchIconState?,
        )
        "lucide_git-branch-minus" -> GitBranchMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-branch-minus"] as GitBranchMinusIconState?,
        )
        "lucide_git-branch-plus" -> GitBranchPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-branch-plus"] as GitBranchPlusIconState?,
        )
        "lucide_git-commit-horizontal" -> GitCommitHorizontalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-commit-horizontal"] as GitCommitHorizontalIconState?,
        )
        "lucide_git-commit-vertical" -> GitCommitVerticalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-commit-vertical"] as GitCommitVerticalIconState?,
        )
        "lucide_git-compare-arrows" -> GitCompareArrowsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-compare-arrows"] as GitCompareArrowsIconState?,
        )
        "lucide_git-compare" -> GitCompareIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-compare"] as GitCompareIconState?,
        )
        else -> Unit
    }
}

@Composable
private fun LucideDetailChunk4(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_git-fork" -> GitForkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-fork"] as GitForkIconState?,
        )
        "lucide_git-graph" -> GitGraphIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-graph"] as GitGraphIconState?,
        )
        "lucide_git-merge-conflict" -> GitMergeConflictIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-merge-conflict"] as GitMergeConflictIconState?,
        )
        "lucide_git-merge" -> GitMergeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-merge"] as GitMergeIconState?,
        )
        "lucide_git-pull-request-arrow" -> GitPullRequestArrowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-pull-request-arrow"] as GitPullRequestArrowIconState?,
        )
        "lucide_git-pull-request-closed" -> GitPullRequestClosedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-pull-request-closed"] as GitPullRequestClosedIconState?,
        )
        "lucide_git-pull-request-create-arrow" -> GitPullRequestCreateArrowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-pull-request-create-arrow"] as GitPullRequestCreateArrowIconState?,
        )
        "lucide_git-pull-request-create" -> GitPullRequestCreateIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-pull-request-create"] as GitPullRequestCreateIconState?,
        )
        "lucide_git-pull-request-draft" -> GitPullRequestDraftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-pull-request-draft"] as GitPullRequestDraftIconState?,
        )
        "lucide_git-pull-request" -> GitPullRequestIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_git-pull-request"] as GitPullRequestIconState?,
        )
        "lucide_github" -> GithubIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_github"] as GithubIconState?,
        )
        "lucide_gitlab" -> GitlabIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_gitlab"] as GitlabIconState?,
        )
        "lucide_globe" -> GlobeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_globe"] as GlobeIconState?,
        )
        "lucide_globe-lock" -> GlobeLockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_globe-lock"] as GlobeLockIconState?,
        )
        "lucide_graduation-cap" -> GraduationCapIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_graduation-cap"] as GraduationCapIconState?,
        )
        "lucide_grid-2x2-check" -> Grid2x2CheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_grid-2x2-check"] as Grid2x2CheckIconState?,
        )
        "lucide_grid-2x2" -> Grid2x2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_grid-2x2"] as Grid2x2IconState?,
        )
        "lucide_grid-2x2-plus" -> Grid2x2PlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_grid-2x2-plus"] as Grid2x2PlusIconState?,
        )
        "lucide_grid-2x2-x" -> Grid2x2XIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_grid-2x2-x"] as Grid2x2XIconState?,
        )
        "lucide_grid-3x2" -> Grid3x2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_grid-3x2"] as Grid3x2IconState?,
        )
        "lucide_grid-3x3" -> Grid3x3Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_grid-3x3"] as Grid3x3IconState?,
        )
        "lucide_grip-vertical" -> GripVerticalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_grip-vertical"] as GripVerticalIconState?,
        )
        "lucide_guitar" -> GuitarIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_guitar"] as GuitarIconState?,
        )
        "lucide_hammer" -> HammerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hammer"] as HammerIconState?,
        )
        "lucide_hand-coins" -> HandCoinsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hand-coins"] as HandCoinsIconState?,
        )
        "lucide_hand-heart" -> HandHeartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hand-heart"] as HandHeartIconState?,
        )
        "lucide_hand" -> HandIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hand"] as HandIconState?,
        )
        "lucide_handshake" -> HandshakeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_handshake"] as HandshakeIconState?,
        )
        "lucide_hard-drive-download" -> HardDriveDownloadIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hard-drive-download"] as HardDriveDownloadIconState?,
        )
        "lucide_hard-drive" -> HardDriveIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hard-drive"] as HardDriveIconState?,
        )
        "lucide_hard-drive-upload" -> HardDriveUploadIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hard-drive-upload"] as HardDriveUploadIconState?,
        )
        "lucide_hash" -> HashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hash"] as HashIconState?,
        )
        "lucide_haze" -> HazeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_haze"] as HazeIconState?,
        )
        "lucide_heading-1" -> Heading1Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heading-1"] as Heading1IconState?,
        )
        "lucide_heading-2" -> Heading2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heading-2"] as Heading2IconState?,
        )
        "lucide_heading-3" -> Heading3Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heading-3"] as Heading3IconState?,
        )
        "lucide_heading-4" -> Heading4Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heading-4"] as Heading4IconState?,
        )
        "lucide_heading-5" -> Heading5Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heading-5"] as Heading5IconState?,
        )
        "lucide_heading-6" -> Heading6Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heading-6"] as Heading6IconState?,
        )
        "lucide_heading" -> HeadingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heading"] as HeadingIconState?,
        )
        "lucide_headphone-off" -> HeadphoneOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_headphone-off"] as HeadphoneOffIconState?,
        )
        "lucide_headphones" -> HeadphonesIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_headphones"] as HeadphonesIconState?,
        )
        "lucide_headset" -> HeadsetIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_headset"] as HeadsetIconState?,
        )
        "lucide_heart-crack" -> HeartCrackIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heart-crack"] as HeartCrackIconState?,
        )
        "lucide_heart-handshake" -> HeartHandshakeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heart-handshake"] as HeartHandshakeIconState?,
        )
        "lucide_heart" -> HeartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heart"] as HeartIconState?,
        )
        "lucide_heart-minus" -> HeartMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heart-minus"] as HeartMinusIconState?,
        )
        "lucide_heart-off" -> HeartOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heart-off"] as HeartOffIconState?,
        )
        "lucide_heart-plus" -> HeartPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heart-plus"] as HeartPlusIconState?,
        )
        "lucide_heart-pulse" -> HeartPulseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heart-pulse"] as HeartPulseIconState?,
        )
        "lucide_heart-x" -> HeartXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_heart-x"] as HeartXIconState?,
        )
        "lucide_hexagon" -> HexagonIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hexagon"] as HexagonIconState?,
        )
        "lucide_highlighter" -> HighlighterIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_highlighter"] as HighlighterIconState?,
        )
        "lucide_history" -> HistoryIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_history"] as HistoryIconState?,
        )
        "lucide_hospital" -> HospitalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hospital"] as HospitalIconState?,
        )
        "lucide_hotel" -> HotelIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hotel"] as HotelIconState?,
        )
        "lucide_hourglass" -> HourglassIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_hourglass"] as HourglassIconState?,
        )
        "lucide_house-cog" -> HouseCogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_house-cog"] as HouseCogIconState?,
        )
        "lucide_house-heart" -> HouseHeartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_house-heart"] as HouseHeartIconState?,
        )
        "lucide_house" -> HouseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_house"] as HouseIconState?,
        )
        "lucide_house-plug" -> HousePlugIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_house-plug"] as HousePlugIconState?,
        )
        "lucide_house-plus" -> HousePlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_house-plus"] as HousePlusIconState?,
        )
        "lucide_house-wifi" -> HouseWifiIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_house-wifi"] as HouseWifiIconState?,
        )
        "lucide_image" -> ImageIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_image"] as ImageIconState?,
        )
        "lucide_image-off" -> ImageOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_image-off"] as ImageOffIconState?,
        )
        "lucide_image-plus" -> ImagePlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_image-plus"] as ImagePlusIconState?,
        )
        "lucide_inbox" -> InboxIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_inbox"] as InboxIconState?,
        )
        "lucide_indent-decrease" -> IndentDecreaseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_indent-decrease"] as IndentDecreaseIconState?,
        )
        "lucide_indent-increase" -> IndentIncreaseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_indent-increase"] as IndentIncreaseIconState?,
        )
        "lucide_indian-rupee" -> IndianRupeeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_indian-rupee"] as IndianRupeeIconState?,
        )
        "lucide_infinity" -> InfinityIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_infinity"] as InfinityIconState?,
        )
        "lucide_info" -> InfoIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_info"] as InfoIconState?,
        )
        "lucide_instagram" -> InstagramIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_instagram"] as InstagramIconState?,
        )
        "lucide_italic" -> ItalicIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_italic"] as ItalicIconState?,
        )
        "lucide_japanese-yen" -> JapaneseYenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_japanese-yen"] as JapaneseYenIconState?,
        )
        "lucide_kanban" -> KanbanIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_kanban"] as KanbanIconState?,
        )
        "lucide_key" -> KeyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_key"] as KeyIconState?,
        )
        "lucide_key-round" -> KeyRoundIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_key-round"] as KeyRoundIconState?,
        )
        "lucide_key-square" -> KeySquareIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_key-square"] as KeySquareIconState?,
        )
        "lucide_keyboard" -> KeyboardIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_keyboard"] as KeyboardIconState?,
        )
        "lucide_landmark" -> LandmarkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_landmark"] as LandmarkIconState?,
        )
        "lucide_languages" -> LanguagesIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_languages"] as LanguagesIconState?,
        )
        "lucide_laptop" -> LaptopIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_laptop"] as LaptopIconState?,
        )
        "lucide_laptop-minimal-check" -> LaptopMinimalCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_laptop-minimal-check"] as LaptopMinimalCheckIconState?,
        )
        "lucide_laptop-minimal" -> LaptopMinimalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_laptop-minimal"] as LaptopMinimalIconState?,
        )
        "lucide_layers" -> LayersIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_layers"] as LayersIconState?,
        )
        "lucide_layout-dashboard" -> LayoutDashboardIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_layout-dashboard"] as LayoutDashboardIconState?,
        )
        "lucide_layout-grid" -> LayoutGridIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_layout-grid"] as LayoutGridIconState?,
        )
        "lucide_layout-list" -> LayoutListIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_layout-list"] as LayoutListIconState?,
        )
        "lucide_layout-panel-left" -> LayoutPanelLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_layout-panel-left"] as LayoutPanelLeftIconState?,
        )
        "lucide_layout-panel-top" -> LayoutPanelTopIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_layout-panel-top"] as LayoutPanelTopIconState?,
        )
        "lucide_layout-template" -> LayoutTemplateIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_layout-template"] as LayoutTemplateIconState?,
        )
        "lucide_leaf" -> LeafIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_leaf"] as LeafIconState?,
        )
        "lucide_life-buoy" -> LifeBuoyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_life-buoy"] as LifeBuoyIconState?,
        )
        "lucide_lightbulb" -> LightbulbIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_lightbulb"] as LightbulbIconState?,
        )
        "lucide_link" -> LinkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_link"] as LinkIconState?,
        )
        "lucide_linkedin" -> LinkedinIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_linkedin"] as LinkedinIconState?,
        )
        "lucide_list-check" -> ListCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-check"] as ListCheckIconState?,
        )
        "lucide_list-checks" -> ListChecksIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-checks"] as ListChecksIconState?,
        )
        "lucide_list-chevrons-down-up" -> ListChevronsDownUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-chevrons-down-up"] as ListChevronsDownUpIconState?,
        )
        else -> Unit
    }
}

@Composable
private fun LucideDetailChunk5(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_list-chevrons-up-down" -> ListChevronsUpDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-chevrons-up-down"] as ListChevronsUpDownIconState?,
        )
        "lucide_list-collapse" -> ListCollapseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-collapse"] as ListCollapseIconState?,
        )
        "lucide_list-end" -> ListEndIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-end"] as ListEndIconState?,
        )
        "lucide_list-filter" -> ListFilterIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-filter"] as ListFilterIconState?,
        )
        "lucide_list-filter-plus" -> ListFilterPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-filter-plus"] as ListFilterPlusIconState?,
        )
        "lucide_list" -> ListIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list"] as ListIconState?,
        )
        "lucide_list-minus" -> ListMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-minus"] as ListMinusIconState?,
        )
        "lucide_list-ordered" -> ListOrderedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-ordered"] as ListOrderedIconState?,
        )
        "lucide_list-plus" -> ListPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-plus"] as ListPlusIconState?,
        )
        "lucide_list-start" -> ListStartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-start"] as ListStartIconState?,
        )
        "lucide_list-todo" -> ListTodoIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-todo"] as ListTodoIconState?,
        )
        "lucide_list-tree" -> ListTreeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-tree"] as ListTreeIconState?,
        )
        "lucide_list-x" -> ListXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_list-x"] as ListXIconState?,
        )
        "lucide_loader-circle" -> LoaderCircleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_loader-circle"] as LoaderCircleIconState?,
        )
        "lucide_loader" -> LoaderIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_loader"] as LoaderIconState?,
        )
        "lucide_locate-fixed" -> LocateFixedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_locate-fixed"] as LocateFixedIconState?,
        )
        "lucide_locate" -> LocateIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_locate"] as LocateIconState?,
        )
        "lucide_locate-off" -> LocateOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_locate-off"] as LocateOffIconState?,
        )
        "lucide_lock" -> LockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_lock"] as LockIconState?,
        )
        "lucide_lock-keyhole" -> LockKeyholeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_lock-keyhole"] as LockKeyholeIconState?,
        )
        "lucide_lock-keyhole-open" -> LockKeyholeOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_lock-keyhole-open"] as LockKeyholeOpenIconState?,
        )
        "lucide_lock-open" -> LockOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_lock-open"] as LockOpenIconState?,
        )
        "lucide_log-in" -> LogInIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_log-in"] as LogInIconState?,
        )
        "lucide_log-out" -> LogOutIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_log-out"] as LogOutIconState?,
        )
        "lucide_magnet" -> MagnetIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_magnet"] as MagnetIconState?,
        )
        "lucide_mail-check" -> MailCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mail-check"] as MailCheckIconState?,
        )
        "lucide_mail-clock" -> MailClockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mail-clock"] as MailClockIconState?,
        )
        "lucide_mail" -> MailIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mail"] as MailIconState?,
        )
        "lucide_mail-minus" -> MailMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mail-minus"] as MailMinusIconState?,
        )
        "lucide_mail-open" -> MailOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mail-open"] as MailOpenIconState?,
        )
        "lucide_mail-plus" -> MailPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mail-plus"] as MailPlusIconState?,
        )
        "lucide_mail-search" -> MailSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mail-search"] as MailSearchIconState?,
        )
        "lucide_mail-warning" -> MailWarningIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mail-warning"] as MailWarningIconState?,
        )
        "lucide_mail-x" -> MailXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mail-x"] as MailXIconState?,
        )
        "lucide_mailbox" -> MailboxIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mailbox"] as MailboxIconState?,
        )
        "lucide_mails" -> MailsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mails"] as MailsIconState?,
        )
        "lucide_map" -> MapIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map"] as MapIconState?,
        )
        "lucide_map-minus" -> MapMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-minus"] as MapMinusIconState?,
        )
        "lucide_map-pin-check" -> MapPinCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-check"] as MapPinCheckIconState?,
        )
        "lucide_map-pin-check-inside" -> MapPinCheckInsideIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-check-inside"] as MapPinCheckInsideIconState?,
        )
        "lucide_map-pin-house" -> MapPinHouseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-house"] as MapPinHouseIconState?,
        )
        "lucide_map-pin" -> MapPinIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin"] as MapPinIconState?,
        )
        "lucide_map-pin-minus" -> MapPinMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-minus"] as MapPinMinusIconState?,
        )
        "lucide_map-pin-minus-inside" -> MapPinMinusInsideIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-minus-inside"] as MapPinMinusInsideIconState?,
        )
        "lucide_map-pin-off" -> MapPinOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-off"] as MapPinOffIconState?,
        )
        "lucide_map-pin-pen" -> MapPinPenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-pen"] as MapPinPenIconState?,
        )
        "lucide_map-pin-plus" -> MapPinPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-plus"] as MapPinPlusIconState?,
        )
        "lucide_map-pin-plus-inside" -> MapPinPlusInsideIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-plus-inside"] as MapPinPlusInsideIconState?,
        )
        "lucide_map-pin-search" -> MapPinSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-search"] as MapPinSearchIconState?,
        )
        "lucide_map-pin-x" -> MapPinXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-x"] as MapPinXIconState?,
        )
        "lucide_map-pin-x-inside" -> MapPinXInsideIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pin-x-inside"] as MapPinXInsideIconState?,
        )
        "lucide_map-pinned" -> MapPinnedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-pinned"] as MapPinnedIconState?,
        )
        "lucide_map-plus" -> MapPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_map-plus"] as MapPlusIconState?,
        )
        "lucide_mars" -> MarsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mars"] as MarsIconState?,
        )
        "lucide_maximize-2" -> Maximize2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_maximize-2"] as Maximize2IconState?,
        )
        "lucide_maximize" -> MaximizeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_maximize"] as MaximizeIconState?,
        )
        "lucide_medal" -> MedalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_medal"] as MedalIconState?,
        )
        "lucide_megaphone" -> MegaphoneIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_megaphone"] as MegaphoneIconState?,
        )
        "lucide_memory-stick" -> MemoryStickIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_memory-stick"] as MemoryStickIconState?,
        )
        "lucide_menu" -> MenuIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_menu"] as MenuIconState?,
        )
        "lucide_message-circle-check" -> MessageCircleCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-check"] as MessageCircleCheckIconState?,
        )
        "lucide_message-circle-code" -> MessageCircleCodeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-code"] as MessageCircleCodeIconState?,
        )
        "lucide_message-circle-dashed-check" -> MessageCircleDashedCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-dashed-check"] as MessageCircleDashedCheckIconState?,
        )
        "lucide_message-circle-dashed" -> MessageCircleDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-dashed"] as MessageCircleDashedIconState?,
        )
        "lucide_message-circle-heart" -> MessageCircleHeartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-heart"] as MessageCircleHeartIconState?,
        )
        "lucide_message-circle" -> MessageCircleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle"] as MessageCircleIconState?,
        )
        "lucide_message-circle-more" -> MessageCircleMoreIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-more"] as MessageCircleMoreIconState?,
        )
        "lucide_message-circle-off" -> MessageCircleOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-off"] as MessageCircleOffIconState?,
        )
        "lucide_message-circle-plus" -> MessageCirclePlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-plus"] as MessageCirclePlusIconState?,
        )
        "lucide_message-circle-question-mark" -> MessageCircleQuestionMarkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-question-mark"] as MessageCircleQuestionMarkIconState?,
        )
        "lucide_message-circle-reply" -> MessageCircleReplyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-reply"] as MessageCircleReplyIconState?,
        )
        "lucide_message-circle-warning" -> MessageCircleWarningIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-warning"] as MessageCircleWarningIconState?,
        )
        "lucide_message-circle-x" -> MessageCircleXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-circle-x"] as MessageCircleXIconState?,
        )
        "lucide_message-square-check" -> MessageSquareCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-check"] as MessageSquareCheckIconState?,
        )
        "lucide_message-square-code" -> MessageSquareCodeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-code"] as MessageSquareCodeIconState?,
        )
        "lucide_message-square-dashed" -> MessageSquareDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-dashed"] as MessageSquareDashedIconState?,
        )
        "lucide_message-square-diff" -> MessageSquareDiffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-diff"] as MessageSquareDiffIconState?,
        )
        "lucide_message-square-dot" -> MessageSquareDotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-dot"] as MessageSquareDotIconState?,
        )
        "lucide_message-square-heart" -> MessageSquareHeartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-heart"] as MessageSquareHeartIconState?,
        )
        "lucide_message-square" -> MessageSquareIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square"] as MessageSquareIconState?,
        )
        "lucide_message-square-lock" -> MessageSquareLockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-lock"] as MessageSquareLockIconState?,
        )
        "lucide_message-square-more" -> MessageSquareMoreIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-more"] as MessageSquareMoreIconState?,
        )
        "lucide_message-square-off" -> MessageSquareOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-off"] as MessageSquareOffIconState?,
        )
        "lucide_message-square-plus" -> MessageSquarePlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-plus"] as MessageSquarePlusIconState?,
        )
        "lucide_message-square-quote" -> MessageSquareQuoteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-quote"] as MessageSquareQuoteIconState?,
        )
        "lucide_message-square-reply" -> MessageSquareReplyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-reply"] as MessageSquareReplyIconState?,
        )
        "lucide_message-square-share" -> MessageSquareShareIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-share"] as MessageSquareShareIconState?,
        )
        "lucide_message-square-text" -> MessageSquareTextIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-text"] as MessageSquareTextIconState?,
        )
        "lucide_message-square-warning" -> MessageSquareWarningIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-warning"] as MessageSquareWarningIconState?,
        )
        "lucide_message-square-x" -> MessageSquareXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_message-square-x"] as MessageSquareXIconState?,
        )
        "lucide_messages-circle" -> MessagesCircleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_messages-circle"] as MessagesCircleIconState?,
        )
        "lucide_messages-square" -> MessagesSquareIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_messages-square"] as MessagesSquareIconState?,
        )
        "lucide_mic-audio-lines" -> MicAudioLinesIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mic-audio-lines"] as MicAudioLinesIconState?,
        )
        "lucide_mic" -> MicIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mic"] as MicIconState?,
        )
        "lucide_mic-off" -> MicOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mic-off"] as MicOffIconState?,
        )
        "lucide_mic-signal" -> MicSignalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mic-signal"] as MicSignalIconState?,
        )
        "lucide_mic-vocal" -> MicVocalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mic-vocal"] as MicVocalIconState?,
        )
        "lucide_microchip" -> MicrochipIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_microchip"] as MicrochipIconState?,
        )
        "lucide_milestone" -> MilestoneIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_milestone"] as MilestoneIconState?,
        )
        "lucide_minimize-2" -> Minimize2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_minimize-2"] as Minimize2IconState?,
        )
        else -> Unit
    }
}

@Composable
private fun LucideDetailChunk6(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_minimize" -> MinimizeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_minimize"] as MinimizeIconState?,
        )
        "lucide_minus" -> MinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_minus"] as MinusIconState?,
        )
        "lucide_monitor-check" -> MonitorCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-check"] as MonitorCheckIconState?,
        )
        "lucide_monitor-cloud" -> MonitorCloudIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-cloud"] as MonitorCloudIconState?,
        )
        "lucide_monitor-cog" -> MonitorCogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-cog"] as MonitorCogIconState?,
        )
        "lucide_monitor-dot" -> MonitorDotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-dot"] as MonitorDotIconState?,
        )
        "lucide_monitor-down" -> MonitorDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-down"] as MonitorDownIconState?,
        )
        "lucide_monitor" -> MonitorIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor"] as MonitorIconState?,
        )
        "lucide_monitor-off" -> MonitorOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-off"] as MonitorOffIconState?,
        )
        "lucide_monitor-pause" -> MonitorPauseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-pause"] as MonitorPauseIconState?,
        )
        "lucide_monitor-pc" -> MonitorPcIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-pc"] as MonitorPcIconState?,
        )
        "lucide_monitor-play" -> MonitorPlayIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-play"] as MonitorPlayIconState?,
        )
        "lucide_monitor-smartphone" -> MonitorSmartphoneIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-smartphone"] as MonitorSmartphoneIconState?,
        )
        "lucide_monitor-speaker" -> MonitorSpeakerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-speaker"] as MonitorSpeakerIconState?,
        )
        "lucide_monitor-stop" -> MonitorStopIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-stop"] as MonitorStopIconState?,
        )
        "lucide_monitor-up" -> MonitorUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-up"] as MonitorUpIconState?,
        )
        "lucide_monitor-x" -> MonitorXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_monitor-x"] as MonitorXIconState?,
        )
        "lucide_moon" -> MoonIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_moon"] as MoonIconState?,
        )
        "lucide_moon-star" -> MoonStarIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_moon-star"] as MoonStarIconState?,
        )
        "lucide_mountain" -> MountainIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mountain"] as MountainIconState?,
        )
        "lucide_mouse" -> MouseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mouse"] as MouseIconState?,
        )
        "lucide_mouse-pointer-click" -> MousePointerClickIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mouse-pointer-click"] as MousePointerClickIconState?,
        )
        "lucide_mouse-pointer" -> MousePointerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_mouse-pointer"] as MousePointerIconState?,
        )
        "lucide_move-diagonal-2" -> MoveDiagonal2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-diagonal-2"] as MoveDiagonal2IconState?,
        )
        "lucide_move-diagonal" -> MoveDiagonalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-diagonal"] as MoveDiagonalIconState?,
        )
        "lucide_move-down" -> MoveDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-down"] as MoveDownIconState?,
        )
        "lucide_move-down-left" -> MoveDownLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-down-left"] as MoveDownLeftIconState?,
        )
        "lucide_move-down-right" -> MoveDownRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-down-right"] as MoveDownRightIconState?,
        )
        "lucide_move-horizontal" -> MoveHorizontalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-horizontal"] as MoveHorizontalIconState?,
        )
        "lucide_move" -> MoveIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move"] as MoveIconState?,
        )
        "lucide_move-left" -> MoveLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-left"] as MoveLeftIconState?,
        )
        "lucide_move-right" -> MoveRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-right"] as MoveRightIconState?,
        )
        "lucide_move-up" -> MoveUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-up"] as MoveUpIconState?,
        )
        "lucide_move-up-left" -> MoveUpLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-up-left"] as MoveUpLeftIconState?,
        )
        "lucide_move-up-right" -> MoveUpRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-up-right"] as MoveUpRightIconState?,
        )
        "lucide_move-vertical" -> MoveVerticalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_move-vertical"] as MoveVerticalIconState?,
        )
        "lucide_music-2" -> Music2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_music-2"] as Music2IconState?,
        )
        "lucide_music-3" -> Music3Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_music-3"] as Music3IconState?,
        )
        "lucide_music-4" -> Music4Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_music-4"] as Music4IconState?,
        )
        "lucide_music" -> MusicIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_music"] as MusicIconState?,
        )
        "lucide_navigation-2" -> Navigation2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_navigation-2"] as Navigation2IconState?,
        )
        "lucide_navigation" -> NavigationIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_navigation"] as NavigationIconState?,
        )
        "lucide_network" -> NetworkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_network"] as NetworkIconState?,
        )
        "lucide_nfc" -> NfcIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_nfc"] as NfcIconState?,
        )
        "lucide_notebook-pen" -> NotebookPenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_notebook-pen"] as NotebookPenIconState?,
        )
        "lucide_octagon-alert" -> OctagonAlertIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_octagon-alert"] as OctagonAlertIconState?,
        )
        "lucide_octagon" -> OctagonIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_octagon"] as OctagonIconState?,
        )
        "lucide_octagon-minus" -> OctagonMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_octagon-minus"] as OctagonMinusIconState?,
        )
        "lucide_octagon-pause" -> OctagonPauseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_octagon-pause"] as OctagonPauseIconState?,
        )
        "lucide_octagon-x" -> OctagonXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_octagon-x"] as OctagonXIconState?,
        )
        "lucide_orbit" -> OrbitIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_orbit"] as OrbitIconState?,
        )
        "lucide_package-check" -> PackageCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_package-check"] as PackageCheckIconState?,
        )
        "lucide_package" -> PackageIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_package"] as PackageIconState?,
        )
        "lucide_package-open" -> PackageOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_package-open"] as PackageOpenIconState?,
        )
        "lucide_package-plus" -> PackagePlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_package-plus"] as PackagePlusIconState?,
        )
        "lucide_package-search" -> PackageSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_package-search"] as PackageSearchIconState?,
        )
        "lucide_package-x" -> PackageXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_package-x"] as PackageXIconState?,
        )
        "lucide_paint-bucket" -> PaintBucketIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_paint-bucket"] as PaintBucketIconState?,
        )
        "lucide_paintbrush" -> PaintbrushIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_paintbrush"] as PaintbrushIconState?,
        )
        "lucide_palette" -> PaletteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_palette"] as PaletteIconState?,
        )
        "lucide_panel-bottom-close" -> PanelBottomCloseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-bottom-close"] as PanelBottomCloseIconState?,
        )
        "lucide_panel-bottom-dashed" -> PanelBottomDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-bottom-dashed"] as PanelBottomDashedIconState?,
        )
        "lucide_panel-bottom" -> PanelBottomIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-bottom"] as PanelBottomIconState?,
        )
        "lucide_panel-bottom-open" -> PanelBottomOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-bottom-open"] as PanelBottomOpenIconState?,
        )
        "lucide_panel-left-close" -> PanelLeftCloseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-left-close"] as PanelLeftCloseIconState?,
        )
        "lucide_panel-left-dashed" -> PanelLeftDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-left-dashed"] as PanelLeftDashedIconState?,
        )
        "lucide_panel-left" -> PanelLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-left"] as PanelLeftIconState?,
        )
        "lucide_panel-left-open" -> PanelLeftOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-left-open"] as PanelLeftOpenIconState?,
        )
        "lucide_panel-left-right-dashed" -> PanelLeftRightDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-left-right-dashed"] as PanelLeftRightDashedIconState?,
        )
        "lucide_panel-right-close" -> PanelRightCloseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-right-close"] as PanelRightCloseIconState?,
        )
        "lucide_panel-right-dashed" -> PanelRightDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-right-dashed"] as PanelRightDashedIconState?,
        )
        "lucide_panel-right" -> PanelRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-right"] as PanelRightIconState?,
        )
        "lucide_panel-right-open" -> PanelRightOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-right-open"] as PanelRightOpenIconState?,
        )
        "lucide_panel-top-bottom-dashed" -> PanelTopBottomDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-top-bottom-dashed"] as PanelTopBottomDashedIconState?,
        )
        "lucide_panel-top-close" -> PanelTopCloseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-top-close"] as PanelTopCloseIconState?,
        )
        "lucide_panel-top-dashed" -> PanelTopDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-top-dashed"] as PanelTopDashedIconState?,
        )
        "lucide_panel-top" -> PanelTopIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-top"] as PanelTopIconState?,
        )
        "lucide_panel-top-open" -> PanelTopOpenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_panel-top-open"] as PanelTopOpenIconState?,
        )
        "lucide_paperclip" -> PaperclipIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_paperclip"] as PaperclipIconState?,
        )
        "lucide_party-popper" -> PartyPopperIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_party-popper"] as PartyPopperIconState?,
        )
        "lucide_pause" -> LucidePauseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pause"] as LucidePauseIconState?,
        )
        "lucide_paw-print" -> PawPrintIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_paw-print"] as PawPrintIconState?,
        )
        "lucide_pen-tool" -> PenToolIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pen-tool"] as PenToolIconState?,
        )
        "lucide_pencil" -> PencilIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pencil"] as PencilIconState?,
        )
        "lucide_pentagon" -> PentagonIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pentagon"] as PentagonIconState?,
        )
        "lucide_percent" -> PercentIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_percent"] as PercentIconState?,
        )
        "lucide_philippine-peso" -> PhilippinePesoIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_philippine-peso"] as PhilippinePesoIconState?,
        )
        "lucide_phone-call" -> PhoneCallIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_phone-call"] as PhoneCallIconState?,
        )
        "lucide_phone-forwarded" -> PhoneForwardedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_phone-forwarded"] as PhoneForwardedIconState?,
        )
        "lucide_phone" -> PhoneIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_phone"] as PhoneIconState?,
        )
        "lucide_phone-incoming" -> PhoneIncomingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_phone-incoming"] as PhoneIncomingIconState?,
        )
        "lucide_phone-missed" -> PhoneMissedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_phone-missed"] as PhoneMissedIconState?,
        )
        "lucide_phone-off" -> PhoneOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_phone-off"] as PhoneOffIconState?,
        )
        "lucide_piano" -> PianoIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_piano"] as PianoIconState?,
        )
        "lucide_picture-in-picture" -> PictureInPictureIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_picture-in-picture"] as PictureInPictureIconState?,
        )
        "lucide_piggy-bank" -> PiggyBankIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_piggy-bank"] as PiggyBankIconState?,
        )
        "lucide_pilcrow" -> PilcrowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pilcrow"] as PilcrowIconState?,
        )
        "lucide_pilcrow-left" -> PilcrowLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pilcrow-left"] as PilcrowLeftIconState?,
        )
        "lucide_pilcrow-right" -> PilcrowRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pilcrow-right"] as PilcrowRightIconState?,
        )
        "lucide_pill" -> PillIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pill"] as PillIconState?,
        )
        else -> Unit
    }
}

@Composable
private fun LucideDetailChunk7(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_pin" -> PinIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pin"] as PinIconState?,
        )
        "lucide_pin-off" -> PinOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pin-off"] as PinOffIconState?,
        )
        "lucide_pipette" -> PipetteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pipette"] as PipetteIconState?,
        )
        "lucide_plane" -> PlaneIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_plane"] as PlaneIconState?,
        )
        "lucide_play" -> LucidePlayIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_play"] as LucidePlayIconState?,
        )
        "lucide_play-off" -> PlayOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_play-off"] as PlayOffIconState?,
        )
        "lucide_plug-2" -> Plug2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_plug-2"] as Plug2IconState?,
        )
        "lucide_plug" -> PlugIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_plug"] as PlugIconState?,
        )
        "lucide_plug-zap" -> PlugZapIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_plug-zap"] as PlugZapIconState?,
        )
        "lucide_plus" -> PlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_plus"] as PlusIconState?,
        )
        "lucide_podcast" -> PodcastIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_podcast"] as PodcastIconState?,
        )
        "lucide_pound-sterling" -> PoundSterlingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_pound-sterling"] as PoundSterlingIconState?,
        )
        "lucide_power" -> PowerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_power"] as PowerIconState?,
        )
        "lucide_power-off" -> PowerOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_power-off"] as PowerOffIconState?,
        )
        "lucide_presentation" -> PresentationIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_presentation"] as PresentationIconState?,
        )
        "lucide_printer" -> PrinterIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_printer"] as PrinterIconState?,
        )
        "lucide_puzzle" -> PuzzleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_puzzle"] as PuzzleIconState?,
        )
        "lucide_qr-code" -> QrCodeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_qr-code"] as QrCodeIconState?,
        )
        "lucide_quote" -> QuoteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_quote"] as QuoteIconState?,
        )
        "lucide_rabbit" -> RabbitIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rabbit"] as RabbitIconState?,
        )
        "lucide_radar" -> RadarIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_radar"] as RadarIconState?,
        )
        "lucide_radio" -> RadioIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_radio"] as RadioIconState?,
        )
        "lucide_radio-off" -> RadioOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_radio-off"] as RadioOffIconState?,
        )
        "lucide_radio-receiver" -> RadioReceiverIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_radio-receiver"] as RadioReceiverIconState?,
        )
        "lucide_rainbow" -> RainbowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rainbow"] as RainbowIconState?,
        )
        "lucide_receipt" -> ReceiptIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_receipt"] as ReceiptIconState?,
        )
        "lucide_receipt-text" -> ReceiptTextIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_receipt-text"] as ReceiptTextIconState?,
        )
        "lucide_recycle" -> RecycleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_recycle"] as RecycleIconState?,
        )
        "lucide_redo" -> RedoIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_redo"] as RedoIconState?,
        )
        "lucide_refresh-ccw-dot" -> RefreshCcwDotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_refresh-ccw-dot"] as RefreshCcwDotIconState?,
        )
        "lucide_refresh-ccw" -> RefreshCcwIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_refresh-ccw"] as RefreshCcwIconState?,
        )
        "lucide_refresh-cw" -> RefreshCwIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_refresh-cw"] as RefreshCwIconState?,
        )
        "lucide_refresh-cw-off" -> RefreshCwOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_refresh-cw-off"] as RefreshCwOffIconState?,
        )
        "lucide_regex" -> RegexIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_regex"] as RegexIconState?,
        )
        "lucide_remove-formatting" -> RemoveFormattingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_remove-formatting"] as RemoveFormattingIconState?,
        )
        "lucide_repeat-1" -> Repeat1Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_repeat-1"] as Repeat1IconState?,
        )
        "lucide_repeat-2" -> Repeat2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_repeat-2"] as Repeat2IconState?,
        )
        "lucide_repeat" -> RepeatIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_repeat"] as RepeatIconState?,
        )
        "lucide_reply-all" -> ReplyAllIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_reply-all"] as ReplyAllIconState?,
        )
        "lucide_reply" -> ReplyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_reply"] as ReplyIconState?,
        )
        "lucide_rewind" -> RewindIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rewind"] as RewindIconState?,
        )
        "lucide_rocket" -> RocketIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rocket"] as RocketIconState?,
        )
        "lucide_rotate-ccw" -> RotateCcwIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rotate-ccw"] as RotateCcwIconState?,
        )
        "lucide_rotate-cw" -> RotateCwIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rotate-cw"] as RotateCwIconState?,
        )
        "lucide_route" -> RouteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_route"] as RouteIconState?,
        )
        "lucide_route-off" -> RouteOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_route-off"] as RouteOffIconState?,
        )
        "lucide_router" -> RouterIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_router"] as RouterIconState?,
        )
        "lucide_rows-2" -> Rows2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rows-2"] as Rows2IconState?,
        )
        "lucide_rows-3" -> Rows3Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rows-3"] as Rows3IconState?,
        )
        "lucide_rows-4" -> Rows4Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rows-4"] as Rows4IconState?,
        )
        "lucide_rss" -> RssIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_rss"] as RssIconState?,
        )
        "lucide_ruler" -> RulerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ruler"] as RulerIconState?,
        )
        "lucide_russian-ruble" -> RussianRubleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_russian-ruble"] as RussianRubleIconState?,
        )
        "lucide_saudi-riyal" -> SaudiRiyalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_saudi-riyal"] as SaudiRiyalIconState?,
        )
        "lucide_save" -> SaveIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_save"] as SaveIconState?,
        )
        "lucide_scan" -> ScanIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_scan"] as ScanIconState?,
        )
        "lucide_scan-line" -> ScanLineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_scan-line"] as ScanLineIconState?,
        )
        "lucide_scan-qr-code" -> ScanQrCodeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_scan-qr-code"] as ScanQrCodeIconState?,
        )
        "lucide_school" -> SchoolIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_school"] as SchoolIconState?,
        )
        "lucide_scissors" -> ScissorsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_scissors"] as ScissorsIconState?,
        )
        "lucide_screen-share" -> ScreenShareIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_screen-share"] as ScreenShareIconState?,
        )
        "lucide_scroll-text" -> ScrollTextIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_scroll-text"] as ScrollTextIconState?,
        )
        "lucide_search" -> SearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_search"] as SearchIconState?,
        )
        "lucide_search-x" -> SearchXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_search-x"] as SearchXIconState?,
        )
        "lucide_send-horizontal" -> SendHorizontalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_send-horizontal"] as SendHorizontalIconState?,
        )
        "lucide_send" -> SendIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_send"] as SendIconState?,
        )
        "lucide_server-cog" -> ServerCogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_server-cog"] as ServerCogIconState?,
        )
        "lucide_server-crash" -> ServerCrashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_server-crash"] as ServerCrashIconState?,
        )
        "lucide_server" -> ServerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_server"] as ServerIconState?,
        )
        "lucide_server-off" -> ServerOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_server-off"] as ServerOffIconState?,
        )
        "lucide_server-plus" -> ServerPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_server-plus"] as ServerPlusIconState?,
        )
        "lucide_settings" -> SettingsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_settings"] as SettingsIconState?,
        )
        "lucide_shapes" -> ShapesIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shapes"] as ShapesIconState?,
        )
        "lucide_share" -> ShareIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_share"] as ShareIconState?,
        )
        "lucide_shield-alert" -> ShieldAlertIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-alert"] as ShieldAlertIconState?,
        )
        "lucide_shield-ban" -> ShieldBanIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-ban"] as ShieldBanIconState?,
        )
        "lucide_shield-check" -> ShieldCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-check"] as ShieldCheckIconState?,
        )
        "lucide_shield-cog-corner" -> ShieldCogCornerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-cog-corner"] as ShieldCogCornerIconState?,
        )
        "lucide_shield-cog" -> ShieldCogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-cog"] as ShieldCogIconState?,
        )
        "lucide_shield-ellipsis" -> ShieldEllipsisIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-ellipsis"] as ShieldEllipsisIconState?,
        )
        "lucide_shield-half" -> ShieldHalfIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-half"] as ShieldHalfIconState?,
        )
        "lucide_shield" -> ShieldIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield"] as ShieldIconState?,
        )
        "lucide_shield-keyhole" -> ShieldKeyholeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-keyhole"] as ShieldKeyholeIconState?,
        )
        "lucide_shield-lock" -> ShieldLockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-lock"] as ShieldLockIconState?,
        )
        "lucide_shield-minus" -> ShieldMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-minus"] as ShieldMinusIconState?,
        )
        "lucide_shield-off" -> ShieldOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-off"] as ShieldOffIconState?,
        )
        "lucide_shield-plus" -> ShieldPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-plus"] as ShieldPlusIconState?,
        )
        "lucide_shield-question-mark" -> ShieldQuestionMarkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-question-mark"] as ShieldQuestionMarkIconState?,
        )
        "lucide_shield-user" -> ShieldUserIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-user"] as ShieldUserIconState?,
        )
        "lucide_shield-x" -> ShieldXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shield-x"] as ShieldXIconState?,
        )
        "lucide_shopping-bag" -> ShoppingBagIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shopping-bag"] as ShoppingBagIconState?,
        )
        "lucide_shopping-basket" -> ShoppingBasketIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shopping-basket"] as ShoppingBasketIconState?,
        )
        "lucide_shopping-cart" -> ShoppingCartIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shopping-cart"] as ShoppingCartIconState?,
        )
        "lucide_shopping-cart-minus" -> ShoppingCartMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shopping-cart-minus"] as ShoppingCartMinusIconState?,
        )
        "lucide_shopping-cart-plus" -> ShoppingCartPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shopping-cart-plus"] as ShoppingCartPlusIconState?,
        )
        "lucide_shrink" -> ShrinkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shrink"] as ShrinkIconState?,
        )
        "lucide_shuffle" -> ShuffleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_shuffle"] as ShuffleIconState?,
        )
        "lucide_sigma" -> SigmaIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sigma"] as SigmaIconState?,
        )
        "lucide_signal-high" -> SignalHighIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_signal-high"] as SignalHighIconState?,
        )
        "lucide_signal" -> SignalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_signal"] as SignalIconState?,
        )
        else -> Unit
    }
}

@Composable
private fun LucideDetailChunk8(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_signal-low" -> SignalLowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_signal-low"] as SignalLowIconState?,
        )
        "lucide_signal-medium" -> SignalMediumIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_signal-medium"] as SignalMediumIconState?,
        )
        "lucide_signal-zero" -> SignalZeroIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_signal-zero"] as SignalZeroIconState?,
        )
        "lucide_signature" -> SignatureIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_signature"] as SignatureIconState?,
        )
        "lucide_signpost-big" -> SignpostBigIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_signpost-big"] as SignpostBigIconState?,
        )
        "lucide_signpost" -> SignpostIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_signpost"] as SignpostIconState?,
        )
        "lucide_siren" -> SirenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_siren"] as SirenIconState?,
        )
        "lucide_skip-back" -> SkipBackIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_skip-back"] as SkipBackIconState?,
        )
        "lucide_skip-forward" -> SkipForwardIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_skip-forward"] as SkipForwardIconState?,
        )
        "lucide_sliders-horizontal" -> SlidersHorizontalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sliders-horizontal"] as SlidersHorizontalIconState?,
        )
        "lucide_smartphone-charging" -> SmartphoneChargingIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_smartphone-charging"] as SmartphoneChargingIconState?,
        )
        "lucide_smartphone" -> SmartphoneIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_smartphone"] as SmartphoneIconState?,
        )
        "lucide_smartphone-nfc" -> SmartphoneNfcIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_smartphone-nfc"] as SmartphoneNfcIconState?,
        )
        "lucide_snowflake" -> SnowflakeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_snowflake"] as SnowflakeIconState?,
        )
        "lucide_sparkles" -> SparklesIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sparkles"] as SparklesIconState?,
        )
        "lucide_speaker" -> SpeakerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_speaker"] as SpeakerIconState?,
        )
        "lucide_spell-check" -> SpellCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_spell-check"] as SpellCheckIconState?,
        )
        "lucide_spray-can" -> SprayCanIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_spray-can"] as SprayCanIconState?,
        )
        "lucide_sprout" -> SproutIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sprout"] as SproutIconState?,
        )
        "lucide_square-arrow-down" -> SquareArrowDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-down"] as SquareArrowDownIconState?,
        )
        "lucide_square-arrow-down-left" -> SquareArrowDownLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-down-left"] as SquareArrowDownLeftIconState?,
        )
        "lucide_square-arrow-down-right" -> SquareArrowDownRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-down-right"] as SquareArrowDownRightIconState?,
        )
        "lucide_square-arrow-left" -> SquareArrowLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-left"] as SquareArrowLeftIconState?,
        )
        "lucide_square-arrow-out-down-left" -> SquareArrowOutDownLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-out-down-left"] as SquareArrowOutDownLeftIconState?,
        )
        "lucide_square-arrow-out-down-right" -> SquareArrowOutDownRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-out-down-right"] as SquareArrowOutDownRightIconState?,
        )
        "lucide_square-arrow-out-up-left" -> SquareArrowOutUpLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-out-up-left"] as SquareArrowOutUpLeftIconState?,
        )
        "lucide_square-arrow-out-up-right" -> SquareArrowOutUpRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-out-up-right"] as SquareArrowOutUpRightIconState?,
        )
        "lucide_square-arrow-right-enter" -> SquareArrowRightEnterIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-right-enter"] as SquareArrowRightEnterIconState?,
        )
        "lucide_square-arrow-right-exit" -> SquareArrowRightExitIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-right-exit"] as SquareArrowRightExitIconState?,
        )
        "lucide_square-arrow-right" -> SquareArrowRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-right"] as SquareArrowRightIconState?,
        )
        "lucide_square-arrow-up" -> SquareArrowUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-up"] as SquareArrowUpIconState?,
        )
        "lucide_square-arrow-up-left" -> SquareArrowUpLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-up-left"] as SquareArrowUpLeftIconState?,
        )
        "lucide_square-arrow-up-right" -> SquareArrowUpRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-arrow-up-right"] as SquareArrowUpRightIconState?,
        )
        "lucide_square-check-big" -> SquareCheckBigIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-check-big"] as SquareCheckBigIconState?,
        )
        "lucide_square-check" -> SquareCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-check"] as SquareCheckIconState?,
        )
        "lucide_square-chevron-down" -> SquareChevronDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-chevron-down"] as SquareChevronDownIconState?,
        )
        "lucide_square-chevron-left" -> SquareChevronLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-chevron-left"] as SquareChevronLeftIconState?,
        )
        "lucide_square-chevron-right" -> SquareChevronRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-chevron-right"] as SquareChevronRightIconState?,
        )
        "lucide_square-chevron-up" -> SquareChevronUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-chevron-up"] as SquareChevronUpIconState?,
        )
        "lucide_square-code" -> SquareCodeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-code"] as SquareCodeIconState?,
        )
        "lucide_square-dashed" -> SquareDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-dashed"] as SquareDashedIconState?,
        )
        "lucide_square-dot" -> SquareDotIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-dot"] as SquareDotIconState?,
        )
        "lucide_square" -> SquareIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square"] as SquareIconState?,
        )
        "lucide_square-minus" -> SquareMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-minus"] as SquareMinusIconState?,
        )
        "lucide_square-pause" -> SquarePauseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-pause"] as SquarePauseIconState?,
        )
        "lucide_square-pen" -> SquarePenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-pen"] as SquarePenIconState?,
        )
        "lucide_square-play" -> SquarePlayIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-play"] as SquarePlayIconState?,
        )
        "lucide_square-plus" -> SquarePlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-plus"] as SquarePlusIconState?,
        )
        "lucide_square-slash" -> SquareSlashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-slash"] as SquareSlashIconState?,
        )
        "lucide_square-stop" -> SquareStopIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-stop"] as SquareStopIconState?,
        )
        "lucide_square-terminal" -> SquareTerminalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-terminal"] as SquareTerminalIconState?,
        )
        "lucide_square-user" -> SquareUserIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-user"] as SquareUserIconState?,
        )
        "lucide_square-user-round" -> SquareUserRoundIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-user-round"] as SquareUserRoundIconState?,
        )
        "lucide_square-x" -> SquareXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_square-x"] as SquareXIconState?,
        )
        "lucide_stamp" -> StampIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_stamp"] as StampIconState?,
        )
        "lucide_star-check" -> StarCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_star-check"] as StarCheckIconState?,
        )
        "lucide_star-half" -> StarHalfIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_star-half"] as StarHalfIconState?,
        )
        "lucide_star" -> StarIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_star"] as StarIconState?,
        )
        "lucide_star-minus" -> StarMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_star-minus"] as StarMinusIconState?,
        )
        "lucide_star-off" -> StarOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_star-off"] as StarOffIconState?,
        )
        "lucide_star-plus" -> StarPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_star-plus"] as StarPlusIconState?,
        )
        "lucide_star-x" -> StarXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_star-x"] as StarXIconState?,
        )
        "lucide_step-back" -> StepBackIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_step-back"] as StepBackIconState?,
        )
        "lucide_step-forward" -> StepForwardIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_step-forward"] as StepForwardIconState?,
        )
        "lucide_stethoscope" -> StethoscopeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_stethoscope"] as StethoscopeIconState?,
        )
        "lucide_sticky-note" -> StickyNoteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sticky-note"] as StickyNoteIconState?,
        )
        "lucide_store" -> StoreIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_store"] as StoreIconState?,
        )
        "lucide_strikethrough" -> StrikethroughIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_strikethrough"] as StrikethroughIconState?,
        )
        "lucide_subscript" -> SubscriptIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_subscript"] as SubscriptIconState?,
        )
        "lucide_sun-dim" -> SunDimIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sun-dim"] as SunDimIconState?,
        )
        "lucide_sun" -> SunIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sun"] as SunIconState?,
        )
        "lucide_sun-medium" -> SunMediumIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sun-medium"] as SunMediumIconState?,
        )
        "lucide_sun-moon" -> SunMoonIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sun-moon"] as SunMoonIconState?,
        )
        "lucide_sun-snow" -> SunSnowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sun-snow"] as SunSnowIconState?,
        )
        "lucide_sunrise" -> SunriseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sunrise"] as SunriseIconState?,
        )
        "lucide_sunset" -> SunsetIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_sunset"] as SunsetIconState?,
        )
        "lucide_superscript" -> SuperscriptIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_superscript"] as SuperscriptIconState?,
        )
        "lucide_swiss-franc" -> SwissFrancIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_swiss-franc"] as SwissFrancIconState?,
        )
        "lucide_swords" -> SwordsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_swords"] as SwordsIconState?,
        )
        "lucide_table" -> TableIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_table"] as TableIconState?,
        )
        "lucide_tablet" -> TabletIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_tablet"] as TabletIconState?,
        )
        "lucide_tag" -> TagIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_tag"] as TagIconState?,
        )
        "lucide_tag-plus" -> TagPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_tag-plus"] as TagPlusIconState?,
        )
        "lucide_tag-x" -> TagXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_tag-x"] as TagXIconState?,
        )
        "lucide_tags" -> TagsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_tags"] as TagsIconState?,
        )
        "lucide_target" -> TargetIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_target"] as TargetIconState?,
        )
        "lucide_telescope" -> TelescopeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_telescope"] as TelescopeIconState?,
        )
        "lucide_tent" -> TentIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_tent"] as TentIconState?,
        )
        "lucide_terminal" -> TerminalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_terminal"] as TerminalIconState?,
        )
        "lucide_text-cursor" -> TextCursorIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_text-cursor"] as TextCursorIconState?,
        )
        "lucide_text-initial" -> TextInitialIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_text-initial"] as TextInitialIconState?,
        )
        "lucide_text-quote" -> TextQuoteIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_text-quote"] as TextQuoteIconState?,
        )
        "lucide_text-search" -> TextSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_text-search"] as TextSearchIconState?,
        )
        "lucide_thermometer" -> ThermometerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_thermometer"] as ThermometerIconState?,
        )
        "lucide_thermometer-sun" -> ThermometerSunIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_thermometer-sun"] as ThermometerSunIconState?,
        )
        "lucide_thumbs-down" -> ThumbsDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_thumbs-down"] as ThumbsDownIconState?,
        )
        "lucide_thumbs-up" -> ThumbsUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_thumbs-up"] as ThumbsUpIconState?,
        )
        "lucide_ticket-check" -> TicketCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ticket-check"] as TicketCheckIconState?,
        )
        "lucide_ticket" -> TicketIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ticket"] as TicketIconState?,
        )
        "lucide_ticket-minus" -> TicketMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ticket-minus"] as TicketMinusIconState?,
        )
        else -> Unit
    }
}

@Composable
private fun LucideDetailChunk9(
    id: String, size: Dp, color: Color, duration: Float, states: LucideIconStates,
) {
    when (id) {
        "lucide_ticket-percent" -> TicketPercentIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ticket-percent"] as TicketPercentIconState?,
        )
        "lucide_ticket-plus" -> TicketPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ticket-plus"] as TicketPlusIconState?,
        )
        "lucide_ticket-slash" -> TicketSlashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ticket-slash"] as TicketSlashIconState?,
        )
        "lucide_ticket-x" -> TicketXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_ticket-x"] as TicketXIconState?,
        )
        "lucide_timer" -> TimerIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_timer"] as TimerIconState?,
        )
        "lucide_timer-off" -> TimerOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_timer-off"] as TimerOffIconState?,
        )
        "lucide_timer-reset" -> TimerResetIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_timer-reset"] as TimerResetIconState?,
        )
        "lucide_toggle-left" -> ToggleLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_toggle-left"] as ToggleLeftIconState?,
        )
        "lucide_toggle-right" -> ToggleRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_toggle-right"] as ToggleRightIconState?,
        )
        "lucide_tornado" -> TornadoIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_tornado"] as TornadoIconState?,
        )
        "lucide_traffic-cone" -> TrafficConeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_traffic-cone"] as TrafficConeIconState?,
        )
        "lucide_trash-2" -> Trash2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_trash-2"] as Trash2IconState?,
        )
        "lucide_trash" -> TrashIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_trash"] as TrashIconState?,
        )
        "lucide_trending-down" -> TrendingDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_trending-down"] as TrendingDownIconState?,
        )
        "lucide_trending-up-down" -> TrendingUpDownIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_trending-up-down"] as TrendingUpDownIconState?,
        )
        "lucide_trending-up" -> TrendingUpIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_trending-up"] as TrendingUpIconState?,
        )
        "lucide_triangle-alert" -> TriangleAlertIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_triangle-alert"] as TriangleAlertIconState?,
        )
        "lucide_triangle-dashed" -> TriangleDashedIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_triangle-dashed"] as TriangleDashedIconState?,
        )
        "lucide_triangle" -> TriangleIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_triangle"] as TriangleIconState?,
        )
        "lucide_triangle-right" -> TriangleRightIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_triangle-right"] as TriangleRightIconState?,
        )
        "lucide_trophy" -> TrophyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_trophy"] as TrophyIconState?,
        )
        "lucide_truck" -> TruckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_truck"] as TruckIconState?,
        )
        "lucide_turkish-lira" -> TurkishLiraIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_turkish-lira"] as TurkishLiraIconState?,
        )
        "lucide_tv" -> TvIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_tv"] as TvIconState?,
        )
        "lucide_tv-minimal" -> TvMinimalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_tv-minimal"] as TvMinimalIconState?,
        )
        "lucide_twitter" -> TwitterIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_twitter"] as TwitterIconState?,
        )
        "lucide_type" -> TypeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_type"] as TypeIconState?,
        )
        "lucide_umbrella" -> UmbrellaIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_umbrella"] as UmbrellaIconState?,
        )
        "lucide_underline" -> UnderlineIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_underline"] as UnderlineIconState?,
        )
        "lucide_undo" -> UndoIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_undo"] as UndoIconState?,
        )
        "lucide_unfold-vertical" -> UnfoldVerticalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_unfold-vertical"] as UnfoldVerticalIconState?,
        )
        "lucide_university" -> UniversityIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_university"] as UniversityIconState?,
        )
        "lucide_unlink" -> UnlinkIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_unlink"] as UnlinkIconState?,
        )
        "lucide_upload" -> UploadIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_upload"] as UploadIconState?,
        )
        "lucide_usb" -> UsbIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_usb"] as UsbIconState?,
        )
        "lucide_user-check" -> UserCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-check"] as UserCheckIconState?,
        )
        "lucide_user-cog" -> UserCogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-cog"] as UserCogIconState?,
        )
        "lucide_user-group" -> UserGroupIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-group"] as UserGroupIconState?,
        )
        "lucide_user" -> UserIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user"] as UserIconState?,
        )
        "lucide_user-key" -> UserKeyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-key"] as UserKeyIconState?,
        )
        "lucide_user-lock" -> UserLockIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-lock"] as UserLockIconState?,
        )
        "lucide_user-minus" -> UserMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-minus"] as UserMinusIconState?,
        )
        "lucide_user-pen" -> UserPenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-pen"] as UserPenIconState?,
        )
        "lucide_user-plus" -> UserPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-plus"] as UserPlusIconState?,
        )
        "lucide_user-round-arrow-left" -> UserRoundArrowLeftIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-arrow-left"] as UserRoundArrowLeftIconState?,
        )
        "lucide_user-round-check" -> UserRoundCheckIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-check"] as UserRoundCheckIconState?,
        )
        "lucide_user-round-cog" -> UserRoundCogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-cog"] as UserRoundCogIconState?,
        )
        "lucide_user-round-group" -> UserRoundGroupIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-group"] as UserRoundGroupIconState?,
        )
        "lucide_user-round" -> UserRoundIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round"] as UserRoundIconState?,
        )
        "lucide_user-round-key" -> UserRoundKeyIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-key"] as UserRoundKeyIconState?,
        )
        "lucide_user-round-minus" -> UserRoundMinusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-minus"] as UserRoundMinusIconState?,
        )
        "lucide_user-round-pen" -> UserRoundPenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-pen"] as UserRoundPenIconState?,
        )
        "lucide_user-round-plus" -> UserRoundPlusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-plus"] as UserRoundPlusIconState?,
        )
        "lucide_user-round-search" -> UserRoundSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-search"] as UserRoundSearchIconState?,
        )
        "lucide_user-round-x" -> UserRoundXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-round-x"] as UserRoundXIconState?,
        )
        "lucide_user-search" -> UserSearchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-search"] as UserSearchIconState?,
        )
        "lucide_user-shield" -> UserShieldIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-shield"] as UserShieldIconState?,
        )
        "lucide_user-star" -> UserStarIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-star"] as UserStarIconState?,
        )
        "lucide_user-x" -> UserXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_user-x"] as UserXIconState?,
        )
        "lucide_users" -> UsersIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_users"] as UsersIconState?,
        )
        "lucide_users-round" -> UsersRoundIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_users-round"] as UsersRoundIconState?,
        )
        "lucide_utensils" -> UtensilsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_utensils"] as UtensilsIconState?,
        )
        "lucide_variable" -> VariableIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_variable"] as VariableIconState?,
        )
        "lucide_vault" -> VaultIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_vault"] as VaultIconState?,
        )
        "lucide_venus" -> VenusIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_venus"] as VenusIconState?,
        )
        "lucide_video" -> VideoIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_video"] as VideoIconState?,
        )
        "lucide_video-off" -> VideoOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_video-off"] as VideoOffIconState?,
        )
        "lucide_volume-1" -> Volume1Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_volume-1"] as Volume1IconState?,
        )
        "lucide_volume-2" -> Volume2Icon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_volume-2"] as Volume2IconState?,
        )
        "lucide_volume" -> VolumeIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_volume"] as VolumeIconState?,
        )
        "lucide_volume-off" -> VolumeOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_volume-off"] as VolumeOffIconState?,
        )
        "lucide_volume-x" -> VolumeXIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_volume-x"] as VolumeXIconState?,
        )
        "lucide_wallet-cards" -> WalletCardsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wallet-cards"] as WalletCardsIconState?,
        )
        "lucide_wallet" -> WalletIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wallet"] as WalletIconState?,
        )
        "lucide_wallet-minimal" -> WalletMinimalIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wallet-minimal"] as WalletMinimalIconState?,
        )
        "lucide_wand-sparkles" -> WandSparklesIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wand-sparkles"] as WandSparklesIconState?,
        )
        "lucide_warehouse" -> WarehouseIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_warehouse"] as WarehouseIconState?,
        )
        "lucide_watch" -> WatchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_watch"] as WatchIconState?,
        )
        "lucide_waypoints" -> WaypointsIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_waypoints"] as WaypointsIconState?,
        )
        "lucide_webcam" -> WebcamIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_webcam"] as WebcamIconState?,
        )
        "lucide_webcam-off" -> WebcamOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_webcam-off"] as WebcamOffIconState?,
        )
        "lucide_webhook" -> WebhookIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_webhook"] as WebhookIconState?,
        )
        "lucide_webhook-off" -> WebhookOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_webhook-off"] as WebhookOffIconState?,
        )
        "lucide_wifi-cog" -> WifiCogIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wifi-cog"] as WifiCogIconState?,
        )
        "lucide_wifi-high" -> WifiHighIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wifi-high"] as WifiHighIconState?,
        )
        "lucide_wifi" -> WifiIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wifi"] as WifiIconState?,
        )
        "lucide_wifi-low" -> WifiLowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wifi-low"] as WifiLowIconState?,
        )
        "lucide_wifi-off" -> WifiOffIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wifi-off"] as WifiOffIconState?,
        )
        "lucide_wifi-pen" -> WifiPenIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wifi-pen"] as WifiPenIconState?,
        )
        "lucide_wifi-sync" -> WifiSyncIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wifi-sync"] as WifiSyncIconState?,
        )
        "lucide_wifi-zero" -> WifiZeroIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wifi-zero"] as WifiZeroIconState?,
        )
        "lucide_wind" -> WindIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wind"] as WindIconState?,
        )
        "lucide_workflow" -> WorkflowIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_workflow"] as WorkflowIconState?,
        )
        "lucide_wrap-text" -> WrapTextIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wrap-text"] as WrapTextIconState?,
        )
        "lucide_wrench" -> WrenchIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_wrench"] as WrenchIconState?,
        )
        "lucide_x" -> XIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_x"] as XIconState?,
        )
        "lucide_zap" -> ZapIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_zap"] as ZapIconState?,
        )
        "lucide_zoom-in" -> ZoomInIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_zoom-in"] as ZoomInIconState?,
        )
        "lucide_zoom-out" -> ZoomOutIcon(
            size = size,
            color = color,
            duration = duration,
            state = states.states["lucide_zoom-out"] as ZoomOutIconState?,
        )
        else -> Unit
    }
}

internal fun lucideStart(id: String, states: LucideIconStates) {
    lucideStartChunk0(id, states)
    lucideStartChunk1(id, states)
    lucideStartChunk2(id, states)
    lucideStartChunk3(id, states)
    lucideStartChunk4(id, states)
    lucideStartChunk5(id, states)
    lucideStartChunk6(id, states)
    lucideStartChunk7(id, states)
    lucideStartChunk8(id, states)
    lucideStartChunk9(id, states)
}

private fun lucideStartChunk0(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_a-arrow-down" -> states.startActions.getValue("lucide_a-arrow-down").invoke()
        "lucide_a-arrow-up" -> states.startActions.getValue("lucide_a-arrow-up").invoke()
        "lucide_accessibility" -> states.startActions.getValue("lucide_accessibility").invoke()
        "lucide_activity" -> states.startActions.getValue("lucide_activity").invoke()
        "lucide_alarm-clock-check" -> states.startActions.getValue("lucide_alarm-clock-check").invoke()
        "lucide_alarm-clock" -> states.startActions.getValue("lucide_alarm-clock").invoke()
        "lucide_alarm-clock-minus" -> states.startActions.getValue("lucide_alarm-clock-minus").invoke()
        "lucide_alarm-clock-off" -> states.startActions.getValue("lucide_alarm-clock-off").invoke()
        "lucide_alarm-clock-plus" -> states.startActions.getValue("lucide_alarm-clock-plus").invoke()
        "lucide_align-center" -> states.startActions.getValue("lucide_align-center").invoke()
        "lucide_align-justify" -> states.startActions.getValue("lucide_align-justify").invoke()
        "lucide_align-left" -> states.startActions.getValue("lucide_align-left").invoke()
        "lucide_align-right" -> states.startActions.getValue("lucide_align-right").invoke()
        "lucide_anchor" -> states.startActions.getValue("lucide_anchor").invoke()
        "lucide_angry" -> states.startActions.getValue("lucide_angry").invoke()
        "lucide_annoyed" -> states.startActions.getValue("lucide_annoyed").invoke()
        "lucide_app-window" -> states.startActions.getValue("lucide_app-window").invoke()
        "lucide_archive" -> states.startActions.getValue("lucide_archive").invoke()
        "lucide_arrow-big-down-dash" -> states.startActions.getValue("lucide_arrow-big-down-dash").invoke()
        "lucide_arrow-big-down" -> states.startActions.getValue("lucide_arrow-big-down").invoke()
        "lucide_arrow-big-left-dash" -> states.startActions.getValue("lucide_arrow-big-left-dash").invoke()
        "lucide_arrow-big-left" -> states.startActions.getValue("lucide_arrow-big-left").invoke()
        "lucide_arrow-big-right-dash" -> states.startActions.getValue("lucide_arrow-big-right-dash").invoke()
        "lucide_arrow-big-right" -> states.startActions.getValue("lucide_arrow-big-right").invoke()
        "lucide_arrow-big-up-dash" -> states.startActions.getValue("lucide_arrow-big-up-dash").invoke()
        "lucide_arrow-big-up" -> states.startActions.getValue("lucide_arrow-big-up").invoke()
        "lucide_arrow-down-0-1" -> states.startActions.getValue("lucide_arrow-down-0-1").invoke()
        "lucide_arrow-down-1-0" -> states.startActions.getValue("lucide_arrow-down-1-0").invoke()
        "lucide_arrow-down-a-z" -> states.startActions.getValue("lucide_arrow-down-a-z").invoke()
        "lucide_arrow-down-from-line" -> states.startActions.getValue("lucide_arrow-down-from-line").invoke()
        "lucide_arrow-down" -> states.startActions.getValue("lucide_arrow-down").invoke()
        "lucide_arrow-down-left" -> states.startActions.getValue("lucide_arrow-down-left").invoke()
        "lucide_arrow-down-narrow-wide" -> states.startActions.getValue("lucide_arrow-down-narrow-wide").invoke()
        "lucide_arrow-down-right" -> states.startActions.getValue("lucide_arrow-down-right").invoke()
        "lucide_arrow-down-to-dot" -> states.startActions.getValue("lucide_arrow-down-to-dot").invoke()
        "lucide_arrow-down-to-line" -> states.startActions.getValue("lucide_arrow-down-to-line").invoke()
        "lucide_arrow-down-up" -> states.startActions.getValue("lucide_arrow-down-up").invoke()
        "lucide_arrow-down-wide-narrow" -> states.startActions.getValue("lucide_arrow-down-wide-narrow").invoke()
        "lucide_arrow-down-z-a" -> states.startActions.getValue("lucide_arrow-down-z-a").invoke()
        "lucide_arrow-left-from-line" -> states.startActions.getValue("lucide_arrow-left-from-line").invoke()
        "lucide_arrow-left" -> states.startActions.getValue("lucide_arrow-left").invoke()
        "lucide_arrow-left-right" -> states.startActions.getValue("lucide_arrow-left-right").invoke()
        "lucide_arrow-left-to-line" -> states.startActions.getValue("lucide_arrow-left-to-line").invoke()
        "lucide_arrow-right-from-line" -> states.startActions.getValue("lucide_arrow-right-from-line").invoke()
        "lucide_arrow-right" -> states.startActions.getValue("lucide_arrow-right").invoke()
        "lucide_arrow-right-left" -> states.startActions.getValue("lucide_arrow-right-left").invoke()
        "lucide_arrow-right-to-line" -> states.startActions.getValue("lucide_arrow-right-to-line").invoke()
        "lucide_arrow-up-0-1" -> states.startActions.getValue("lucide_arrow-up-0-1").invoke()
        "lucide_arrow-up-1-0" -> states.startActions.getValue("lucide_arrow-up-1-0").invoke()
        "lucide_arrow-up-a-z" -> states.startActions.getValue("lucide_arrow-up-a-z").invoke()
        "lucide_arrow-up-down" -> states.startActions.getValue("lucide_arrow-up-down").invoke()
        "lucide_arrow-up-from-dot" -> states.startActions.getValue("lucide_arrow-up-from-dot").invoke()
        "lucide_arrow-up-from-line" -> states.startActions.getValue("lucide_arrow-up-from-line").invoke()
        "lucide_arrow-up" -> states.startActions.getValue("lucide_arrow-up").invoke()
        "lucide_arrow-up-left" -> states.startActions.getValue("lucide_arrow-up-left").invoke()
        "lucide_arrow-up-narrow-wide" -> states.startActions.getValue("lucide_arrow-up-narrow-wide").invoke()
        "lucide_arrow-up-right" -> states.startActions.getValue("lucide_arrow-up-right").invoke()
        "lucide_arrow-up-to-line" -> states.startActions.getValue("lucide_arrow-up-to-line").invoke()
        "lucide_arrow-up-wide-narrow" -> states.startActions.getValue("lucide_arrow-up-wide-narrow").invoke()
        "lucide_arrow-up-z-a" -> states.startActions.getValue("lucide_arrow-up-z-a").invoke()
        "lucide_at-sign" -> states.startActions.getValue("lucide_at-sign").invoke()
        "lucide_atom" -> states.startActions.getValue("lucide_atom").invoke()
        "lucide_audio-lines" -> states.startActions.getValue("lucide_audio-lines").invoke()
        "lucide_audio-lines-off" -> states.startActions.getValue("lucide_audio-lines-off").invoke()
        "lucide_audio-lines-x" -> states.startActions.getValue("lucide_audio-lines-x").invoke()
        "lucide_audio-waveform" -> states.startActions.getValue("lucide_audio-waveform").invoke()
        "lucide_badge-cent" -> states.startActions.getValue("lucide_badge-cent").invoke()
        "lucide_badge-check" -> states.startActions.getValue("lucide_badge-check").invoke()
        "lucide_badge-dollar-sign" -> states.startActions.getValue("lucide_badge-dollar-sign").invoke()
        "lucide_badge-info" -> states.startActions.getValue("lucide_badge-info").invoke()
        "lucide_badge-percent" -> states.startActions.getValue("lucide_badge-percent").invoke()
        "lucide_baggage-claim" -> states.startActions.getValue("lucide_baggage-claim").invoke()
        "lucide_ban" -> states.startActions.getValue("lucide_ban").invoke()
        "lucide_banknote-arrow-down" -> states.startActions.getValue("lucide_banknote-arrow-down").invoke()
        "lucide_banknote-arrow-up" -> states.startActions.getValue("lucide_banknote-arrow-up").invoke()
        "lucide_banknote-check" -> states.startActions.getValue("lucide_banknote-check").invoke()
        "lucide_banknote" -> states.startActions.getValue("lucide_banknote").invoke()
        "lucide_banknote-x" -> states.startActions.getValue("lucide_banknote-x").invoke()
        "lucide_barcode" -> states.startActions.getValue("lucide_barcode").invoke()
        "lucide_battery-charging" -> states.startActions.getValue("lucide_battery-charging").invoke()
        "lucide_battery-full" -> states.startActions.getValue("lucide_battery-full").invoke()
        "lucide_battery" -> states.startActions.getValue("lucide_battery").invoke()
        "lucide_battery-low" -> states.startActions.getValue("lucide_battery-low").invoke()
        "lucide_battery-medium" -> states.startActions.getValue("lucide_battery-medium").invoke()
        "lucide_battery-plus" -> states.startActions.getValue("lucide_battery-plus").invoke()
        "lucide_battery-warning" -> states.startActions.getValue("lucide_battery-warning").invoke()
        "lucide_bell-dot" -> states.startActions.getValue("lucide_bell-dot").invoke()
        "lucide_bell-electric" -> states.startActions.getValue("lucide_bell-electric").invoke()
        "lucide_bell" -> states.startActions.getValue("lucide_bell").invoke()
        "lucide_bell-minus" -> states.startActions.getValue("lucide_bell-minus").invoke()
        "lucide_bell-off" -> states.startActions.getValue("lucide_bell-off").invoke()
        "lucide_bell-plus" -> states.startActions.getValue("lucide_bell-plus").invoke()
        "lucide_bell-ring" -> states.startActions.getValue("lucide_bell-ring").invoke()
        "lucide_biceps-flexed" -> states.startActions.getValue("lucide_biceps-flexed").invoke()
        "lucide_bike" -> states.startActions.getValue("lucide_bike").invoke()
        "lucide_bitcoin" -> states.startActions.getValue("lucide_bitcoin").invoke()
        "lucide_blend" -> states.startActions.getValue("lucide_blend").invoke()
        "lucide_blocks" -> states.startActions.getValue("lucide_blocks").invoke()
        "lucide_bluetooth" -> states.startActions.getValue("lucide_bluetooth").invoke()
        "lucide_bold" -> states.startActions.getValue("lucide_bold").invoke()
        else -> Unit
    }
}

private fun lucideStartChunk1(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_bolt" -> states.startActions.getValue("lucide_bolt").invoke()
        "lucide_book-open-check" -> states.startActions.getValue("lucide_book-open-check").invoke()
        "lucide_book-open" -> states.startActions.getValue("lucide_book-open").invoke()
        "lucide_book-open-text" -> states.startActions.getValue("lucide_book-open-text").invoke()
        "lucide_bookmark-check" -> states.startActions.getValue("lucide_bookmark-check").invoke()
        "lucide_bookmark" -> states.startActions.getValue("lucide_bookmark").invoke()
        "lucide_bookmark-minus" -> states.startActions.getValue("lucide_bookmark-minus").invoke()
        "lucide_bookmark-off" -> states.startActions.getValue("lucide_bookmark-off").invoke()
        "lucide_bookmark-plus" -> states.startActions.getValue("lucide_bookmark-plus").invoke()
        "lucide_bookmark-x" -> states.startActions.getValue("lucide_bookmark-x").invoke()
        "lucide_bot" -> states.startActions.getValue("lucide_bot").invoke()
        "lucide_box" -> states.startActions.getValue("lucide_box").invoke()
        "lucide_boxes" -> states.startActions.getValue("lucide_boxes").invoke()
        "lucide_brain" -> states.startActions.getValue("lucide_brain").invoke()
        "lucide_briefcase" -> states.startActions.getValue("lucide_briefcase").invoke()
        "lucide_bring-to-front" -> states.startActions.getValue("lucide_bring-to-front").invoke()
        "lucide_bug" -> states.startActions.getValue("lucide_bug").invoke()
        "lucide_bug-off" -> states.startActions.getValue("lucide_bug-off").invoke()
        "lucide_bug-play" -> states.startActions.getValue("lucide_bug-play").invoke()
        "lucide_building-2" -> states.startActions.getValue("lucide_building-2").invoke()
        "lucide_building-complex" -> states.startActions.getValue("lucide_building-complex").invoke()
        "lucide_building-complex-plus" -> states.startActions.getValue("lucide_building-complex-plus").invoke()
        "lucide_building" -> states.startActions.getValue("lucide_building").invoke()
        "lucide_calculator" -> states.startActions.getValue("lucide_calculator").invoke()
        "lucide_calendar-arrow-down" -> states.startActions.getValue("lucide_calendar-arrow-down").invoke()
        "lucide_calendar-arrow-up" -> states.startActions.getValue("lucide_calendar-arrow-up").invoke()
        "lucide_calendar-check" -> states.startActions.getValue("lucide_calendar-check").invoke()
        "lucide_calendar-chevrons-right" -> states.startActions.getValue("lucide_calendar-chevrons-right").invoke()
        "lucide_calendar-clock" -> states.startActions.getValue("lucide_calendar-clock").invoke()
        "lucide_calendar-cog" -> states.startActions.getValue("lucide_calendar-cog").invoke()
        "lucide_calendar-days" -> states.startActions.getValue("lucide_calendar-days").invoke()
        "lucide_calendar-fold" -> states.startActions.getValue("lucide_calendar-fold").invoke()
        "lucide_calendar-heart" -> states.startActions.getValue("lucide_calendar-heart").invoke()
        "lucide_calendar" -> states.startActions.getValue("lucide_calendar").invoke()
        "lucide_calendar-minus" -> states.startActions.getValue("lucide_calendar-minus").invoke()
        "lucide_calendar-off" -> states.startActions.getValue("lucide_calendar-off").invoke()
        "lucide_calendar-plus" -> states.startActions.getValue("lucide_calendar-plus").invoke()
        "lucide_calendar-range" -> states.startActions.getValue("lucide_calendar-range").invoke()
        "lucide_calendar-search" -> states.startActions.getValue("lucide_calendar-search").invoke()
        "lucide_calendar-sync" -> states.startActions.getValue("lucide_calendar-sync").invoke()
        "lucide_calendar-x" -> states.startActions.getValue("lucide_calendar-x").invoke()
        "lucide_calendars" -> states.startActions.getValue("lucide_calendars").invoke()
        "lucide_camera" -> states.startActions.getValue("lucide_camera").invoke()
        "lucide_camera-off" -> states.startActions.getValue("lucide_camera-off").invoke()
        "lucide_captions" -> states.startActions.getValue("lucide_captions").invoke()
        "lucide_car" -> states.startActions.getValue("lucide_car").invoke()
        "lucide_case-lower" -> states.startActions.getValue("lucide_case-lower").invoke()
        "lucide_case-sensitive" -> states.startActions.getValue("lucide_case-sensitive").invoke()
        "lucide_case-upper" -> states.startActions.getValue("lucide_case-upper").invoke()
        "lucide_cast" -> states.startActions.getValue("lucide_cast").invoke()
        "lucide_cat" -> states.startActions.getValue("lucide_cat").invoke()
        "lucide_cctv" -> states.startActions.getValue("lucide_cctv").invoke()
        "lucide_cctv-off" -> states.startActions.getValue("lucide_cctv-off").invoke()
        "lucide_chart-area" -> states.startActions.getValue("lucide_chart-area").invoke()
        "lucide_chart-bar-big" -> states.startActions.getValue("lucide_chart-bar-big").invoke()
        "lucide_chart-bar-decreasing" -> states.startActions.getValue("lucide_chart-bar-decreasing").invoke()
        "lucide_chart-bar" -> states.startActions.getValue("lucide_chart-bar").invoke()
        "lucide_chart-bar-increasing" -> states.startActions.getValue("lucide_chart-bar-increasing").invoke()
        "lucide_chart-candlestick" -> states.startActions.getValue("lucide_chart-candlestick").invoke()
        "lucide_chart-column-big" -> states.startActions.getValue("lucide_chart-column-big").invoke()
        "lucide_chart-column-decreasing" -> states.startActions.getValue("lucide_chart-column-decreasing").invoke()
        "lucide_chart-column" -> states.startActions.getValue("lucide_chart-column").invoke()
        "lucide_chart-column-increasing" -> states.startActions.getValue("lucide_chart-column-increasing").invoke()
        "lucide_chart-gantt" -> states.startActions.getValue("lucide_chart-gantt").invoke()
        "lucide_chart-line" -> states.startActions.getValue("lucide_chart-line").invoke()
        "lucide_chart-network" -> states.startActions.getValue("lucide_chart-network").invoke()
        "lucide_chart-no-axes-column" -> states.startActions.getValue("lucide_chart-no-axes-column").invoke()
        "lucide_chart-no-axes-combined" -> states.startActions.getValue("lucide_chart-no-axes-combined").invoke()
        "lucide_chart-pie" -> states.startActions.getValue("lucide_chart-pie").invoke()
        "lucide_chart-scatter" -> states.startActions.getValue("lucide_chart-scatter").invoke()
        "lucide_chart-spline" -> states.startActions.getValue("lucide_chart-spline").invoke()
        "lucide_check-check" -> states.startActions.getValue("lucide_check-check").invoke()
        "lucide_check" -> states.startActions.getValue("lucide_check").invoke()
        "lucide_chevron-down" -> states.startActions.getValue("lucide_chevron-down").invoke()
        "lucide_chevron-first" -> states.startActions.getValue("lucide_chevron-first").invoke()
        "lucide_chevron-last" -> states.startActions.getValue("lucide_chevron-last").invoke()
        "lucide_chevron-left" -> states.startActions.getValue("lucide_chevron-left").invoke()
        "lucide_chevron-right" -> states.startActions.getValue("lucide_chevron-right").invoke()
        "lucide_chevron-up" -> states.startActions.getValue("lucide_chevron-up").invoke()
        "lucide_chevrons-down" -> states.startActions.getValue("lucide_chevrons-down").invoke()
        "lucide_chevrons-down-up" -> states.startActions.getValue("lucide_chevrons-down-up").invoke()
        "lucide_chevrons-left" -> states.startActions.getValue("lucide_chevrons-left").invoke()
        "lucide_chevrons-left-right-ellipsis" -> states.startActions.getValue("lucide_chevrons-left-right-ellipsis").invoke()
        "lucide_chevrons-left-right" -> states.startActions.getValue("lucide_chevrons-left-right").invoke()
        "lucide_chevrons-right" -> states.startActions.getValue("lucide_chevrons-right").invoke()
        "lucide_chevrons-right-left" -> states.startActions.getValue("lucide_chevrons-right-left").invoke()
        "lucide_chevrons-up-down" -> states.startActions.getValue("lucide_chevrons-up-down").invoke()
        "lucide_chevrons-up" -> states.startActions.getValue("lucide_chevrons-up").invoke()
        "lucide_chrome" -> states.startActions.getValue("lucide_chrome").invoke()
        "lucide_circle-alert" -> states.startActions.getValue("lucide_circle-alert").invoke()
        "lucide_circle-arrow-down" -> states.startActions.getValue("lucide_circle-arrow-down").invoke()
        "lucide_circle-arrow-left" -> states.startActions.getValue("lucide_circle-arrow-left").invoke()
        "lucide_circle-arrow-out-down-left" -> states.startActions.getValue("lucide_circle-arrow-out-down-left").invoke()
        "lucide_circle-arrow-out-down-right" -> states.startActions.getValue("lucide_circle-arrow-out-down-right").invoke()
        "lucide_circle-arrow-out-up-left" -> states.startActions.getValue("lucide_circle-arrow-out-up-left").invoke()
        "lucide_circle-arrow-out-up-right" -> states.startActions.getValue("lucide_circle-arrow-out-up-right").invoke()
        "lucide_circle-arrow-right" -> states.startActions.getValue("lucide_circle-arrow-right").invoke()
        "lucide_circle-arrow-up" -> states.startActions.getValue("lucide_circle-arrow-up").invoke()
        "lucide_circle-check-big" -> states.startActions.getValue("lucide_circle-check-big").invoke()
        "lucide_circle-check" -> states.startActions.getValue("lucide_circle-check").invoke()
        else -> Unit
    }
}

private fun lucideStartChunk2(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_circle-chevron-down" -> states.startActions.getValue("lucide_circle-chevron-down").invoke()
        "lucide_circle-chevron-left" -> states.startActions.getValue("lucide_circle-chevron-left").invoke()
        "lucide_circle-chevron-right" -> states.startActions.getValue("lucide_circle-chevron-right").invoke()
        "lucide_circle-chevron-up" -> states.startActions.getValue("lucide_circle-chevron-up").invoke()
        "lucide_circle-dashed" -> states.startActions.getValue("lucide_circle-dashed").invoke()
        "lucide_circle-dollar-sign" -> states.startActions.getValue("lucide_circle-dollar-sign").invoke()
        "lucide_circle-dot" -> states.startActions.getValue("lucide_circle-dot").invoke()
        "lucide_circle-ellipsis" -> states.startActions.getValue("lucide_circle-ellipsis").invoke()
        "lucide_circle-euro" -> states.startActions.getValue("lucide_circle-euro").invoke()
        "lucide_circle" -> states.startActions.getValue("lucide_circle").invoke()
        "lucide_circle-minus" -> states.startActions.getValue("lucide_circle-minus").invoke()
        "lucide_circle-off" -> states.startActions.getValue("lucide_circle-off").invoke()
        "lucide_circle-pause" -> states.startActions.getValue("lucide_circle-pause").invoke()
        "lucide_circle-percent" -> states.startActions.getValue("lucide_circle-percent").invoke()
        "lucide_circle-play" -> states.startActions.getValue("lucide_circle-play").invoke()
        "lucide_circle-plus" -> states.startActions.getValue("lucide_circle-plus").invoke()
        "lucide_circle-pound-sterling" -> states.startActions.getValue("lucide_circle-pound-sterling").invoke()
        "lucide_circle-power" -> states.startActions.getValue("lucide_circle-power").invoke()
        "lucide_circle-question-mark" -> states.startActions.getValue("lucide_circle-question-mark").invoke()
        "lucide_circle-slash-2" -> states.startActions.getValue("lucide_circle-slash-2").invoke()
        "lucide_circle-slash" -> states.startActions.getValue("lucide_circle-slash").invoke()
        "lucide_circle-star" -> states.startActions.getValue("lucide_circle-star").invoke()
        "lucide_circle-stop" -> states.startActions.getValue("lucide_circle-stop").invoke()
        "lucide_circle-user" -> states.startActions.getValue("lucide_circle-user").invoke()
        "lucide_circle-user-round" -> states.startActions.getValue("lucide_circle-user-round").invoke()
        "lucide_circle-x" -> states.startActions.getValue("lucide_circle-x").invoke()
        "lucide_clapperboard" -> states.startActions.getValue("lucide_clapperboard").invoke()
        "lucide_clipboard-check" -> states.startActions.getValue("lucide_clipboard-check").invoke()
        "lucide_clipboard-clock" -> states.startActions.getValue("lucide_clipboard-clock").invoke()
        "lucide_clipboard" -> states.startActions.getValue("lucide_clipboard").invoke()
        "lucide_clipboard-list" -> states.startActions.getValue("lucide_clipboard-list").invoke()
        "lucide_clipboard-paste" -> states.startActions.getValue("lucide_clipboard-paste").invoke()
        "lucide_clock-alert" -> states.startActions.getValue("lucide_clock-alert").invoke()
        "lucide_clock-arrow-down" -> states.startActions.getValue("lucide_clock-arrow-down").invoke()
        "lucide_clock-arrow-left" -> states.startActions.getValue("lucide_clock-arrow-left").invoke()
        "lucide_clock-arrow-right" -> states.startActions.getValue("lucide_clock-arrow-right").invoke()
        "lucide_clock-arrow-up" -> states.startActions.getValue("lucide_clock-arrow-up").invoke()
        "lucide_clock-check" -> states.startActions.getValue("lucide_clock-check").invoke()
        "lucide_clock-fading" -> states.startActions.getValue("lucide_clock-fading").invoke()
        "lucide_clock" -> states.startActions.getValue("lucide_clock").invoke()
        "lucide_clock-plus" -> states.startActions.getValue("lucide_clock-plus").invoke()
        "lucide_cloud-check" -> states.startActions.getValue("lucide_cloud-check").invoke()
        "lucide_cloud-download" -> states.startActions.getValue("lucide_cloud-download").invoke()
        "lucide_cloud-drizzle" -> states.startActions.getValue("lucide_cloud-drizzle").invoke()
        "lucide_cloud-fog" -> states.startActions.getValue("lucide_cloud-fog").invoke()
        "lucide_cloud-hail" -> states.startActions.getValue("lucide_cloud-hail").invoke()
        "lucide_cloud" -> states.startActions.getValue("lucide_cloud").invoke()
        "lucide_cloud-lightning" -> states.startActions.getValue("lucide_cloud-lightning").invoke()
        "lucide_cloud-moon" -> states.startActions.getValue("lucide_cloud-moon").invoke()
        "lucide_cloud-moon-rain" -> states.startActions.getValue("lucide_cloud-moon-rain").invoke()
        "lucide_cloud-off" -> states.startActions.getValue("lucide_cloud-off").invoke()
        "lucide_cloud-rain" -> states.startActions.getValue("lucide_cloud-rain").invoke()
        "lucide_cloud-rain-wind" -> states.startActions.getValue("lucide_cloud-rain-wind").invoke()
        "lucide_cloud-snow" -> states.startActions.getValue("lucide_cloud-snow").invoke()
        "lucide_cloud-sun" -> states.startActions.getValue("lucide_cloud-sun").invoke()
        "lucide_cloud-sun-rain" -> states.startActions.getValue("lucide_cloud-sun-rain").invoke()
        "lucide_cloud-sync" -> states.startActions.getValue("lucide_cloud-sync").invoke()
        "lucide_cloud-upload" -> states.startActions.getValue("lucide_cloud-upload").invoke()
        "lucide_cloudy" -> states.startActions.getValue("lucide_cloudy").invoke()
        "lucide_code" -> states.startActions.getValue("lucide_code").invoke()
        "lucide_code-xml" -> states.startActions.getValue("lucide_code-xml").invoke()
        "lucide_coffee" -> states.startActions.getValue("lucide_coffee").invoke()
        "lucide_coins" -> states.startActions.getValue("lucide_coins").invoke()
        "lucide_columns-2" -> states.startActions.getValue("lucide_columns-2").invoke()
        "lucide_columns-3" -> states.startActions.getValue("lucide_columns-3").invoke()
        "lucide_columns-4" -> states.startActions.getValue("lucide_columns-4").invoke()
        "lucide_command" -> states.startActions.getValue("lucide_command").invoke()
        "lucide_compass" -> states.startActions.getValue("lucide_compass").invoke()
        "lucide_component" -> states.startActions.getValue("lucide_component").invoke()
        "lucide_contact" -> states.startActions.getValue("lucide_contact").invoke()
        "lucide_contact-round" -> states.startActions.getValue("lucide_contact-round").invoke()
        "lucide_contrast" -> states.startActions.getValue("lucide_contrast").invoke()
        "lucide_cookie" -> states.startActions.getValue("lucide_cookie").invoke()
        "lucide_copy-check" -> states.startActions.getValue("lucide_copy-check").invoke()
        "lucide_copy" -> states.startActions.getValue("lucide_copy").invoke()
        "lucide_copy-plus" -> states.startActions.getValue("lucide_copy-plus").invoke()
        "lucide_corner-down-left" -> states.startActions.getValue("lucide_corner-down-left").invoke()
        "lucide_corner-down-right" -> states.startActions.getValue("lucide_corner-down-right").invoke()
        "lucide_corner-left-down" -> states.startActions.getValue("lucide_corner-left-down").invoke()
        "lucide_corner-left-up" -> states.startActions.getValue("lucide_corner-left-up").invoke()
        "lucide_corner-right-down" -> states.startActions.getValue("lucide_corner-right-down").invoke()
        "lucide_corner-right-up" -> states.startActions.getValue("lucide_corner-right-up").invoke()
        "lucide_corner-up-left" -> states.startActions.getValue("lucide_corner-up-left").invoke()
        "lucide_corner-up-right" -> states.startActions.getValue("lucide_corner-up-right").invoke()
        "lucide_cpu" -> states.startActions.getValue("lucide_cpu").invoke()
        "lucide_credit-card-check" -> states.startActions.getValue("lucide_credit-card-check").invoke()
        "lucide_credit-card" -> states.startActions.getValue("lucide_credit-card").invoke()
        "lucide_credit-card-minus" -> states.startActions.getValue("lucide_credit-card-minus").invoke()
        "lucide_credit-card-plus" -> states.startActions.getValue("lucide_credit-card-plus").invoke()
        "lucide_credit-card-reader" -> states.startActions.getValue("lucide_credit-card-reader").invoke()
        "lucide_credit-card-x" -> states.startActions.getValue("lucide_credit-card-x").invoke()
        "lucide_crop" -> states.startActions.getValue("lucide_crop").invoke()
        "lucide_crown" -> states.startActions.getValue("lucide_crown").invoke()
        "lucide_database-arrow-down" -> states.startActions.getValue("lucide_database-arrow-down").invoke()
        "lucide_database-arrow-up" -> states.startActions.getValue("lucide_database-arrow-up").invoke()
        "lucide_database-backup" -> states.startActions.getValue("lucide_database-backup").invoke()
        "lucide_database-check" -> states.startActions.getValue("lucide_database-check").invoke()
        "lucide_database" -> states.startActions.getValue("lucide_database").invoke()
        "lucide_database-minus" -> states.startActions.getValue("lucide_database-minus").invoke()
        "lucide_database-plus" -> states.startActions.getValue("lucide_database-plus").invoke()
        else -> Unit
    }
}

private fun lucideStartChunk3(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_database-search" -> states.startActions.getValue("lucide_database-search").invoke()
        "lucide_database-x" -> states.startActions.getValue("lucide_database-x").invoke()
        "lucide_database-zap" -> states.startActions.getValue("lucide_database-zap").invoke()
        "lucide_delete" -> states.startActions.getValue("lucide_delete").invoke()
        "lucide_diamond" -> states.startActions.getValue("lucide_diamond").invoke()
        "lucide_diamond-minus" -> states.startActions.getValue("lucide_diamond-minus").invoke()
        "lucide_diamond-percent" -> states.startActions.getValue("lucide_diamond-percent").invoke()
        "lucide_diamond-plus" -> states.startActions.getValue("lucide_diamond-plus").invoke()
        "lucide_dice-5" -> states.startActions.getValue("lucide_dice-5").invoke()
        "lucide_diff" -> states.startActions.getValue("lucide_diff").invoke()
        "lucide_disc-2" -> states.startActions.getValue("lucide_disc-2").invoke()
        "lucide_disc-3" -> states.startActions.getValue("lucide_disc-3").invoke()
        "lucide_disc-album" -> states.startActions.getValue("lucide_disc-album").invoke()
        "lucide_disc" -> states.startActions.getValue("lucide_disc").invoke()
        "lucide_dog" -> states.startActions.getValue("lucide_dog").invoke()
        "lucide_dollar-sign" -> states.startActions.getValue("lucide_dollar-sign").invoke()
        "lucide_door-open" -> states.startActions.getValue("lucide_door-open").invoke()
        "lucide_download" -> states.startActions.getValue("lucide_download").invoke()
        "lucide_droplet" -> states.startActions.getValue("lucide_droplet").invoke()
        "lucide_droplets" -> states.startActions.getValue("lucide_droplets").invoke()
        "lucide_drum" -> states.startActions.getValue("lucide_drum").invoke()
        "lucide_dumbbell" -> states.startActions.getValue("lucide_dumbbell").invoke()
        "lucide_ellipsis" -> states.startActions.getValue("lucide_ellipsis").invoke()
        "lucide_ellipsis-vertical" -> states.startActions.getValue("lucide_ellipsis-vertical").invoke()
        "lucide_eraser" -> states.startActions.getValue("lucide_eraser").invoke()
        "lucide_euro" -> states.startActions.getValue("lucide_euro").invoke()
        "lucide_expand" -> states.startActions.getValue("lucide_expand").invoke()
        "lucide_external-link" -> states.startActions.getValue("lucide_external-link").invoke()
        "lucide_eye-closed" -> states.startActions.getValue("lucide_eye-closed").invoke()
        "lucide_eye" -> states.startActions.getValue("lucide_eye").invoke()
        "lucide_eye-off" -> states.startActions.getValue("lucide_eye-off").invoke()
        "lucide_facebook" -> states.startActions.getValue("lucide_facebook").invoke()
        "lucide_factory" -> states.startActions.getValue("lucide_factory").invoke()
        "lucide_fast-forward" -> states.startActions.getValue("lucide_fast-forward").invoke()
        "lucide_feather" -> states.startActions.getValue("lucide_feather").invoke()
        "lucide_figma" -> states.startActions.getValue("lucide_figma").invoke()
        "lucide_file-archive" -> states.startActions.getValue("lucide_file-archive").invoke()
        "lucide_file-check" -> states.startActions.getValue("lucide_file-check").invoke()
        "lucide_file-clock" -> states.startActions.getValue("lucide_file-clock").invoke()
        "lucide_file-code" -> states.startActions.getValue("lucide_file-code").invoke()
        "lucide_file-down" -> states.startActions.getValue("lucide_file-down").invoke()
        "lucide_file-heart" -> states.startActions.getValue("lucide_file-heart").invoke()
        "lucide_file" -> states.startActions.getValue("lucide_file").invoke()
        "lucide_file-image" -> states.startActions.getValue("lucide_file-image").invoke()
        "lucide_file-key" -> states.startActions.getValue("lucide_file-key").invoke()
        "lucide_file-lock" -> states.startActions.getValue("lucide_file-lock").invoke()
        "lucide_file-minus" -> states.startActions.getValue("lucide_file-minus").invoke()
        "lucide_file-music" -> states.startActions.getValue("lucide_file-music").invoke()
        "lucide_file-pen" -> states.startActions.getValue("lucide_file-pen").invoke()
        "lucide_file-plus" -> states.startActions.getValue("lucide_file-plus").invoke()
        "lucide_file-scan" -> states.startActions.getValue("lucide_file-scan").invoke()
        "lucide_file-search" -> states.startActions.getValue("lucide_file-search").invoke()
        "lucide_file-spreadsheet" -> states.startActions.getValue("lucide_file-spreadsheet").invoke()
        "lucide_file-terminal" -> states.startActions.getValue("lucide_file-terminal").invoke()
        "lucide_file-text" -> states.startActions.getValue("lucide_file-text").invoke()
        "lucide_file-type" -> states.startActions.getValue("lucide_file-type").invoke()
        "lucide_file-up" -> states.startActions.getValue("lucide_file-up").invoke()
        "lucide_file-x" -> states.startActions.getValue("lucide_file-x").invoke()
        "lucide_files" -> states.startActions.getValue("lucide_files").invoke()
        "lucide_film" -> states.startActions.getValue("lucide_film").invoke()
        "lucide_filter" -> states.startActions.getValue("lucide_filter").invoke()
        "lucide_fingerprint-pattern" -> states.startActions.getValue("lucide_fingerprint-pattern").invoke()
        "lucide_fish" -> states.startActions.getValue("lucide_fish").invoke()
        "lucide_flag" -> states.startActions.getValue("lucide_flag").invoke()
        "lucide_flag-off" -> states.startActions.getValue("lucide_flag-off").invoke()
        "lucide_flag-triangle-right" -> states.startActions.getValue("lucide_flag-triangle-right").invoke()
        "lucide_flame" -> states.startActions.getValue("lucide_flame").invoke()
        "lucide_flask-conical" -> states.startActions.getValue("lucide_flask-conical").invoke()
        "lucide_fold-vertical" -> states.startActions.getValue("lucide_fold-vertical").invoke()
        "lucide_folder-check" -> states.startActions.getValue("lucide_folder-check").invoke()
        "lucide_folder-closed" -> states.startActions.getValue("lucide_folder-closed").invoke()
        "lucide_folder-dot" -> states.startActions.getValue("lucide_folder-dot").invoke()
        "lucide_folder-heart" -> states.startActions.getValue("lucide_folder-heart").invoke()
        "lucide_folder" -> states.startActions.getValue("lucide_folder").invoke()
        "lucide_folder-key" -> states.startActions.getValue("lucide_folder-key").invoke()
        "lucide_folder-lock" -> states.startActions.getValue("lucide_folder-lock").invoke()
        "lucide_folder-minus" -> states.startActions.getValue("lucide_folder-minus").invoke()
        "lucide_folder-open" -> states.startActions.getValue("lucide_folder-open").invoke()
        "lucide_folder-plus" -> states.startActions.getValue("lucide_folder-plus").invoke()
        "lucide_folder-search" -> states.startActions.getValue("lucide_folder-search").invoke()
        "lucide_folder-tree" -> states.startActions.getValue("lucide_folder-tree").invoke()
        "lucide_folder-x" -> states.startActions.getValue("lucide_folder-x").invoke()
        "lucide_footprints" -> states.startActions.getValue("lucide_footprints").invoke()
        "lucide_framer" -> states.startActions.getValue("lucide_framer").invoke()
        "lucide_fullscreen" -> states.startActions.getValue("lucide_fullscreen").invoke()
        "lucide_funnel-x" -> states.startActions.getValue("lucide_funnel-x").invoke()
        "lucide_gallery-horizontal" -> states.startActions.getValue("lucide_gallery-horizontal").invoke()
        "lucide_gamepad" -> states.startActions.getValue("lucide_gamepad").invoke()
        "lucide_gauge" -> states.startActions.getValue("lucide_gauge").invoke()
        "lucide_gem" -> states.startActions.getValue("lucide_gem").invoke()
        "lucide_georgian-lari" -> states.startActions.getValue("lucide_georgian-lari").invoke()
        "lucide_ghost" -> states.startActions.getValue("lucide_ghost").invoke()
        "lucide_gift" -> states.startActions.getValue("lucide_gift").invoke()
        "lucide_git-branch" -> states.startActions.getValue("lucide_git-branch").invoke()
        "lucide_git-branch-minus" -> states.startActions.getValue("lucide_git-branch-minus").invoke()
        "lucide_git-branch-plus" -> states.startActions.getValue("lucide_git-branch-plus").invoke()
        "lucide_git-commit-horizontal" -> states.startActions.getValue("lucide_git-commit-horizontal").invoke()
        "lucide_git-commit-vertical" -> states.startActions.getValue("lucide_git-commit-vertical").invoke()
        "lucide_git-compare-arrows" -> states.startActions.getValue("lucide_git-compare-arrows").invoke()
        "lucide_git-compare" -> states.startActions.getValue("lucide_git-compare").invoke()
        else -> Unit
    }
}

private fun lucideStartChunk4(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_git-fork" -> states.startActions.getValue("lucide_git-fork").invoke()
        "lucide_git-graph" -> states.startActions.getValue("lucide_git-graph").invoke()
        "lucide_git-merge-conflict" -> states.startActions.getValue("lucide_git-merge-conflict").invoke()
        "lucide_git-merge" -> states.startActions.getValue("lucide_git-merge").invoke()
        "lucide_git-pull-request-arrow" -> states.startActions.getValue("lucide_git-pull-request-arrow").invoke()
        "lucide_git-pull-request-closed" -> states.startActions.getValue("lucide_git-pull-request-closed").invoke()
        "lucide_git-pull-request-create-arrow" -> states.startActions.getValue("lucide_git-pull-request-create-arrow").invoke()
        "lucide_git-pull-request-create" -> states.startActions.getValue("lucide_git-pull-request-create").invoke()
        "lucide_git-pull-request-draft" -> states.startActions.getValue("lucide_git-pull-request-draft").invoke()
        "lucide_git-pull-request" -> states.startActions.getValue("lucide_git-pull-request").invoke()
        "lucide_github" -> states.startActions.getValue("lucide_github").invoke()
        "lucide_gitlab" -> states.startActions.getValue("lucide_gitlab").invoke()
        "lucide_globe" -> states.startActions.getValue("lucide_globe").invoke()
        "lucide_globe-lock" -> states.startActions.getValue("lucide_globe-lock").invoke()
        "lucide_graduation-cap" -> states.startActions.getValue("lucide_graduation-cap").invoke()
        "lucide_grid-2x2-check" -> states.startActions.getValue("lucide_grid-2x2-check").invoke()
        "lucide_grid-2x2" -> states.startActions.getValue("lucide_grid-2x2").invoke()
        "lucide_grid-2x2-plus" -> states.startActions.getValue("lucide_grid-2x2-plus").invoke()
        "lucide_grid-2x2-x" -> states.startActions.getValue("lucide_grid-2x2-x").invoke()
        "lucide_grid-3x2" -> states.startActions.getValue("lucide_grid-3x2").invoke()
        "lucide_grid-3x3" -> states.startActions.getValue("lucide_grid-3x3").invoke()
        "lucide_grip-vertical" -> states.startActions.getValue("lucide_grip-vertical").invoke()
        "lucide_guitar" -> states.startActions.getValue("lucide_guitar").invoke()
        "lucide_hammer" -> states.startActions.getValue("lucide_hammer").invoke()
        "lucide_hand-coins" -> states.startActions.getValue("lucide_hand-coins").invoke()
        "lucide_hand-heart" -> states.startActions.getValue("lucide_hand-heart").invoke()
        "lucide_hand" -> states.startActions.getValue("lucide_hand").invoke()
        "lucide_handshake" -> states.startActions.getValue("lucide_handshake").invoke()
        "lucide_hard-drive-download" -> states.startActions.getValue("lucide_hard-drive-download").invoke()
        "lucide_hard-drive" -> states.startActions.getValue("lucide_hard-drive").invoke()
        "lucide_hard-drive-upload" -> states.startActions.getValue("lucide_hard-drive-upload").invoke()
        "lucide_hash" -> states.startActions.getValue("lucide_hash").invoke()
        "lucide_haze" -> states.startActions.getValue("lucide_haze").invoke()
        "lucide_heading-1" -> states.startActions.getValue("lucide_heading-1").invoke()
        "lucide_heading-2" -> states.startActions.getValue("lucide_heading-2").invoke()
        "lucide_heading-3" -> states.startActions.getValue("lucide_heading-3").invoke()
        "lucide_heading-4" -> states.startActions.getValue("lucide_heading-4").invoke()
        "lucide_heading-5" -> states.startActions.getValue("lucide_heading-5").invoke()
        "lucide_heading-6" -> states.startActions.getValue("lucide_heading-6").invoke()
        "lucide_heading" -> states.startActions.getValue("lucide_heading").invoke()
        "lucide_headphone-off" -> states.startActions.getValue("lucide_headphone-off").invoke()
        "lucide_headphones" -> states.startActions.getValue("lucide_headphones").invoke()
        "lucide_headset" -> states.startActions.getValue("lucide_headset").invoke()
        "lucide_heart-crack" -> states.startActions.getValue("lucide_heart-crack").invoke()
        "lucide_heart-handshake" -> states.startActions.getValue("lucide_heart-handshake").invoke()
        "lucide_heart" -> states.startActions.getValue("lucide_heart").invoke()
        "lucide_heart-minus" -> states.startActions.getValue("lucide_heart-minus").invoke()
        "lucide_heart-off" -> states.startActions.getValue("lucide_heart-off").invoke()
        "lucide_heart-plus" -> states.startActions.getValue("lucide_heart-plus").invoke()
        "lucide_heart-pulse" -> states.startActions.getValue("lucide_heart-pulse").invoke()
        "lucide_heart-x" -> states.startActions.getValue("lucide_heart-x").invoke()
        "lucide_hexagon" -> states.startActions.getValue("lucide_hexagon").invoke()
        "lucide_highlighter" -> states.startActions.getValue("lucide_highlighter").invoke()
        "lucide_history" -> states.startActions.getValue("lucide_history").invoke()
        "lucide_hospital" -> states.startActions.getValue("lucide_hospital").invoke()
        "lucide_hotel" -> states.startActions.getValue("lucide_hotel").invoke()
        "lucide_hourglass" -> states.startActions.getValue("lucide_hourglass").invoke()
        "lucide_house-cog" -> states.startActions.getValue("lucide_house-cog").invoke()
        "lucide_house-heart" -> states.startActions.getValue("lucide_house-heart").invoke()
        "lucide_house" -> states.startActions.getValue("lucide_house").invoke()
        "lucide_house-plug" -> states.startActions.getValue("lucide_house-plug").invoke()
        "lucide_house-plus" -> states.startActions.getValue("lucide_house-plus").invoke()
        "lucide_house-wifi" -> states.startActions.getValue("lucide_house-wifi").invoke()
        "lucide_image" -> states.startActions.getValue("lucide_image").invoke()
        "lucide_image-off" -> states.startActions.getValue("lucide_image-off").invoke()
        "lucide_image-plus" -> states.startActions.getValue("lucide_image-plus").invoke()
        "lucide_inbox" -> states.startActions.getValue("lucide_inbox").invoke()
        "lucide_indent-decrease" -> states.startActions.getValue("lucide_indent-decrease").invoke()
        "lucide_indent-increase" -> states.startActions.getValue("lucide_indent-increase").invoke()
        "lucide_indian-rupee" -> states.startActions.getValue("lucide_indian-rupee").invoke()
        "lucide_infinity" -> states.startActions.getValue("lucide_infinity").invoke()
        "lucide_info" -> states.startActions.getValue("lucide_info").invoke()
        "lucide_instagram" -> states.startActions.getValue("lucide_instagram").invoke()
        "lucide_italic" -> states.startActions.getValue("lucide_italic").invoke()
        "lucide_japanese-yen" -> states.startActions.getValue("lucide_japanese-yen").invoke()
        "lucide_kanban" -> states.startActions.getValue("lucide_kanban").invoke()
        "lucide_key" -> states.startActions.getValue("lucide_key").invoke()
        "lucide_key-round" -> states.startActions.getValue("lucide_key-round").invoke()
        "lucide_key-square" -> states.startActions.getValue("lucide_key-square").invoke()
        "lucide_keyboard" -> states.startActions.getValue("lucide_keyboard").invoke()
        "lucide_landmark" -> states.startActions.getValue("lucide_landmark").invoke()
        "lucide_languages" -> states.startActions.getValue("lucide_languages").invoke()
        "lucide_laptop" -> states.startActions.getValue("lucide_laptop").invoke()
        "lucide_laptop-minimal-check" -> states.startActions.getValue("lucide_laptop-minimal-check").invoke()
        "lucide_laptop-minimal" -> states.startActions.getValue("lucide_laptop-minimal").invoke()
        "lucide_layers" -> states.startActions.getValue("lucide_layers").invoke()
        "lucide_layout-dashboard" -> states.startActions.getValue("lucide_layout-dashboard").invoke()
        "lucide_layout-grid" -> states.startActions.getValue("lucide_layout-grid").invoke()
        "lucide_layout-list" -> states.startActions.getValue("lucide_layout-list").invoke()
        "lucide_layout-panel-left" -> states.startActions.getValue("lucide_layout-panel-left").invoke()
        "lucide_layout-panel-top" -> states.startActions.getValue("lucide_layout-panel-top").invoke()
        "lucide_layout-template" -> states.startActions.getValue("lucide_layout-template").invoke()
        "lucide_leaf" -> states.startActions.getValue("lucide_leaf").invoke()
        "lucide_life-buoy" -> states.startActions.getValue("lucide_life-buoy").invoke()
        "lucide_lightbulb" -> states.startActions.getValue("lucide_lightbulb").invoke()
        "lucide_link" -> states.startActions.getValue("lucide_link").invoke()
        "lucide_linkedin" -> states.startActions.getValue("lucide_linkedin").invoke()
        "lucide_list-check" -> states.startActions.getValue("lucide_list-check").invoke()
        "lucide_list-checks" -> states.startActions.getValue("lucide_list-checks").invoke()
        "lucide_list-chevrons-down-up" -> states.startActions.getValue("lucide_list-chevrons-down-up").invoke()
        else -> Unit
    }
}

private fun lucideStartChunk5(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_list-chevrons-up-down" -> states.startActions.getValue("lucide_list-chevrons-up-down").invoke()
        "lucide_list-collapse" -> states.startActions.getValue("lucide_list-collapse").invoke()
        "lucide_list-end" -> states.startActions.getValue("lucide_list-end").invoke()
        "lucide_list-filter" -> states.startActions.getValue("lucide_list-filter").invoke()
        "lucide_list-filter-plus" -> states.startActions.getValue("lucide_list-filter-plus").invoke()
        "lucide_list" -> states.startActions.getValue("lucide_list").invoke()
        "lucide_list-minus" -> states.startActions.getValue("lucide_list-minus").invoke()
        "lucide_list-ordered" -> states.startActions.getValue("lucide_list-ordered").invoke()
        "lucide_list-plus" -> states.startActions.getValue("lucide_list-plus").invoke()
        "lucide_list-start" -> states.startActions.getValue("lucide_list-start").invoke()
        "lucide_list-todo" -> states.startActions.getValue("lucide_list-todo").invoke()
        "lucide_list-tree" -> states.startActions.getValue("lucide_list-tree").invoke()
        "lucide_list-x" -> states.startActions.getValue("lucide_list-x").invoke()
        "lucide_loader-circle" -> states.startActions.getValue("lucide_loader-circle").invoke()
        "lucide_loader" -> states.startActions.getValue("lucide_loader").invoke()
        "lucide_locate-fixed" -> states.startActions.getValue("lucide_locate-fixed").invoke()
        "lucide_locate" -> states.startActions.getValue("lucide_locate").invoke()
        "lucide_locate-off" -> states.startActions.getValue("lucide_locate-off").invoke()
        "lucide_lock" -> states.startActions.getValue("lucide_lock").invoke()
        "lucide_lock-keyhole" -> states.startActions.getValue("lucide_lock-keyhole").invoke()
        "lucide_lock-keyhole-open" -> states.startActions.getValue("lucide_lock-keyhole-open").invoke()
        "lucide_lock-open" -> states.startActions.getValue("lucide_lock-open").invoke()
        "lucide_log-in" -> states.startActions.getValue("lucide_log-in").invoke()
        "lucide_log-out" -> states.startActions.getValue("lucide_log-out").invoke()
        "lucide_magnet" -> states.startActions.getValue("lucide_magnet").invoke()
        "lucide_mail-check" -> states.startActions.getValue("lucide_mail-check").invoke()
        "lucide_mail-clock" -> states.startActions.getValue("lucide_mail-clock").invoke()
        "lucide_mail" -> states.startActions.getValue("lucide_mail").invoke()
        "lucide_mail-minus" -> states.startActions.getValue("lucide_mail-minus").invoke()
        "lucide_mail-open" -> states.startActions.getValue("lucide_mail-open").invoke()
        "lucide_mail-plus" -> states.startActions.getValue("lucide_mail-plus").invoke()
        "lucide_mail-search" -> states.startActions.getValue("lucide_mail-search").invoke()
        "lucide_mail-warning" -> states.startActions.getValue("lucide_mail-warning").invoke()
        "lucide_mail-x" -> states.startActions.getValue("lucide_mail-x").invoke()
        "lucide_mailbox" -> states.startActions.getValue("lucide_mailbox").invoke()
        "lucide_mails" -> states.startActions.getValue("lucide_mails").invoke()
        "lucide_map" -> states.startActions.getValue("lucide_map").invoke()
        "lucide_map-minus" -> states.startActions.getValue("lucide_map-minus").invoke()
        "lucide_map-pin-check" -> states.startActions.getValue("lucide_map-pin-check").invoke()
        "lucide_map-pin-check-inside" -> states.startActions.getValue("lucide_map-pin-check-inside").invoke()
        "lucide_map-pin-house" -> states.startActions.getValue("lucide_map-pin-house").invoke()
        "lucide_map-pin" -> states.startActions.getValue("lucide_map-pin").invoke()
        "lucide_map-pin-minus" -> states.startActions.getValue("lucide_map-pin-minus").invoke()
        "lucide_map-pin-minus-inside" -> states.startActions.getValue("lucide_map-pin-minus-inside").invoke()
        "lucide_map-pin-off" -> states.startActions.getValue("lucide_map-pin-off").invoke()
        "lucide_map-pin-pen" -> states.startActions.getValue("lucide_map-pin-pen").invoke()
        "lucide_map-pin-plus" -> states.startActions.getValue("lucide_map-pin-plus").invoke()
        "lucide_map-pin-plus-inside" -> states.startActions.getValue("lucide_map-pin-plus-inside").invoke()
        "lucide_map-pin-search" -> states.startActions.getValue("lucide_map-pin-search").invoke()
        "lucide_map-pin-x" -> states.startActions.getValue("lucide_map-pin-x").invoke()
        "lucide_map-pin-x-inside" -> states.startActions.getValue("lucide_map-pin-x-inside").invoke()
        "lucide_map-pinned" -> states.startActions.getValue("lucide_map-pinned").invoke()
        "lucide_map-plus" -> states.startActions.getValue("lucide_map-plus").invoke()
        "lucide_mars" -> states.startActions.getValue("lucide_mars").invoke()
        "lucide_maximize-2" -> states.startActions.getValue("lucide_maximize-2").invoke()
        "lucide_maximize" -> states.startActions.getValue("lucide_maximize").invoke()
        "lucide_medal" -> states.startActions.getValue("lucide_medal").invoke()
        "lucide_megaphone" -> states.startActions.getValue("lucide_megaphone").invoke()
        "lucide_memory-stick" -> states.startActions.getValue("lucide_memory-stick").invoke()
        "lucide_menu" -> states.startActions.getValue("lucide_menu").invoke()
        "lucide_message-circle-check" -> states.startActions.getValue("lucide_message-circle-check").invoke()
        "lucide_message-circle-code" -> states.startActions.getValue("lucide_message-circle-code").invoke()
        "lucide_message-circle-dashed-check" -> states.startActions.getValue("lucide_message-circle-dashed-check").invoke()
        "lucide_message-circle-dashed" -> states.startActions.getValue("lucide_message-circle-dashed").invoke()
        "lucide_message-circle-heart" -> states.startActions.getValue("lucide_message-circle-heart").invoke()
        "lucide_message-circle" -> states.startActions.getValue("lucide_message-circle").invoke()
        "lucide_message-circle-more" -> states.startActions.getValue("lucide_message-circle-more").invoke()
        "lucide_message-circle-off" -> states.startActions.getValue("lucide_message-circle-off").invoke()
        "lucide_message-circle-plus" -> states.startActions.getValue("lucide_message-circle-plus").invoke()
        "lucide_message-circle-question-mark" -> states.startActions.getValue("lucide_message-circle-question-mark").invoke()
        "lucide_message-circle-reply" -> states.startActions.getValue("lucide_message-circle-reply").invoke()
        "lucide_message-circle-warning" -> states.startActions.getValue("lucide_message-circle-warning").invoke()
        "lucide_message-circle-x" -> states.startActions.getValue("lucide_message-circle-x").invoke()
        "lucide_message-square-check" -> states.startActions.getValue("lucide_message-square-check").invoke()
        "lucide_message-square-code" -> states.startActions.getValue("lucide_message-square-code").invoke()
        "lucide_message-square-dashed" -> states.startActions.getValue("lucide_message-square-dashed").invoke()
        "lucide_message-square-diff" -> states.startActions.getValue("lucide_message-square-diff").invoke()
        "lucide_message-square-dot" -> states.startActions.getValue("lucide_message-square-dot").invoke()
        "lucide_message-square-heart" -> states.startActions.getValue("lucide_message-square-heart").invoke()
        "lucide_message-square" -> states.startActions.getValue("lucide_message-square").invoke()
        "lucide_message-square-lock" -> states.startActions.getValue("lucide_message-square-lock").invoke()
        "lucide_message-square-more" -> states.startActions.getValue("lucide_message-square-more").invoke()
        "lucide_message-square-off" -> states.startActions.getValue("lucide_message-square-off").invoke()
        "lucide_message-square-plus" -> states.startActions.getValue("lucide_message-square-plus").invoke()
        "lucide_message-square-quote" -> states.startActions.getValue("lucide_message-square-quote").invoke()
        "lucide_message-square-reply" -> states.startActions.getValue("lucide_message-square-reply").invoke()
        "lucide_message-square-share" -> states.startActions.getValue("lucide_message-square-share").invoke()
        "lucide_message-square-text" -> states.startActions.getValue("lucide_message-square-text").invoke()
        "lucide_message-square-warning" -> states.startActions.getValue("lucide_message-square-warning").invoke()
        "lucide_message-square-x" -> states.startActions.getValue("lucide_message-square-x").invoke()
        "lucide_messages-circle" -> states.startActions.getValue("lucide_messages-circle").invoke()
        "lucide_messages-square" -> states.startActions.getValue("lucide_messages-square").invoke()
        "lucide_mic-audio-lines" -> states.startActions.getValue("lucide_mic-audio-lines").invoke()
        "lucide_mic" -> states.startActions.getValue("lucide_mic").invoke()
        "lucide_mic-off" -> states.startActions.getValue("lucide_mic-off").invoke()
        "lucide_mic-signal" -> states.startActions.getValue("lucide_mic-signal").invoke()
        "lucide_mic-vocal" -> states.startActions.getValue("lucide_mic-vocal").invoke()
        "lucide_microchip" -> states.startActions.getValue("lucide_microchip").invoke()
        "lucide_milestone" -> states.startActions.getValue("lucide_milestone").invoke()
        "lucide_minimize-2" -> states.startActions.getValue("lucide_minimize-2").invoke()
        else -> Unit
    }
}

private fun lucideStartChunk6(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_minimize" -> states.startActions.getValue("lucide_minimize").invoke()
        "lucide_minus" -> states.startActions.getValue("lucide_minus").invoke()
        "lucide_monitor-check" -> states.startActions.getValue("lucide_monitor-check").invoke()
        "lucide_monitor-cloud" -> states.startActions.getValue("lucide_monitor-cloud").invoke()
        "lucide_monitor-cog" -> states.startActions.getValue("lucide_monitor-cog").invoke()
        "lucide_monitor-dot" -> states.startActions.getValue("lucide_monitor-dot").invoke()
        "lucide_monitor-down" -> states.startActions.getValue("lucide_monitor-down").invoke()
        "lucide_monitor" -> states.startActions.getValue("lucide_monitor").invoke()
        "lucide_monitor-off" -> states.startActions.getValue("lucide_monitor-off").invoke()
        "lucide_monitor-pause" -> states.startActions.getValue("lucide_monitor-pause").invoke()
        "lucide_monitor-pc" -> states.startActions.getValue("lucide_monitor-pc").invoke()
        "lucide_monitor-play" -> states.startActions.getValue("lucide_monitor-play").invoke()
        "lucide_monitor-smartphone" -> states.startActions.getValue("lucide_monitor-smartphone").invoke()
        "lucide_monitor-speaker" -> states.startActions.getValue("lucide_monitor-speaker").invoke()
        "lucide_monitor-stop" -> states.startActions.getValue("lucide_monitor-stop").invoke()
        "lucide_monitor-up" -> states.startActions.getValue("lucide_monitor-up").invoke()
        "lucide_monitor-x" -> states.startActions.getValue("lucide_monitor-x").invoke()
        "lucide_moon" -> states.startActions.getValue("lucide_moon").invoke()
        "lucide_moon-star" -> states.startActions.getValue("lucide_moon-star").invoke()
        "lucide_mountain" -> states.startActions.getValue("lucide_mountain").invoke()
        "lucide_mouse" -> states.startActions.getValue("lucide_mouse").invoke()
        "lucide_mouse-pointer-click" -> states.startActions.getValue("lucide_mouse-pointer-click").invoke()
        "lucide_mouse-pointer" -> states.startActions.getValue("lucide_mouse-pointer").invoke()
        "lucide_move-diagonal-2" -> states.startActions.getValue("lucide_move-diagonal-2").invoke()
        "lucide_move-diagonal" -> states.startActions.getValue("lucide_move-diagonal").invoke()
        "lucide_move-down" -> states.startActions.getValue("lucide_move-down").invoke()
        "lucide_move-down-left" -> states.startActions.getValue("lucide_move-down-left").invoke()
        "lucide_move-down-right" -> states.startActions.getValue("lucide_move-down-right").invoke()
        "lucide_move-horizontal" -> states.startActions.getValue("lucide_move-horizontal").invoke()
        "lucide_move" -> states.startActions.getValue("lucide_move").invoke()
        "lucide_move-left" -> states.startActions.getValue("lucide_move-left").invoke()
        "lucide_move-right" -> states.startActions.getValue("lucide_move-right").invoke()
        "lucide_move-up" -> states.startActions.getValue("lucide_move-up").invoke()
        "lucide_move-up-left" -> states.startActions.getValue("lucide_move-up-left").invoke()
        "lucide_move-up-right" -> states.startActions.getValue("lucide_move-up-right").invoke()
        "lucide_move-vertical" -> states.startActions.getValue("lucide_move-vertical").invoke()
        "lucide_music-2" -> states.startActions.getValue("lucide_music-2").invoke()
        "lucide_music-3" -> states.startActions.getValue("lucide_music-3").invoke()
        "lucide_music-4" -> states.startActions.getValue("lucide_music-4").invoke()
        "lucide_music" -> states.startActions.getValue("lucide_music").invoke()
        "lucide_navigation-2" -> states.startActions.getValue("lucide_navigation-2").invoke()
        "lucide_navigation" -> states.startActions.getValue("lucide_navigation").invoke()
        "lucide_network" -> states.startActions.getValue("lucide_network").invoke()
        "lucide_nfc" -> states.startActions.getValue("lucide_nfc").invoke()
        "lucide_notebook-pen" -> states.startActions.getValue("lucide_notebook-pen").invoke()
        "lucide_octagon-alert" -> states.startActions.getValue("lucide_octagon-alert").invoke()
        "lucide_octagon" -> states.startActions.getValue("lucide_octagon").invoke()
        "lucide_octagon-minus" -> states.startActions.getValue("lucide_octagon-minus").invoke()
        "lucide_octagon-pause" -> states.startActions.getValue("lucide_octagon-pause").invoke()
        "lucide_octagon-x" -> states.startActions.getValue("lucide_octagon-x").invoke()
        "lucide_orbit" -> states.startActions.getValue("lucide_orbit").invoke()
        "lucide_package-check" -> states.startActions.getValue("lucide_package-check").invoke()
        "lucide_package" -> states.startActions.getValue("lucide_package").invoke()
        "lucide_package-open" -> states.startActions.getValue("lucide_package-open").invoke()
        "lucide_package-plus" -> states.startActions.getValue("lucide_package-plus").invoke()
        "lucide_package-search" -> states.startActions.getValue("lucide_package-search").invoke()
        "lucide_package-x" -> states.startActions.getValue("lucide_package-x").invoke()
        "lucide_paint-bucket" -> states.startActions.getValue("lucide_paint-bucket").invoke()
        "lucide_paintbrush" -> states.startActions.getValue("lucide_paintbrush").invoke()
        "lucide_palette" -> states.startActions.getValue("lucide_palette").invoke()
        "lucide_panel-bottom-close" -> states.startActions.getValue("lucide_panel-bottom-close").invoke()
        "lucide_panel-bottom-dashed" -> states.startActions.getValue("lucide_panel-bottom-dashed").invoke()
        "lucide_panel-bottom" -> states.startActions.getValue("lucide_panel-bottom").invoke()
        "lucide_panel-bottom-open" -> states.startActions.getValue("lucide_panel-bottom-open").invoke()
        "lucide_panel-left-close" -> states.startActions.getValue("lucide_panel-left-close").invoke()
        "lucide_panel-left-dashed" -> states.startActions.getValue("lucide_panel-left-dashed").invoke()
        "lucide_panel-left" -> states.startActions.getValue("lucide_panel-left").invoke()
        "lucide_panel-left-open" -> states.startActions.getValue("lucide_panel-left-open").invoke()
        "lucide_panel-left-right-dashed" -> states.startActions.getValue("lucide_panel-left-right-dashed").invoke()
        "lucide_panel-right-close" -> states.startActions.getValue("lucide_panel-right-close").invoke()
        "lucide_panel-right-dashed" -> states.startActions.getValue("lucide_panel-right-dashed").invoke()
        "lucide_panel-right" -> states.startActions.getValue("lucide_panel-right").invoke()
        "lucide_panel-right-open" -> states.startActions.getValue("lucide_panel-right-open").invoke()
        "lucide_panel-top-bottom-dashed" -> states.startActions.getValue("lucide_panel-top-bottom-dashed").invoke()
        "lucide_panel-top-close" -> states.startActions.getValue("lucide_panel-top-close").invoke()
        "lucide_panel-top-dashed" -> states.startActions.getValue("lucide_panel-top-dashed").invoke()
        "lucide_panel-top" -> states.startActions.getValue("lucide_panel-top").invoke()
        "lucide_panel-top-open" -> states.startActions.getValue("lucide_panel-top-open").invoke()
        "lucide_paperclip" -> states.startActions.getValue("lucide_paperclip").invoke()
        "lucide_party-popper" -> states.startActions.getValue("lucide_party-popper").invoke()
        "lucide_pause" -> states.startActions.getValue("lucide_pause").invoke()
        "lucide_paw-print" -> states.startActions.getValue("lucide_paw-print").invoke()
        "lucide_pen-tool" -> states.startActions.getValue("lucide_pen-tool").invoke()
        "lucide_pencil" -> states.startActions.getValue("lucide_pencil").invoke()
        "lucide_pentagon" -> states.startActions.getValue("lucide_pentagon").invoke()
        "lucide_percent" -> states.startActions.getValue("lucide_percent").invoke()
        "lucide_philippine-peso" -> states.startActions.getValue("lucide_philippine-peso").invoke()
        "lucide_phone-call" -> states.startActions.getValue("lucide_phone-call").invoke()
        "lucide_phone-forwarded" -> states.startActions.getValue("lucide_phone-forwarded").invoke()
        "lucide_phone" -> states.startActions.getValue("lucide_phone").invoke()
        "lucide_phone-incoming" -> states.startActions.getValue("lucide_phone-incoming").invoke()
        "lucide_phone-missed" -> states.startActions.getValue("lucide_phone-missed").invoke()
        "lucide_phone-off" -> states.startActions.getValue("lucide_phone-off").invoke()
        "lucide_piano" -> states.startActions.getValue("lucide_piano").invoke()
        "lucide_picture-in-picture" -> states.startActions.getValue("lucide_picture-in-picture").invoke()
        "lucide_piggy-bank" -> states.startActions.getValue("lucide_piggy-bank").invoke()
        "lucide_pilcrow" -> states.startActions.getValue("lucide_pilcrow").invoke()
        "lucide_pilcrow-left" -> states.startActions.getValue("lucide_pilcrow-left").invoke()
        "lucide_pilcrow-right" -> states.startActions.getValue("lucide_pilcrow-right").invoke()
        "lucide_pill" -> states.startActions.getValue("lucide_pill").invoke()
        else -> Unit
    }
}

private fun lucideStartChunk7(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_pin" -> states.startActions.getValue("lucide_pin").invoke()
        "lucide_pin-off" -> states.startActions.getValue("lucide_pin-off").invoke()
        "lucide_pipette" -> states.startActions.getValue("lucide_pipette").invoke()
        "lucide_plane" -> states.startActions.getValue("lucide_plane").invoke()
        "lucide_play" -> states.startActions.getValue("lucide_play").invoke()
        "lucide_play-off" -> states.startActions.getValue("lucide_play-off").invoke()
        "lucide_plug-2" -> states.startActions.getValue("lucide_plug-2").invoke()
        "lucide_plug" -> states.startActions.getValue("lucide_plug").invoke()
        "lucide_plug-zap" -> states.startActions.getValue("lucide_plug-zap").invoke()
        "lucide_plus" -> states.startActions.getValue("lucide_plus").invoke()
        "lucide_podcast" -> states.startActions.getValue("lucide_podcast").invoke()
        "lucide_pound-sterling" -> states.startActions.getValue("lucide_pound-sterling").invoke()
        "lucide_power" -> states.startActions.getValue("lucide_power").invoke()
        "lucide_power-off" -> states.startActions.getValue("lucide_power-off").invoke()
        "lucide_presentation" -> states.startActions.getValue("lucide_presentation").invoke()
        "lucide_printer" -> states.startActions.getValue("lucide_printer").invoke()
        "lucide_puzzle" -> states.startActions.getValue("lucide_puzzle").invoke()
        "lucide_qr-code" -> states.startActions.getValue("lucide_qr-code").invoke()
        "lucide_quote" -> states.startActions.getValue("lucide_quote").invoke()
        "lucide_rabbit" -> states.startActions.getValue("lucide_rabbit").invoke()
        "lucide_radar" -> states.startActions.getValue("lucide_radar").invoke()
        "lucide_radio" -> states.startActions.getValue("lucide_radio").invoke()
        "lucide_radio-off" -> states.startActions.getValue("lucide_radio-off").invoke()
        "lucide_radio-receiver" -> states.startActions.getValue("lucide_radio-receiver").invoke()
        "lucide_rainbow" -> states.startActions.getValue("lucide_rainbow").invoke()
        "lucide_receipt" -> states.startActions.getValue("lucide_receipt").invoke()
        "lucide_receipt-text" -> states.startActions.getValue("lucide_receipt-text").invoke()
        "lucide_recycle" -> states.startActions.getValue("lucide_recycle").invoke()
        "lucide_redo" -> states.startActions.getValue("lucide_redo").invoke()
        "lucide_refresh-ccw-dot" -> states.startActions.getValue("lucide_refresh-ccw-dot").invoke()
        "lucide_refresh-ccw" -> states.startActions.getValue("lucide_refresh-ccw").invoke()
        "lucide_refresh-cw" -> states.startActions.getValue("lucide_refresh-cw").invoke()
        "lucide_refresh-cw-off" -> states.startActions.getValue("lucide_refresh-cw-off").invoke()
        "lucide_regex" -> states.startActions.getValue("lucide_regex").invoke()
        "lucide_remove-formatting" -> states.startActions.getValue("lucide_remove-formatting").invoke()
        "lucide_repeat-1" -> states.startActions.getValue("lucide_repeat-1").invoke()
        "lucide_repeat-2" -> states.startActions.getValue("lucide_repeat-2").invoke()
        "lucide_repeat" -> states.startActions.getValue("lucide_repeat").invoke()
        "lucide_reply-all" -> states.startActions.getValue("lucide_reply-all").invoke()
        "lucide_reply" -> states.startActions.getValue("lucide_reply").invoke()
        "lucide_rewind" -> states.startActions.getValue("lucide_rewind").invoke()
        "lucide_rocket" -> states.startActions.getValue("lucide_rocket").invoke()
        "lucide_rotate-ccw" -> states.startActions.getValue("lucide_rotate-ccw").invoke()
        "lucide_rotate-cw" -> states.startActions.getValue("lucide_rotate-cw").invoke()
        "lucide_route" -> states.startActions.getValue("lucide_route").invoke()
        "lucide_route-off" -> states.startActions.getValue("lucide_route-off").invoke()
        "lucide_router" -> states.startActions.getValue("lucide_router").invoke()
        "lucide_rows-2" -> states.startActions.getValue("lucide_rows-2").invoke()
        "lucide_rows-3" -> states.startActions.getValue("lucide_rows-3").invoke()
        "lucide_rows-4" -> states.startActions.getValue("lucide_rows-4").invoke()
        "lucide_rss" -> states.startActions.getValue("lucide_rss").invoke()
        "lucide_ruler" -> states.startActions.getValue("lucide_ruler").invoke()
        "lucide_russian-ruble" -> states.startActions.getValue("lucide_russian-ruble").invoke()
        "lucide_saudi-riyal" -> states.startActions.getValue("lucide_saudi-riyal").invoke()
        "lucide_save" -> states.startActions.getValue("lucide_save").invoke()
        "lucide_scan" -> states.startActions.getValue("lucide_scan").invoke()
        "lucide_scan-line" -> states.startActions.getValue("lucide_scan-line").invoke()
        "lucide_scan-qr-code" -> states.startActions.getValue("lucide_scan-qr-code").invoke()
        "lucide_school" -> states.startActions.getValue("lucide_school").invoke()
        "lucide_scissors" -> states.startActions.getValue("lucide_scissors").invoke()
        "lucide_screen-share" -> states.startActions.getValue("lucide_screen-share").invoke()
        "lucide_scroll-text" -> states.startActions.getValue("lucide_scroll-text").invoke()
        "lucide_search" -> states.startActions.getValue("lucide_search").invoke()
        "lucide_search-x" -> states.startActions.getValue("lucide_search-x").invoke()
        "lucide_send-horizontal" -> states.startActions.getValue("lucide_send-horizontal").invoke()
        "lucide_send" -> states.startActions.getValue("lucide_send").invoke()
        "lucide_server-cog" -> states.startActions.getValue("lucide_server-cog").invoke()
        "lucide_server-crash" -> states.startActions.getValue("lucide_server-crash").invoke()
        "lucide_server" -> states.startActions.getValue("lucide_server").invoke()
        "lucide_server-off" -> states.startActions.getValue("lucide_server-off").invoke()
        "lucide_server-plus" -> states.startActions.getValue("lucide_server-plus").invoke()
        "lucide_settings" -> states.startActions.getValue("lucide_settings").invoke()
        "lucide_shapes" -> states.startActions.getValue("lucide_shapes").invoke()
        "lucide_share" -> states.startActions.getValue("lucide_share").invoke()
        "lucide_shield-alert" -> states.startActions.getValue("lucide_shield-alert").invoke()
        "lucide_shield-ban" -> states.startActions.getValue("lucide_shield-ban").invoke()
        "lucide_shield-check" -> states.startActions.getValue("lucide_shield-check").invoke()
        "lucide_shield-cog-corner" -> states.startActions.getValue("lucide_shield-cog-corner").invoke()
        "lucide_shield-cog" -> states.startActions.getValue("lucide_shield-cog").invoke()
        "lucide_shield-ellipsis" -> states.startActions.getValue("lucide_shield-ellipsis").invoke()
        "lucide_shield-half" -> states.startActions.getValue("lucide_shield-half").invoke()
        "lucide_shield" -> states.startActions.getValue("lucide_shield").invoke()
        "lucide_shield-keyhole" -> states.startActions.getValue("lucide_shield-keyhole").invoke()
        "lucide_shield-lock" -> states.startActions.getValue("lucide_shield-lock").invoke()
        "lucide_shield-minus" -> states.startActions.getValue("lucide_shield-minus").invoke()
        "lucide_shield-off" -> states.startActions.getValue("lucide_shield-off").invoke()
        "lucide_shield-plus" -> states.startActions.getValue("lucide_shield-plus").invoke()
        "lucide_shield-question-mark" -> states.startActions.getValue("lucide_shield-question-mark").invoke()
        "lucide_shield-user" -> states.startActions.getValue("lucide_shield-user").invoke()
        "lucide_shield-x" -> states.startActions.getValue("lucide_shield-x").invoke()
        "lucide_shopping-bag" -> states.startActions.getValue("lucide_shopping-bag").invoke()
        "lucide_shopping-basket" -> states.startActions.getValue("lucide_shopping-basket").invoke()
        "lucide_shopping-cart" -> states.startActions.getValue("lucide_shopping-cart").invoke()
        "lucide_shopping-cart-minus" -> states.startActions.getValue("lucide_shopping-cart-minus").invoke()
        "lucide_shopping-cart-plus" -> states.startActions.getValue("lucide_shopping-cart-plus").invoke()
        "lucide_shrink" -> states.startActions.getValue("lucide_shrink").invoke()
        "lucide_shuffle" -> states.startActions.getValue("lucide_shuffle").invoke()
        "lucide_sigma" -> states.startActions.getValue("lucide_sigma").invoke()
        "lucide_signal-high" -> states.startActions.getValue("lucide_signal-high").invoke()
        "lucide_signal" -> states.startActions.getValue("lucide_signal").invoke()
        else -> Unit
    }
}

private fun lucideStartChunk8(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_signal-low" -> states.startActions.getValue("lucide_signal-low").invoke()
        "lucide_signal-medium" -> states.startActions.getValue("lucide_signal-medium").invoke()
        "lucide_signal-zero" -> states.startActions.getValue("lucide_signal-zero").invoke()
        "lucide_signature" -> states.startActions.getValue("lucide_signature").invoke()
        "lucide_signpost-big" -> states.startActions.getValue("lucide_signpost-big").invoke()
        "lucide_signpost" -> states.startActions.getValue("lucide_signpost").invoke()
        "lucide_siren" -> states.startActions.getValue("lucide_siren").invoke()
        "lucide_skip-back" -> states.startActions.getValue("lucide_skip-back").invoke()
        "lucide_skip-forward" -> states.startActions.getValue("lucide_skip-forward").invoke()
        "lucide_sliders-horizontal" -> states.startActions.getValue("lucide_sliders-horizontal").invoke()
        "lucide_smartphone-charging" -> states.startActions.getValue("lucide_smartphone-charging").invoke()
        "lucide_smartphone" -> states.startActions.getValue("lucide_smartphone").invoke()
        "lucide_smartphone-nfc" -> states.startActions.getValue("lucide_smartphone-nfc").invoke()
        "lucide_snowflake" -> states.startActions.getValue("lucide_snowflake").invoke()
        "lucide_sparkles" -> states.startActions.getValue("lucide_sparkles").invoke()
        "lucide_speaker" -> states.startActions.getValue("lucide_speaker").invoke()
        "lucide_spell-check" -> states.startActions.getValue("lucide_spell-check").invoke()
        "lucide_spray-can" -> states.startActions.getValue("lucide_spray-can").invoke()
        "lucide_sprout" -> states.startActions.getValue("lucide_sprout").invoke()
        "lucide_square-arrow-down" -> states.startActions.getValue("lucide_square-arrow-down").invoke()
        "lucide_square-arrow-down-left" -> states.startActions.getValue("lucide_square-arrow-down-left").invoke()
        "lucide_square-arrow-down-right" -> states.startActions.getValue("lucide_square-arrow-down-right").invoke()
        "lucide_square-arrow-left" -> states.startActions.getValue("lucide_square-arrow-left").invoke()
        "lucide_square-arrow-out-down-left" -> states.startActions.getValue("lucide_square-arrow-out-down-left").invoke()
        "lucide_square-arrow-out-down-right" -> states.startActions.getValue("lucide_square-arrow-out-down-right").invoke()
        "lucide_square-arrow-out-up-left" -> states.startActions.getValue("lucide_square-arrow-out-up-left").invoke()
        "lucide_square-arrow-out-up-right" -> states.startActions.getValue("lucide_square-arrow-out-up-right").invoke()
        "lucide_square-arrow-right-enter" -> states.startActions.getValue("lucide_square-arrow-right-enter").invoke()
        "lucide_square-arrow-right-exit" -> states.startActions.getValue("lucide_square-arrow-right-exit").invoke()
        "lucide_square-arrow-right" -> states.startActions.getValue("lucide_square-arrow-right").invoke()
        "lucide_square-arrow-up" -> states.startActions.getValue("lucide_square-arrow-up").invoke()
        "lucide_square-arrow-up-left" -> states.startActions.getValue("lucide_square-arrow-up-left").invoke()
        "lucide_square-arrow-up-right" -> states.startActions.getValue("lucide_square-arrow-up-right").invoke()
        "lucide_square-check-big" -> states.startActions.getValue("lucide_square-check-big").invoke()
        "lucide_square-check" -> states.startActions.getValue("lucide_square-check").invoke()
        "lucide_square-chevron-down" -> states.startActions.getValue("lucide_square-chevron-down").invoke()
        "lucide_square-chevron-left" -> states.startActions.getValue("lucide_square-chevron-left").invoke()
        "lucide_square-chevron-right" -> states.startActions.getValue("lucide_square-chevron-right").invoke()
        "lucide_square-chevron-up" -> states.startActions.getValue("lucide_square-chevron-up").invoke()
        "lucide_square-code" -> states.startActions.getValue("lucide_square-code").invoke()
        "lucide_square-dashed" -> states.startActions.getValue("lucide_square-dashed").invoke()
        "lucide_square-dot" -> states.startActions.getValue("lucide_square-dot").invoke()
        "lucide_square" -> states.startActions.getValue("lucide_square").invoke()
        "lucide_square-minus" -> states.startActions.getValue("lucide_square-minus").invoke()
        "lucide_square-pause" -> states.startActions.getValue("lucide_square-pause").invoke()
        "lucide_square-pen" -> states.startActions.getValue("lucide_square-pen").invoke()
        "lucide_square-play" -> states.startActions.getValue("lucide_square-play").invoke()
        "lucide_square-plus" -> states.startActions.getValue("lucide_square-plus").invoke()
        "lucide_square-slash" -> states.startActions.getValue("lucide_square-slash").invoke()
        "lucide_square-stop" -> states.startActions.getValue("lucide_square-stop").invoke()
        "lucide_square-terminal" -> states.startActions.getValue("lucide_square-terminal").invoke()
        "lucide_square-user" -> states.startActions.getValue("lucide_square-user").invoke()
        "lucide_square-user-round" -> states.startActions.getValue("lucide_square-user-round").invoke()
        "lucide_square-x" -> states.startActions.getValue("lucide_square-x").invoke()
        "lucide_stamp" -> states.startActions.getValue("lucide_stamp").invoke()
        "lucide_star-check" -> states.startActions.getValue("lucide_star-check").invoke()
        "lucide_star-half" -> states.startActions.getValue("lucide_star-half").invoke()
        "lucide_star" -> states.startActions.getValue("lucide_star").invoke()
        "lucide_star-minus" -> states.startActions.getValue("lucide_star-minus").invoke()
        "lucide_star-off" -> states.startActions.getValue("lucide_star-off").invoke()
        "lucide_star-plus" -> states.startActions.getValue("lucide_star-plus").invoke()
        "lucide_star-x" -> states.startActions.getValue("lucide_star-x").invoke()
        "lucide_step-back" -> states.startActions.getValue("lucide_step-back").invoke()
        "lucide_step-forward" -> states.startActions.getValue("lucide_step-forward").invoke()
        "lucide_stethoscope" -> states.startActions.getValue("lucide_stethoscope").invoke()
        "lucide_sticky-note" -> states.startActions.getValue("lucide_sticky-note").invoke()
        "lucide_store" -> states.startActions.getValue("lucide_store").invoke()
        "lucide_strikethrough" -> states.startActions.getValue("lucide_strikethrough").invoke()
        "lucide_subscript" -> states.startActions.getValue("lucide_subscript").invoke()
        "lucide_sun-dim" -> states.startActions.getValue("lucide_sun-dim").invoke()
        "lucide_sun" -> states.startActions.getValue("lucide_sun").invoke()
        "lucide_sun-medium" -> states.startActions.getValue("lucide_sun-medium").invoke()
        "lucide_sun-moon" -> states.startActions.getValue("lucide_sun-moon").invoke()
        "lucide_sun-snow" -> states.startActions.getValue("lucide_sun-snow").invoke()
        "lucide_sunrise" -> states.startActions.getValue("lucide_sunrise").invoke()
        "lucide_sunset" -> states.startActions.getValue("lucide_sunset").invoke()
        "lucide_superscript" -> states.startActions.getValue("lucide_superscript").invoke()
        "lucide_swiss-franc" -> states.startActions.getValue("lucide_swiss-franc").invoke()
        "lucide_swords" -> states.startActions.getValue("lucide_swords").invoke()
        "lucide_table" -> states.startActions.getValue("lucide_table").invoke()
        "lucide_tablet" -> states.startActions.getValue("lucide_tablet").invoke()
        "lucide_tag" -> states.startActions.getValue("lucide_tag").invoke()
        "lucide_tag-plus" -> states.startActions.getValue("lucide_tag-plus").invoke()
        "lucide_tag-x" -> states.startActions.getValue("lucide_tag-x").invoke()
        "lucide_tags" -> states.startActions.getValue("lucide_tags").invoke()
        "lucide_target" -> states.startActions.getValue("lucide_target").invoke()
        "lucide_telescope" -> states.startActions.getValue("lucide_telescope").invoke()
        "lucide_tent" -> states.startActions.getValue("lucide_tent").invoke()
        "lucide_terminal" -> states.startActions.getValue("lucide_terminal").invoke()
        "lucide_text-cursor" -> states.startActions.getValue("lucide_text-cursor").invoke()
        "lucide_text-initial" -> states.startActions.getValue("lucide_text-initial").invoke()
        "lucide_text-quote" -> states.startActions.getValue("lucide_text-quote").invoke()
        "lucide_text-search" -> states.startActions.getValue("lucide_text-search").invoke()
        "lucide_thermometer" -> states.startActions.getValue("lucide_thermometer").invoke()
        "lucide_thermometer-sun" -> states.startActions.getValue("lucide_thermometer-sun").invoke()
        "lucide_thumbs-down" -> states.startActions.getValue("lucide_thumbs-down").invoke()
        "lucide_thumbs-up" -> states.startActions.getValue("lucide_thumbs-up").invoke()
        "lucide_ticket-check" -> states.startActions.getValue("lucide_ticket-check").invoke()
        "lucide_ticket" -> states.startActions.getValue("lucide_ticket").invoke()
        "lucide_ticket-minus" -> states.startActions.getValue("lucide_ticket-minus").invoke()
        else -> Unit
    }
}

private fun lucideStartChunk9(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_ticket-percent" -> states.startActions.getValue("lucide_ticket-percent").invoke()
        "lucide_ticket-plus" -> states.startActions.getValue("lucide_ticket-plus").invoke()
        "lucide_ticket-slash" -> states.startActions.getValue("lucide_ticket-slash").invoke()
        "lucide_ticket-x" -> states.startActions.getValue("lucide_ticket-x").invoke()
        "lucide_timer" -> states.startActions.getValue("lucide_timer").invoke()
        "lucide_timer-off" -> states.startActions.getValue("lucide_timer-off").invoke()
        "lucide_timer-reset" -> states.startActions.getValue("lucide_timer-reset").invoke()
        "lucide_toggle-left" -> states.startActions.getValue("lucide_toggle-left").invoke()
        "lucide_toggle-right" -> states.startActions.getValue("lucide_toggle-right").invoke()
        "lucide_tornado" -> states.startActions.getValue("lucide_tornado").invoke()
        "lucide_traffic-cone" -> states.startActions.getValue("lucide_traffic-cone").invoke()
        "lucide_trash-2" -> states.startActions.getValue("lucide_trash-2").invoke()
        "lucide_trash" -> states.startActions.getValue("lucide_trash").invoke()
        "lucide_trending-down" -> states.startActions.getValue("lucide_trending-down").invoke()
        "lucide_trending-up-down" -> states.startActions.getValue("lucide_trending-up-down").invoke()
        "lucide_trending-up" -> states.startActions.getValue("lucide_trending-up").invoke()
        "lucide_triangle-alert" -> states.startActions.getValue("lucide_triangle-alert").invoke()
        "lucide_triangle-dashed" -> states.startActions.getValue("lucide_triangle-dashed").invoke()
        "lucide_triangle" -> states.startActions.getValue("lucide_triangle").invoke()
        "lucide_triangle-right" -> states.startActions.getValue("lucide_triangle-right").invoke()
        "lucide_trophy" -> states.startActions.getValue("lucide_trophy").invoke()
        "lucide_truck" -> states.startActions.getValue("lucide_truck").invoke()
        "lucide_turkish-lira" -> states.startActions.getValue("lucide_turkish-lira").invoke()
        "lucide_tv" -> states.startActions.getValue("lucide_tv").invoke()
        "lucide_tv-minimal" -> states.startActions.getValue("lucide_tv-minimal").invoke()
        "lucide_twitter" -> states.startActions.getValue("lucide_twitter").invoke()
        "lucide_type" -> states.startActions.getValue("lucide_type").invoke()
        "lucide_umbrella" -> states.startActions.getValue("lucide_umbrella").invoke()
        "lucide_underline" -> states.startActions.getValue("lucide_underline").invoke()
        "lucide_undo" -> states.startActions.getValue("lucide_undo").invoke()
        "lucide_unfold-vertical" -> states.startActions.getValue("lucide_unfold-vertical").invoke()
        "lucide_university" -> states.startActions.getValue("lucide_university").invoke()
        "lucide_unlink" -> states.startActions.getValue("lucide_unlink").invoke()
        "lucide_upload" -> states.startActions.getValue("lucide_upload").invoke()
        "lucide_usb" -> states.startActions.getValue("lucide_usb").invoke()
        "lucide_user-check" -> states.startActions.getValue("lucide_user-check").invoke()
        "lucide_user-cog" -> states.startActions.getValue("lucide_user-cog").invoke()
        "lucide_user-group" -> states.startActions.getValue("lucide_user-group").invoke()
        "lucide_user" -> states.startActions.getValue("lucide_user").invoke()
        "lucide_user-key" -> states.startActions.getValue("lucide_user-key").invoke()
        "lucide_user-lock" -> states.startActions.getValue("lucide_user-lock").invoke()
        "lucide_user-minus" -> states.startActions.getValue("lucide_user-minus").invoke()
        "lucide_user-pen" -> states.startActions.getValue("lucide_user-pen").invoke()
        "lucide_user-plus" -> states.startActions.getValue("lucide_user-plus").invoke()
        "lucide_user-round-arrow-left" -> states.startActions.getValue("lucide_user-round-arrow-left").invoke()
        "lucide_user-round-check" -> states.startActions.getValue("lucide_user-round-check").invoke()
        "lucide_user-round-cog" -> states.startActions.getValue("lucide_user-round-cog").invoke()
        "lucide_user-round-group" -> states.startActions.getValue("lucide_user-round-group").invoke()
        "lucide_user-round" -> states.startActions.getValue("lucide_user-round").invoke()
        "lucide_user-round-key" -> states.startActions.getValue("lucide_user-round-key").invoke()
        "lucide_user-round-minus" -> states.startActions.getValue("lucide_user-round-minus").invoke()
        "lucide_user-round-pen" -> states.startActions.getValue("lucide_user-round-pen").invoke()
        "lucide_user-round-plus" -> states.startActions.getValue("lucide_user-round-plus").invoke()
        "lucide_user-round-search" -> states.startActions.getValue("lucide_user-round-search").invoke()
        "lucide_user-round-x" -> states.startActions.getValue("lucide_user-round-x").invoke()
        "lucide_user-search" -> states.startActions.getValue("lucide_user-search").invoke()
        "lucide_user-shield" -> states.startActions.getValue("lucide_user-shield").invoke()
        "lucide_user-star" -> states.startActions.getValue("lucide_user-star").invoke()
        "lucide_user-x" -> states.startActions.getValue("lucide_user-x").invoke()
        "lucide_users" -> states.startActions.getValue("lucide_users").invoke()
        "lucide_users-round" -> states.startActions.getValue("lucide_users-round").invoke()
        "lucide_utensils" -> states.startActions.getValue("lucide_utensils").invoke()
        "lucide_variable" -> states.startActions.getValue("lucide_variable").invoke()
        "lucide_vault" -> states.startActions.getValue("lucide_vault").invoke()
        "lucide_venus" -> states.startActions.getValue("lucide_venus").invoke()
        "lucide_video" -> states.startActions.getValue("lucide_video").invoke()
        "lucide_video-off" -> states.startActions.getValue("lucide_video-off").invoke()
        "lucide_volume-1" -> states.startActions.getValue("lucide_volume-1").invoke()
        "lucide_volume-2" -> states.startActions.getValue("lucide_volume-2").invoke()
        "lucide_volume" -> states.startActions.getValue("lucide_volume").invoke()
        "lucide_volume-off" -> states.startActions.getValue("lucide_volume-off").invoke()
        "lucide_volume-x" -> states.startActions.getValue("lucide_volume-x").invoke()
        "lucide_wallet-cards" -> states.startActions.getValue("lucide_wallet-cards").invoke()
        "lucide_wallet" -> states.startActions.getValue("lucide_wallet").invoke()
        "lucide_wallet-minimal" -> states.startActions.getValue("lucide_wallet-minimal").invoke()
        "lucide_wand-sparkles" -> states.startActions.getValue("lucide_wand-sparkles").invoke()
        "lucide_warehouse" -> states.startActions.getValue("lucide_warehouse").invoke()
        "lucide_watch" -> states.startActions.getValue("lucide_watch").invoke()
        "lucide_waypoints" -> states.startActions.getValue("lucide_waypoints").invoke()
        "lucide_webcam" -> states.startActions.getValue("lucide_webcam").invoke()
        "lucide_webcam-off" -> states.startActions.getValue("lucide_webcam-off").invoke()
        "lucide_webhook" -> states.startActions.getValue("lucide_webhook").invoke()
        "lucide_webhook-off" -> states.startActions.getValue("lucide_webhook-off").invoke()
        "lucide_wifi-cog" -> states.startActions.getValue("lucide_wifi-cog").invoke()
        "lucide_wifi-high" -> states.startActions.getValue("lucide_wifi-high").invoke()
        "lucide_wifi" -> states.startActions.getValue("lucide_wifi").invoke()
        "lucide_wifi-low" -> states.startActions.getValue("lucide_wifi-low").invoke()
        "lucide_wifi-off" -> states.startActions.getValue("lucide_wifi-off").invoke()
        "lucide_wifi-pen" -> states.startActions.getValue("lucide_wifi-pen").invoke()
        "lucide_wifi-sync" -> states.startActions.getValue("lucide_wifi-sync").invoke()
        "lucide_wifi-zero" -> states.startActions.getValue("lucide_wifi-zero").invoke()
        "lucide_wind" -> states.startActions.getValue("lucide_wind").invoke()
        "lucide_workflow" -> states.startActions.getValue("lucide_workflow").invoke()
        "lucide_wrap-text" -> states.startActions.getValue("lucide_wrap-text").invoke()
        "lucide_wrench" -> states.startActions.getValue("lucide_wrench").invoke()
        "lucide_x" -> states.startActions.getValue("lucide_x").invoke()
        "lucide_zap" -> states.startActions.getValue("lucide_zap").invoke()
        "lucide_zoom-in" -> states.startActions.getValue("lucide_zoom-in").invoke()
        "lucide_zoom-out" -> states.startActions.getValue("lucide_zoom-out").invoke()
        else -> Unit
    }
}

internal fun lucideStop(id: String, states: LucideIconStates) {
    lucideStopChunk0(id, states)
    lucideStopChunk1(id, states)
    lucideStopChunk2(id, states)
    lucideStopChunk3(id, states)
    lucideStopChunk4(id, states)
    lucideStopChunk5(id, states)
    lucideStopChunk6(id, states)
    lucideStopChunk7(id, states)
    lucideStopChunk8(id, states)
    lucideStopChunk9(id, states)
}

private fun lucideStopChunk0(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_a-arrow-down" -> states.stopActions.getValue("lucide_a-arrow-down").invoke()
        "lucide_a-arrow-up" -> states.stopActions.getValue("lucide_a-arrow-up").invoke()
        "lucide_accessibility" -> states.stopActions.getValue("lucide_accessibility").invoke()
        "lucide_activity" -> states.stopActions.getValue("lucide_activity").invoke()
        "lucide_alarm-clock-check" -> states.stopActions.getValue("lucide_alarm-clock-check").invoke()
        "lucide_alarm-clock" -> states.stopActions.getValue("lucide_alarm-clock").invoke()
        "lucide_alarm-clock-minus" -> states.stopActions.getValue("lucide_alarm-clock-minus").invoke()
        "lucide_alarm-clock-off" -> states.stopActions.getValue("lucide_alarm-clock-off").invoke()
        "lucide_alarm-clock-plus" -> states.stopActions.getValue("lucide_alarm-clock-plus").invoke()
        "lucide_align-center" -> states.stopActions.getValue("lucide_align-center").invoke()
        "lucide_align-justify" -> states.stopActions.getValue("lucide_align-justify").invoke()
        "lucide_align-left" -> states.stopActions.getValue("lucide_align-left").invoke()
        "lucide_align-right" -> states.stopActions.getValue("lucide_align-right").invoke()
        "lucide_anchor" -> states.stopActions.getValue("lucide_anchor").invoke()
        "lucide_angry" -> states.stopActions.getValue("lucide_angry").invoke()
        "lucide_annoyed" -> states.stopActions.getValue("lucide_annoyed").invoke()
        "lucide_app-window" -> states.stopActions.getValue("lucide_app-window").invoke()
        "lucide_archive" -> states.stopActions.getValue("lucide_archive").invoke()
        "lucide_arrow-big-down-dash" -> states.stopActions.getValue("lucide_arrow-big-down-dash").invoke()
        "lucide_arrow-big-down" -> states.stopActions.getValue("lucide_arrow-big-down").invoke()
        "lucide_arrow-big-left-dash" -> states.stopActions.getValue("lucide_arrow-big-left-dash").invoke()
        "lucide_arrow-big-left" -> states.stopActions.getValue("lucide_arrow-big-left").invoke()
        "lucide_arrow-big-right-dash" -> states.stopActions.getValue("lucide_arrow-big-right-dash").invoke()
        "lucide_arrow-big-right" -> states.stopActions.getValue("lucide_arrow-big-right").invoke()
        "lucide_arrow-big-up-dash" -> states.stopActions.getValue("lucide_arrow-big-up-dash").invoke()
        "lucide_arrow-big-up" -> states.stopActions.getValue("lucide_arrow-big-up").invoke()
        "lucide_arrow-down-0-1" -> states.stopActions.getValue("lucide_arrow-down-0-1").invoke()
        "lucide_arrow-down-1-0" -> states.stopActions.getValue("lucide_arrow-down-1-0").invoke()
        "lucide_arrow-down-a-z" -> states.stopActions.getValue("lucide_arrow-down-a-z").invoke()
        "lucide_arrow-down-from-line" -> states.stopActions.getValue("lucide_arrow-down-from-line").invoke()
        "lucide_arrow-down" -> states.stopActions.getValue("lucide_arrow-down").invoke()
        "lucide_arrow-down-left" -> states.stopActions.getValue("lucide_arrow-down-left").invoke()
        "lucide_arrow-down-narrow-wide" -> states.stopActions.getValue("lucide_arrow-down-narrow-wide").invoke()
        "lucide_arrow-down-right" -> states.stopActions.getValue("lucide_arrow-down-right").invoke()
        "lucide_arrow-down-to-dot" -> states.stopActions.getValue("lucide_arrow-down-to-dot").invoke()
        "lucide_arrow-down-to-line" -> states.stopActions.getValue("lucide_arrow-down-to-line").invoke()
        "lucide_arrow-down-up" -> states.stopActions.getValue("lucide_arrow-down-up").invoke()
        "lucide_arrow-down-wide-narrow" -> states.stopActions.getValue("lucide_arrow-down-wide-narrow").invoke()
        "lucide_arrow-down-z-a" -> states.stopActions.getValue("lucide_arrow-down-z-a").invoke()
        "lucide_arrow-left-from-line" -> states.stopActions.getValue("lucide_arrow-left-from-line").invoke()
        "lucide_arrow-left" -> states.stopActions.getValue("lucide_arrow-left").invoke()
        "lucide_arrow-left-right" -> states.stopActions.getValue("lucide_arrow-left-right").invoke()
        "lucide_arrow-left-to-line" -> states.stopActions.getValue("lucide_arrow-left-to-line").invoke()
        "lucide_arrow-right-from-line" -> states.stopActions.getValue("lucide_arrow-right-from-line").invoke()
        "lucide_arrow-right" -> states.stopActions.getValue("lucide_arrow-right").invoke()
        "lucide_arrow-right-left" -> states.stopActions.getValue("lucide_arrow-right-left").invoke()
        "lucide_arrow-right-to-line" -> states.stopActions.getValue("lucide_arrow-right-to-line").invoke()
        "lucide_arrow-up-0-1" -> states.stopActions.getValue("lucide_arrow-up-0-1").invoke()
        "lucide_arrow-up-1-0" -> states.stopActions.getValue("lucide_arrow-up-1-0").invoke()
        "lucide_arrow-up-a-z" -> states.stopActions.getValue("lucide_arrow-up-a-z").invoke()
        "lucide_arrow-up-down" -> states.stopActions.getValue("lucide_arrow-up-down").invoke()
        "lucide_arrow-up-from-dot" -> states.stopActions.getValue("lucide_arrow-up-from-dot").invoke()
        "lucide_arrow-up-from-line" -> states.stopActions.getValue("lucide_arrow-up-from-line").invoke()
        "lucide_arrow-up" -> states.stopActions.getValue("lucide_arrow-up").invoke()
        "lucide_arrow-up-left" -> states.stopActions.getValue("lucide_arrow-up-left").invoke()
        "lucide_arrow-up-narrow-wide" -> states.stopActions.getValue("lucide_arrow-up-narrow-wide").invoke()
        "lucide_arrow-up-right" -> states.stopActions.getValue("lucide_arrow-up-right").invoke()
        "lucide_arrow-up-to-line" -> states.stopActions.getValue("lucide_arrow-up-to-line").invoke()
        "lucide_arrow-up-wide-narrow" -> states.stopActions.getValue("lucide_arrow-up-wide-narrow").invoke()
        "lucide_arrow-up-z-a" -> states.stopActions.getValue("lucide_arrow-up-z-a").invoke()
        "lucide_at-sign" -> states.stopActions.getValue("lucide_at-sign").invoke()
        "lucide_atom" -> states.stopActions.getValue("lucide_atom").invoke()
        "lucide_audio-lines" -> states.stopActions.getValue("lucide_audio-lines").invoke()
        "lucide_audio-lines-off" -> states.stopActions.getValue("lucide_audio-lines-off").invoke()
        "lucide_audio-lines-x" -> states.stopActions.getValue("lucide_audio-lines-x").invoke()
        "lucide_audio-waveform" -> states.stopActions.getValue("lucide_audio-waveform").invoke()
        "lucide_badge-cent" -> states.stopActions.getValue("lucide_badge-cent").invoke()
        "lucide_badge-check" -> states.stopActions.getValue("lucide_badge-check").invoke()
        "lucide_badge-dollar-sign" -> states.stopActions.getValue("lucide_badge-dollar-sign").invoke()
        "lucide_badge-info" -> states.stopActions.getValue("lucide_badge-info").invoke()
        "lucide_badge-percent" -> states.stopActions.getValue("lucide_badge-percent").invoke()
        "lucide_baggage-claim" -> states.stopActions.getValue("lucide_baggage-claim").invoke()
        "lucide_ban" -> states.stopActions.getValue("lucide_ban").invoke()
        "lucide_banknote-arrow-down" -> states.stopActions.getValue("lucide_banknote-arrow-down").invoke()
        "lucide_banknote-arrow-up" -> states.stopActions.getValue("lucide_banknote-arrow-up").invoke()
        "lucide_banknote-check" -> states.stopActions.getValue("lucide_banknote-check").invoke()
        "lucide_banknote" -> states.stopActions.getValue("lucide_banknote").invoke()
        "lucide_banknote-x" -> states.stopActions.getValue("lucide_banknote-x").invoke()
        "lucide_barcode" -> states.stopActions.getValue("lucide_barcode").invoke()
        "lucide_battery-charging" -> states.stopActions.getValue("lucide_battery-charging").invoke()
        "lucide_battery-full" -> states.stopActions.getValue("lucide_battery-full").invoke()
        "lucide_battery" -> states.stopActions.getValue("lucide_battery").invoke()
        "lucide_battery-low" -> states.stopActions.getValue("lucide_battery-low").invoke()
        "lucide_battery-medium" -> states.stopActions.getValue("lucide_battery-medium").invoke()
        "lucide_battery-plus" -> states.stopActions.getValue("lucide_battery-plus").invoke()
        "lucide_battery-warning" -> states.stopActions.getValue("lucide_battery-warning").invoke()
        "lucide_bell-dot" -> states.stopActions.getValue("lucide_bell-dot").invoke()
        "lucide_bell-electric" -> states.stopActions.getValue("lucide_bell-electric").invoke()
        "lucide_bell" -> states.stopActions.getValue("lucide_bell").invoke()
        "lucide_bell-minus" -> states.stopActions.getValue("lucide_bell-minus").invoke()
        "lucide_bell-off" -> states.stopActions.getValue("lucide_bell-off").invoke()
        "lucide_bell-plus" -> states.stopActions.getValue("lucide_bell-plus").invoke()
        "lucide_bell-ring" -> states.stopActions.getValue("lucide_bell-ring").invoke()
        "lucide_biceps-flexed" -> states.stopActions.getValue("lucide_biceps-flexed").invoke()
        "lucide_bike" -> states.stopActions.getValue("lucide_bike").invoke()
        "lucide_bitcoin" -> states.stopActions.getValue("lucide_bitcoin").invoke()
        "lucide_blend" -> states.stopActions.getValue("lucide_blend").invoke()
        "lucide_blocks" -> states.stopActions.getValue("lucide_blocks").invoke()
        "lucide_bluetooth" -> states.stopActions.getValue("lucide_bluetooth").invoke()
        "lucide_bold" -> states.stopActions.getValue("lucide_bold").invoke()
        else -> Unit
    }
}

private fun lucideStopChunk1(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_bolt" -> states.stopActions.getValue("lucide_bolt").invoke()
        "lucide_book-open-check" -> states.stopActions.getValue("lucide_book-open-check").invoke()
        "lucide_book-open" -> states.stopActions.getValue("lucide_book-open").invoke()
        "lucide_book-open-text" -> states.stopActions.getValue("lucide_book-open-text").invoke()
        "lucide_bookmark-check" -> states.stopActions.getValue("lucide_bookmark-check").invoke()
        "lucide_bookmark" -> states.stopActions.getValue("lucide_bookmark").invoke()
        "lucide_bookmark-minus" -> states.stopActions.getValue("lucide_bookmark-minus").invoke()
        "lucide_bookmark-off" -> states.stopActions.getValue("lucide_bookmark-off").invoke()
        "lucide_bookmark-plus" -> states.stopActions.getValue("lucide_bookmark-plus").invoke()
        "lucide_bookmark-x" -> states.stopActions.getValue("lucide_bookmark-x").invoke()
        "lucide_bot" -> states.stopActions.getValue("lucide_bot").invoke()
        "lucide_box" -> states.stopActions.getValue("lucide_box").invoke()
        "lucide_boxes" -> states.stopActions.getValue("lucide_boxes").invoke()
        "lucide_brain" -> states.stopActions.getValue("lucide_brain").invoke()
        "lucide_briefcase" -> states.stopActions.getValue("lucide_briefcase").invoke()
        "lucide_bring-to-front" -> states.stopActions.getValue("lucide_bring-to-front").invoke()
        "lucide_bug" -> states.stopActions.getValue("lucide_bug").invoke()
        "lucide_bug-off" -> states.stopActions.getValue("lucide_bug-off").invoke()
        "lucide_bug-play" -> states.stopActions.getValue("lucide_bug-play").invoke()
        "lucide_building-2" -> states.stopActions.getValue("lucide_building-2").invoke()
        "lucide_building-complex" -> states.stopActions.getValue("lucide_building-complex").invoke()
        "lucide_building-complex-plus" -> states.stopActions.getValue("lucide_building-complex-plus").invoke()
        "lucide_building" -> states.stopActions.getValue("lucide_building").invoke()
        "lucide_calculator" -> states.stopActions.getValue("lucide_calculator").invoke()
        "lucide_calendar-arrow-down" -> states.stopActions.getValue("lucide_calendar-arrow-down").invoke()
        "lucide_calendar-arrow-up" -> states.stopActions.getValue("lucide_calendar-arrow-up").invoke()
        "lucide_calendar-check" -> states.stopActions.getValue("lucide_calendar-check").invoke()
        "lucide_calendar-chevrons-right" -> states.stopActions.getValue("lucide_calendar-chevrons-right").invoke()
        "lucide_calendar-clock" -> states.stopActions.getValue("lucide_calendar-clock").invoke()
        "lucide_calendar-cog" -> states.stopActions.getValue("lucide_calendar-cog").invoke()
        "lucide_calendar-days" -> states.stopActions.getValue("lucide_calendar-days").invoke()
        "lucide_calendar-fold" -> states.stopActions.getValue("lucide_calendar-fold").invoke()
        "lucide_calendar-heart" -> states.stopActions.getValue("lucide_calendar-heart").invoke()
        "lucide_calendar" -> states.stopActions.getValue("lucide_calendar").invoke()
        "lucide_calendar-minus" -> states.stopActions.getValue("lucide_calendar-minus").invoke()
        "lucide_calendar-off" -> states.stopActions.getValue("lucide_calendar-off").invoke()
        "lucide_calendar-plus" -> states.stopActions.getValue("lucide_calendar-plus").invoke()
        "lucide_calendar-range" -> states.stopActions.getValue("lucide_calendar-range").invoke()
        "lucide_calendar-search" -> states.stopActions.getValue("lucide_calendar-search").invoke()
        "lucide_calendar-sync" -> states.stopActions.getValue("lucide_calendar-sync").invoke()
        "lucide_calendar-x" -> states.stopActions.getValue("lucide_calendar-x").invoke()
        "lucide_calendars" -> states.stopActions.getValue("lucide_calendars").invoke()
        "lucide_camera" -> states.stopActions.getValue("lucide_camera").invoke()
        "lucide_camera-off" -> states.stopActions.getValue("lucide_camera-off").invoke()
        "lucide_captions" -> states.stopActions.getValue("lucide_captions").invoke()
        "lucide_car" -> states.stopActions.getValue("lucide_car").invoke()
        "lucide_case-lower" -> states.stopActions.getValue("lucide_case-lower").invoke()
        "lucide_case-sensitive" -> states.stopActions.getValue("lucide_case-sensitive").invoke()
        "lucide_case-upper" -> states.stopActions.getValue("lucide_case-upper").invoke()
        "lucide_cast" -> states.stopActions.getValue("lucide_cast").invoke()
        "lucide_cat" -> states.stopActions.getValue("lucide_cat").invoke()
        "lucide_cctv" -> states.stopActions.getValue("lucide_cctv").invoke()
        "lucide_cctv-off" -> states.stopActions.getValue("lucide_cctv-off").invoke()
        "lucide_chart-area" -> states.stopActions.getValue("lucide_chart-area").invoke()
        "lucide_chart-bar-big" -> states.stopActions.getValue("lucide_chart-bar-big").invoke()
        "lucide_chart-bar-decreasing" -> states.stopActions.getValue("lucide_chart-bar-decreasing").invoke()
        "lucide_chart-bar" -> states.stopActions.getValue("lucide_chart-bar").invoke()
        "lucide_chart-bar-increasing" -> states.stopActions.getValue("lucide_chart-bar-increasing").invoke()
        "lucide_chart-candlestick" -> states.stopActions.getValue("lucide_chart-candlestick").invoke()
        "lucide_chart-column-big" -> states.stopActions.getValue("lucide_chart-column-big").invoke()
        "lucide_chart-column-decreasing" -> states.stopActions.getValue("lucide_chart-column-decreasing").invoke()
        "lucide_chart-column" -> states.stopActions.getValue("lucide_chart-column").invoke()
        "lucide_chart-column-increasing" -> states.stopActions.getValue("lucide_chart-column-increasing").invoke()
        "lucide_chart-gantt" -> states.stopActions.getValue("lucide_chart-gantt").invoke()
        "lucide_chart-line" -> states.stopActions.getValue("lucide_chart-line").invoke()
        "lucide_chart-network" -> states.stopActions.getValue("lucide_chart-network").invoke()
        "lucide_chart-no-axes-column" -> states.stopActions.getValue("lucide_chart-no-axes-column").invoke()
        "lucide_chart-no-axes-combined" -> states.stopActions.getValue("lucide_chart-no-axes-combined").invoke()
        "lucide_chart-pie" -> states.stopActions.getValue("lucide_chart-pie").invoke()
        "lucide_chart-scatter" -> states.stopActions.getValue("lucide_chart-scatter").invoke()
        "lucide_chart-spline" -> states.stopActions.getValue("lucide_chart-spline").invoke()
        "lucide_check-check" -> states.stopActions.getValue("lucide_check-check").invoke()
        "lucide_check" -> states.stopActions.getValue("lucide_check").invoke()
        "lucide_chevron-down" -> states.stopActions.getValue("lucide_chevron-down").invoke()
        "lucide_chevron-first" -> states.stopActions.getValue("lucide_chevron-first").invoke()
        "lucide_chevron-last" -> states.stopActions.getValue("lucide_chevron-last").invoke()
        "lucide_chevron-left" -> states.stopActions.getValue("lucide_chevron-left").invoke()
        "lucide_chevron-right" -> states.stopActions.getValue("lucide_chevron-right").invoke()
        "lucide_chevron-up" -> states.stopActions.getValue("lucide_chevron-up").invoke()
        "lucide_chevrons-down" -> states.stopActions.getValue("lucide_chevrons-down").invoke()
        "lucide_chevrons-down-up" -> states.stopActions.getValue("lucide_chevrons-down-up").invoke()
        "lucide_chevrons-left" -> states.stopActions.getValue("lucide_chevrons-left").invoke()
        "lucide_chevrons-left-right-ellipsis" -> states.stopActions.getValue("lucide_chevrons-left-right-ellipsis").invoke()
        "lucide_chevrons-left-right" -> states.stopActions.getValue("lucide_chevrons-left-right").invoke()
        "lucide_chevrons-right" -> states.stopActions.getValue("lucide_chevrons-right").invoke()
        "lucide_chevrons-right-left" -> states.stopActions.getValue("lucide_chevrons-right-left").invoke()
        "lucide_chevrons-up-down" -> states.stopActions.getValue("lucide_chevrons-up-down").invoke()
        "lucide_chevrons-up" -> states.stopActions.getValue("lucide_chevrons-up").invoke()
        "lucide_chrome" -> states.stopActions.getValue("lucide_chrome").invoke()
        "lucide_circle-alert" -> states.stopActions.getValue("lucide_circle-alert").invoke()
        "lucide_circle-arrow-down" -> states.stopActions.getValue("lucide_circle-arrow-down").invoke()
        "lucide_circle-arrow-left" -> states.stopActions.getValue("lucide_circle-arrow-left").invoke()
        "lucide_circle-arrow-out-down-left" -> states.stopActions.getValue("lucide_circle-arrow-out-down-left").invoke()
        "lucide_circle-arrow-out-down-right" -> states.stopActions.getValue("lucide_circle-arrow-out-down-right").invoke()
        "lucide_circle-arrow-out-up-left" -> states.stopActions.getValue("lucide_circle-arrow-out-up-left").invoke()
        "lucide_circle-arrow-out-up-right" -> states.stopActions.getValue("lucide_circle-arrow-out-up-right").invoke()
        "lucide_circle-arrow-right" -> states.stopActions.getValue("lucide_circle-arrow-right").invoke()
        "lucide_circle-arrow-up" -> states.stopActions.getValue("lucide_circle-arrow-up").invoke()
        "lucide_circle-check-big" -> states.stopActions.getValue("lucide_circle-check-big").invoke()
        "lucide_circle-check" -> states.stopActions.getValue("lucide_circle-check").invoke()
        else -> Unit
    }
}

private fun lucideStopChunk2(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_circle-chevron-down" -> states.stopActions.getValue("lucide_circle-chevron-down").invoke()
        "lucide_circle-chevron-left" -> states.stopActions.getValue("lucide_circle-chevron-left").invoke()
        "lucide_circle-chevron-right" -> states.stopActions.getValue("lucide_circle-chevron-right").invoke()
        "lucide_circle-chevron-up" -> states.stopActions.getValue("lucide_circle-chevron-up").invoke()
        "lucide_circle-dashed" -> states.stopActions.getValue("lucide_circle-dashed").invoke()
        "lucide_circle-dollar-sign" -> states.stopActions.getValue("lucide_circle-dollar-sign").invoke()
        "lucide_circle-dot" -> states.stopActions.getValue("lucide_circle-dot").invoke()
        "lucide_circle-ellipsis" -> states.stopActions.getValue("lucide_circle-ellipsis").invoke()
        "lucide_circle-euro" -> states.stopActions.getValue("lucide_circle-euro").invoke()
        "lucide_circle" -> states.stopActions.getValue("lucide_circle").invoke()
        "lucide_circle-minus" -> states.stopActions.getValue("lucide_circle-minus").invoke()
        "lucide_circle-off" -> states.stopActions.getValue("lucide_circle-off").invoke()
        "lucide_circle-pause" -> states.stopActions.getValue("lucide_circle-pause").invoke()
        "lucide_circle-percent" -> states.stopActions.getValue("lucide_circle-percent").invoke()
        "lucide_circle-play" -> states.stopActions.getValue("lucide_circle-play").invoke()
        "lucide_circle-plus" -> states.stopActions.getValue("lucide_circle-plus").invoke()
        "lucide_circle-pound-sterling" -> states.stopActions.getValue("lucide_circle-pound-sterling").invoke()
        "lucide_circle-power" -> states.stopActions.getValue("lucide_circle-power").invoke()
        "lucide_circle-question-mark" -> states.stopActions.getValue("lucide_circle-question-mark").invoke()
        "lucide_circle-slash-2" -> states.stopActions.getValue("lucide_circle-slash-2").invoke()
        "lucide_circle-slash" -> states.stopActions.getValue("lucide_circle-slash").invoke()
        "lucide_circle-star" -> states.stopActions.getValue("lucide_circle-star").invoke()
        "lucide_circle-stop" -> states.stopActions.getValue("lucide_circle-stop").invoke()
        "lucide_circle-user" -> states.stopActions.getValue("lucide_circle-user").invoke()
        "lucide_circle-user-round" -> states.stopActions.getValue("lucide_circle-user-round").invoke()
        "lucide_circle-x" -> states.stopActions.getValue("lucide_circle-x").invoke()
        "lucide_clapperboard" -> states.stopActions.getValue("lucide_clapperboard").invoke()
        "lucide_clipboard-check" -> states.stopActions.getValue("lucide_clipboard-check").invoke()
        "lucide_clipboard-clock" -> states.stopActions.getValue("lucide_clipboard-clock").invoke()
        "lucide_clipboard" -> states.stopActions.getValue("lucide_clipboard").invoke()
        "lucide_clipboard-list" -> states.stopActions.getValue("lucide_clipboard-list").invoke()
        "lucide_clipboard-paste" -> states.stopActions.getValue("lucide_clipboard-paste").invoke()
        "lucide_clock-alert" -> states.stopActions.getValue("lucide_clock-alert").invoke()
        "lucide_clock-arrow-down" -> states.stopActions.getValue("lucide_clock-arrow-down").invoke()
        "lucide_clock-arrow-left" -> states.stopActions.getValue("lucide_clock-arrow-left").invoke()
        "lucide_clock-arrow-right" -> states.stopActions.getValue("lucide_clock-arrow-right").invoke()
        "lucide_clock-arrow-up" -> states.stopActions.getValue("lucide_clock-arrow-up").invoke()
        "lucide_clock-check" -> states.stopActions.getValue("lucide_clock-check").invoke()
        "lucide_clock-fading" -> states.stopActions.getValue("lucide_clock-fading").invoke()
        "lucide_clock" -> states.stopActions.getValue("lucide_clock").invoke()
        "lucide_clock-plus" -> states.stopActions.getValue("lucide_clock-plus").invoke()
        "lucide_cloud-check" -> states.stopActions.getValue("lucide_cloud-check").invoke()
        "lucide_cloud-download" -> states.stopActions.getValue("lucide_cloud-download").invoke()
        "lucide_cloud-drizzle" -> states.stopActions.getValue("lucide_cloud-drizzle").invoke()
        "lucide_cloud-fog" -> states.stopActions.getValue("lucide_cloud-fog").invoke()
        "lucide_cloud-hail" -> states.stopActions.getValue("lucide_cloud-hail").invoke()
        "lucide_cloud" -> states.stopActions.getValue("lucide_cloud").invoke()
        "lucide_cloud-lightning" -> states.stopActions.getValue("lucide_cloud-lightning").invoke()
        "lucide_cloud-moon" -> states.stopActions.getValue("lucide_cloud-moon").invoke()
        "lucide_cloud-moon-rain" -> states.stopActions.getValue("lucide_cloud-moon-rain").invoke()
        "lucide_cloud-off" -> states.stopActions.getValue("lucide_cloud-off").invoke()
        "lucide_cloud-rain" -> states.stopActions.getValue("lucide_cloud-rain").invoke()
        "lucide_cloud-rain-wind" -> states.stopActions.getValue("lucide_cloud-rain-wind").invoke()
        "lucide_cloud-snow" -> states.stopActions.getValue("lucide_cloud-snow").invoke()
        "lucide_cloud-sun" -> states.stopActions.getValue("lucide_cloud-sun").invoke()
        "lucide_cloud-sun-rain" -> states.stopActions.getValue("lucide_cloud-sun-rain").invoke()
        "lucide_cloud-sync" -> states.stopActions.getValue("lucide_cloud-sync").invoke()
        "lucide_cloud-upload" -> states.stopActions.getValue("lucide_cloud-upload").invoke()
        "lucide_cloudy" -> states.stopActions.getValue("lucide_cloudy").invoke()
        "lucide_code" -> states.stopActions.getValue("lucide_code").invoke()
        "lucide_code-xml" -> states.stopActions.getValue("lucide_code-xml").invoke()
        "lucide_coffee" -> states.stopActions.getValue("lucide_coffee").invoke()
        "lucide_coins" -> states.stopActions.getValue("lucide_coins").invoke()
        "lucide_columns-2" -> states.stopActions.getValue("lucide_columns-2").invoke()
        "lucide_columns-3" -> states.stopActions.getValue("lucide_columns-3").invoke()
        "lucide_columns-4" -> states.stopActions.getValue("lucide_columns-4").invoke()
        "lucide_command" -> states.stopActions.getValue("lucide_command").invoke()
        "lucide_compass" -> states.stopActions.getValue("lucide_compass").invoke()
        "lucide_component" -> states.stopActions.getValue("lucide_component").invoke()
        "lucide_contact" -> states.stopActions.getValue("lucide_contact").invoke()
        "lucide_contact-round" -> states.stopActions.getValue("lucide_contact-round").invoke()
        "lucide_contrast" -> states.stopActions.getValue("lucide_contrast").invoke()
        "lucide_cookie" -> states.stopActions.getValue("lucide_cookie").invoke()
        "lucide_copy-check" -> states.stopActions.getValue("lucide_copy-check").invoke()
        "lucide_copy" -> states.stopActions.getValue("lucide_copy").invoke()
        "lucide_copy-plus" -> states.stopActions.getValue("lucide_copy-plus").invoke()
        "lucide_corner-down-left" -> states.stopActions.getValue("lucide_corner-down-left").invoke()
        "lucide_corner-down-right" -> states.stopActions.getValue("lucide_corner-down-right").invoke()
        "lucide_corner-left-down" -> states.stopActions.getValue("lucide_corner-left-down").invoke()
        "lucide_corner-left-up" -> states.stopActions.getValue("lucide_corner-left-up").invoke()
        "lucide_corner-right-down" -> states.stopActions.getValue("lucide_corner-right-down").invoke()
        "lucide_corner-right-up" -> states.stopActions.getValue("lucide_corner-right-up").invoke()
        "lucide_corner-up-left" -> states.stopActions.getValue("lucide_corner-up-left").invoke()
        "lucide_corner-up-right" -> states.stopActions.getValue("lucide_corner-up-right").invoke()
        "lucide_cpu" -> states.stopActions.getValue("lucide_cpu").invoke()
        "lucide_credit-card-check" -> states.stopActions.getValue("lucide_credit-card-check").invoke()
        "lucide_credit-card" -> states.stopActions.getValue("lucide_credit-card").invoke()
        "lucide_credit-card-minus" -> states.stopActions.getValue("lucide_credit-card-minus").invoke()
        "lucide_credit-card-plus" -> states.stopActions.getValue("lucide_credit-card-plus").invoke()
        "lucide_credit-card-reader" -> states.stopActions.getValue("lucide_credit-card-reader").invoke()
        "lucide_credit-card-x" -> states.stopActions.getValue("lucide_credit-card-x").invoke()
        "lucide_crop" -> states.stopActions.getValue("lucide_crop").invoke()
        "lucide_crown" -> states.stopActions.getValue("lucide_crown").invoke()
        "lucide_database-arrow-down" -> states.stopActions.getValue("lucide_database-arrow-down").invoke()
        "lucide_database-arrow-up" -> states.stopActions.getValue("lucide_database-arrow-up").invoke()
        "lucide_database-backup" -> states.stopActions.getValue("lucide_database-backup").invoke()
        "lucide_database-check" -> states.stopActions.getValue("lucide_database-check").invoke()
        "lucide_database" -> states.stopActions.getValue("lucide_database").invoke()
        "lucide_database-minus" -> states.stopActions.getValue("lucide_database-minus").invoke()
        "lucide_database-plus" -> states.stopActions.getValue("lucide_database-plus").invoke()
        else -> Unit
    }
}

private fun lucideStopChunk3(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_database-search" -> states.stopActions.getValue("lucide_database-search").invoke()
        "lucide_database-x" -> states.stopActions.getValue("lucide_database-x").invoke()
        "lucide_database-zap" -> states.stopActions.getValue("lucide_database-zap").invoke()
        "lucide_delete" -> states.stopActions.getValue("lucide_delete").invoke()
        "lucide_diamond" -> states.stopActions.getValue("lucide_diamond").invoke()
        "lucide_diamond-minus" -> states.stopActions.getValue("lucide_diamond-minus").invoke()
        "lucide_diamond-percent" -> states.stopActions.getValue("lucide_diamond-percent").invoke()
        "lucide_diamond-plus" -> states.stopActions.getValue("lucide_diamond-plus").invoke()
        "lucide_dice-5" -> states.stopActions.getValue("lucide_dice-5").invoke()
        "lucide_diff" -> states.stopActions.getValue("lucide_diff").invoke()
        "lucide_disc-2" -> states.stopActions.getValue("lucide_disc-2").invoke()
        "lucide_disc-3" -> states.stopActions.getValue("lucide_disc-3").invoke()
        "lucide_disc-album" -> states.stopActions.getValue("lucide_disc-album").invoke()
        "lucide_disc" -> states.stopActions.getValue("lucide_disc").invoke()
        "lucide_dog" -> states.stopActions.getValue("lucide_dog").invoke()
        "lucide_dollar-sign" -> states.stopActions.getValue("lucide_dollar-sign").invoke()
        "lucide_door-open" -> states.stopActions.getValue("lucide_door-open").invoke()
        "lucide_download" -> states.stopActions.getValue("lucide_download").invoke()
        "lucide_droplet" -> states.stopActions.getValue("lucide_droplet").invoke()
        "lucide_droplets" -> states.stopActions.getValue("lucide_droplets").invoke()
        "lucide_drum" -> states.stopActions.getValue("lucide_drum").invoke()
        "lucide_dumbbell" -> states.stopActions.getValue("lucide_dumbbell").invoke()
        "lucide_ellipsis" -> states.stopActions.getValue("lucide_ellipsis").invoke()
        "lucide_ellipsis-vertical" -> states.stopActions.getValue("lucide_ellipsis-vertical").invoke()
        "lucide_eraser" -> states.stopActions.getValue("lucide_eraser").invoke()
        "lucide_euro" -> states.stopActions.getValue("lucide_euro").invoke()
        "lucide_expand" -> states.stopActions.getValue("lucide_expand").invoke()
        "lucide_external-link" -> states.stopActions.getValue("lucide_external-link").invoke()
        "lucide_eye-closed" -> states.stopActions.getValue("lucide_eye-closed").invoke()
        "lucide_eye" -> states.stopActions.getValue("lucide_eye").invoke()
        "lucide_eye-off" -> states.stopActions.getValue("lucide_eye-off").invoke()
        "lucide_facebook" -> states.stopActions.getValue("lucide_facebook").invoke()
        "lucide_factory" -> states.stopActions.getValue("lucide_factory").invoke()
        "lucide_fast-forward" -> states.stopActions.getValue("lucide_fast-forward").invoke()
        "lucide_feather" -> states.stopActions.getValue("lucide_feather").invoke()
        "lucide_figma" -> states.stopActions.getValue("lucide_figma").invoke()
        "lucide_file-archive" -> states.stopActions.getValue("lucide_file-archive").invoke()
        "lucide_file-check" -> states.stopActions.getValue("lucide_file-check").invoke()
        "lucide_file-clock" -> states.stopActions.getValue("lucide_file-clock").invoke()
        "lucide_file-code" -> states.stopActions.getValue("lucide_file-code").invoke()
        "lucide_file-down" -> states.stopActions.getValue("lucide_file-down").invoke()
        "lucide_file-heart" -> states.stopActions.getValue("lucide_file-heart").invoke()
        "lucide_file" -> states.stopActions.getValue("lucide_file").invoke()
        "lucide_file-image" -> states.stopActions.getValue("lucide_file-image").invoke()
        "lucide_file-key" -> states.stopActions.getValue("lucide_file-key").invoke()
        "lucide_file-lock" -> states.stopActions.getValue("lucide_file-lock").invoke()
        "lucide_file-minus" -> states.stopActions.getValue("lucide_file-minus").invoke()
        "lucide_file-music" -> states.stopActions.getValue("lucide_file-music").invoke()
        "lucide_file-pen" -> states.stopActions.getValue("lucide_file-pen").invoke()
        "lucide_file-plus" -> states.stopActions.getValue("lucide_file-plus").invoke()
        "lucide_file-scan" -> states.stopActions.getValue("lucide_file-scan").invoke()
        "lucide_file-search" -> states.stopActions.getValue("lucide_file-search").invoke()
        "lucide_file-spreadsheet" -> states.stopActions.getValue("lucide_file-spreadsheet").invoke()
        "lucide_file-terminal" -> states.stopActions.getValue("lucide_file-terminal").invoke()
        "lucide_file-text" -> states.stopActions.getValue("lucide_file-text").invoke()
        "lucide_file-type" -> states.stopActions.getValue("lucide_file-type").invoke()
        "lucide_file-up" -> states.stopActions.getValue("lucide_file-up").invoke()
        "lucide_file-x" -> states.stopActions.getValue("lucide_file-x").invoke()
        "lucide_files" -> states.stopActions.getValue("lucide_files").invoke()
        "lucide_film" -> states.stopActions.getValue("lucide_film").invoke()
        "lucide_filter" -> states.stopActions.getValue("lucide_filter").invoke()
        "lucide_fingerprint-pattern" -> states.stopActions.getValue("lucide_fingerprint-pattern").invoke()
        "lucide_fish" -> states.stopActions.getValue("lucide_fish").invoke()
        "lucide_flag" -> states.stopActions.getValue("lucide_flag").invoke()
        "lucide_flag-off" -> states.stopActions.getValue("lucide_flag-off").invoke()
        "lucide_flag-triangle-right" -> states.stopActions.getValue("lucide_flag-triangle-right").invoke()
        "lucide_flame" -> states.stopActions.getValue("lucide_flame").invoke()
        "lucide_flask-conical" -> states.stopActions.getValue("lucide_flask-conical").invoke()
        "lucide_fold-vertical" -> states.stopActions.getValue("lucide_fold-vertical").invoke()
        "lucide_folder-check" -> states.stopActions.getValue("lucide_folder-check").invoke()
        "lucide_folder-closed" -> states.stopActions.getValue("lucide_folder-closed").invoke()
        "lucide_folder-dot" -> states.stopActions.getValue("lucide_folder-dot").invoke()
        "lucide_folder-heart" -> states.stopActions.getValue("lucide_folder-heart").invoke()
        "lucide_folder" -> states.stopActions.getValue("lucide_folder").invoke()
        "lucide_folder-key" -> states.stopActions.getValue("lucide_folder-key").invoke()
        "lucide_folder-lock" -> states.stopActions.getValue("lucide_folder-lock").invoke()
        "lucide_folder-minus" -> states.stopActions.getValue("lucide_folder-minus").invoke()
        "lucide_folder-open" -> states.stopActions.getValue("lucide_folder-open").invoke()
        "lucide_folder-plus" -> states.stopActions.getValue("lucide_folder-plus").invoke()
        "lucide_folder-search" -> states.stopActions.getValue("lucide_folder-search").invoke()
        "lucide_folder-tree" -> states.stopActions.getValue("lucide_folder-tree").invoke()
        "lucide_folder-x" -> states.stopActions.getValue("lucide_folder-x").invoke()
        "lucide_footprints" -> states.stopActions.getValue("lucide_footprints").invoke()
        "lucide_framer" -> states.stopActions.getValue("lucide_framer").invoke()
        "lucide_fullscreen" -> states.stopActions.getValue("lucide_fullscreen").invoke()
        "lucide_funnel-x" -> states.stopActions.getValue("lucide_funnel-x").invoke()
        "lucide_gallery-horizontal" -> states.stopActions.getValue("lucide_gallery-horizontal").invoke()
        "lucide_gamepad" -> states.stopActions.getValue("lucide_gamepad").invoke()
        "lucide_gauge" -> states.stopActions.getValue("lucide_gauge").invoke()
        "lucide_gem" -> states.stopActions.getValue("lucide_gem").invoke()
        "lucide_georgian-lari" -> states.stopActions.getValue("lucide_georgian-lari").invoke()
        "lucide_ghost" -> states.stopActions.getValue("lucide_ghost").invoke()
        "lucide_gift" -> states.stopActions.getValue("lucide_gift").invoke()
        "lucide_git-branch" -> states.stopActions.getValue("lucide_git-branch").invoke()
        "lucide_git-branch-minus" -> states.stopActions.getValue("lucide_git-branch-minus").invoke()
        "lucide_git-branch-plus" -> states.stopActions.getValue("lucide_git-branch-plus").invoke()
        "lucide_git-commit-horizontal" -> states.stopActions.getValue("lucide_git-commit-horizontal").invoke()
        "lucide_git-commit-vertical" -> states.stopActions.getValue("lucide_git-commit-vertical").invoke()
        "lucide_git-compare-arrows" -> states.stopActions.getValue("lucide_git-compare-arrows").invoke()
        "lucide_git-compare" -> states.stopActions.getValue("lucide_git-compare").invoke()
        else -> Unit
    }
}

private fun lucideStopChunk4(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_git-fork" -> states.stopActions.getValue("lucide_git-fork").invoke()
        "lucide_git-graph" -> states.stopActions.getValue("lucide_git-graph").invoke()
        "lucide_git-merge-conflict" -> states.stopActions.getValue("lucide_git-merge-conflict").invoke()
        "lucide_git-merge" -> states.stopActions.getValue("lucide_git-merge").invoke()
        "lucide_git-pull-request-arrow" -> states.stopActions.getValue("lucide_git-pull-request-arrow").invoke()
        "lucide_git-pull-request-closed" -> states.stopActions.getValue("lucide_git-pull-request-closed").invoke()
        "lucide_git-pull-request-create-arrow" -> states.stopActions.getValue("lucide_git-pull-request-create-arrow").invoke()
        "lucide_git-pull-request-create" -> states.stopActions.getValue("lucide_git-pull-request-create").invoke()
        "lucide_git-pull-request-draft" -> states.stopActions.getValue("lucide_git-pull-request-draft").invoke()
        "lucide_git-pull-request" -> states.stopActions.getValue("lucide_git-pull-request").invoke()
        "lucide_github" -> states.stopActions.getValue("lucide_github").invoke()
        "lucide_gitlab" -> states.stopActions.getValue("lucide_gitlab").invoke()
        "lucide_globe" -> states.stopActions.getValue("lucide_globe").invoke()
        "lucide_globe-lock" -> states.stopActions.getValue("lucide_globe-lock").invoke()
        "lucide_graduation-cap" -> states.stopActions.getValue("lucide_graduation-cap").invoke()
        "lucide_grid-2x2-check" -> states.stopActions.getValue("lucide_grid-2x2-check").invoke()
        "lucide_grid-2x2" -> states.stopActions.getValue("lucide_grid-2x2").invoke()
        "lucide_grid-2x2-plus" -> states.stopActions.getValue("lucide_grid-2x2-plus").invoke()
        "lucide_grid-2x2-x" -> states.stopActions.getValue("lucide_grid-2x2-x").invoke()
        "lucide_grid-3x2" -> states.stopActions.getValue("lucide_grid-3x2").invoke()
        "lucide_grid-3x3" -> states.stopActions.getValue("lucide_grid-3x3").invoke()
        "lucide_grip-vertical" -> states.stopActions.getValue("lucide_grip-vertical").invoke()
        "lucide_guitar" -> states.stopActions.getValue("lucide_guitar").invoke()
        "lucide_hammer" -> states.stopActions.getValue("lucide_hammer").invoke()
        "lucide_hand-coins" -> states.stopActions.getValue("lucide_hand-coins").invoke()
        "lucide_hand-heart" -> states.stopActions.getValue("lucide_hand-heart").invoke()
        "lucide_hand" -> states.stopActions.getValue("lucide_hand").invoke()
        "lucide_handshake" -> states.stopActions.getValue("lucide_handshake").invoke()
        "lucide_hard-drive-download" -> states.stopActions.getValue("lucide_hard-drive-download").invoke()
        "lucide_hard-drive" -> states.stopActions.getValue("lucide_hard-drive").invoke()
        "lucide_hard-drive-upload" -> states.stopActions.getValue("lucide_hard-drive-upload").invoke()
        "lucide_hash" -> states.stopActions.getValue("lucide_hash").invoke()
        "lucide_haze" -> states.stopActions.getValue("lucide_haze").invoke()
        "lucide_heading-1" -> states.stopActions.getValue("lucide_heading-1").invoke()
        "lucide_heading-2" -> states.stopActions.getValue("lucide_heading-2").invoke()
        "lucide_heading-3" -> states.stopActions.getValue("lucide_heading-3").invoke()
        "lucide_heading-4" -> states.stopActions.getValue("lucide_heading-4").invoke()
        "lucide_heading-5" -> states.stopActions.getValue("lucide_heading-5").invoke()
        "lucide_heading-6" -> states.stopActions.getValue("lucide_heading-6").invoke()
        "lucide_heading" -> states.stopActions.getValue("lucide_heading").invoke()
        "lucide_headphone-off" -> states.stopActions.getValue("lucide_headphone-off").invoke()
        "lucide_headphones" -> states.stopActions.getValue("lucide_headphones").invoke()
        "lucide_headset" -> states.stopActions.getValue("lucide_headset").invoke()
        "lucide_heart-crack" -> states.stopActions.getValue("lucide_heart-crack").invoke()
        "lucide_heart-handshake" -> states.stopActions.getValue("lucide_heart-handshake").invoke()
        "lucide_heart" -> states.stopActions.getValue("lucide_heart").invoke()
        "lucide_heart-minus" -> states.stopActions.getValue("lucide_heart-minus").invoke()
        "lucide_heart-off" -> states.stopActions.getValue("lucide_heart-off").invoke()
        "lucide_heart-plus" -> states.stopActions.getValue("lucide_heart-plus").invoke()
        "lucide_heart-pulse" -> states.stopActions.getValue("lucide_heart-pulse").invoke()
        "lucide_heart-x" -> states.stopActions.getValue("lucide_heart-x").invoke()
        "lucide_hexagon" -> states.stopActions.getValue("lucide_hexagon").invoke()
        "lucide_highlighter" -> states.stopActions.getValue("lucide_highlighter").invoke()
        "lucide_history" -> states.stopActions.getValue("lucide_history").invoke()
        "lucide_hospital" -> states.stopActions.getValue("lucide_hospital").invoke()
        "lucide_hotel" -> states.stopActions.getValue("lucide_hotel").invoke()
        "lucide_hourglass" -> states.stopActions.getValue("lucide_hourglass").invoke()
        "lucide_house-cog" -> states.stopActions.getValue("lucide_house-cog").invoke()
        "lucide_house-heart" -> states.stopActions.getValue("lucide_house-heart").invoke()
        "lucide_house" -> states.stopActions.getValue("lucide_house").invoke()
        "lucide_house-plug" -> states.stopActions.getValue("lucide_house-plug").invoke()
        "lucide_house-plus" -> states.stopActions.getValue("lucide_house-plus").invoke()
        "lucide_house-wifi" -> states.stopActions.getValue("lucide_house-wifi").invoke()
        "lucide_image" -> states.stopActions.getValue("lucide_image").invoke()
        "lucide_image-off" -> states.stopActions.getValue("lucide_image-off").invoke()
        "lucide_image-plus" -> states.stopActions.getValue("lucide_image-plus").invoke()
        "lucide_inbox" -> states.stopActions.getValue("lucide_inbox").invoke()
        "lucide_indent-decrease" -> states.stopActions.getValue("lucide_indent-decrease").invoke()
        "lucide_indent-increase" -> states.stopActions.getValue("lucide_indent-increase").invoke()
        "lucide_indian-rupee" -> states.stopActions.getValue("lucide_indian-rupee").invoke()
        "lucide_infinity" -> states.stopActions.getValue("lucide_infinity").invoke()
        "lucide_info" -> states.stopActions.getValue("lucide_info").invoke()
        "lucide_instagram" -> states.stopActions.getValue("lucide_instagram").invoke()
        "lucide_italic" -> states.stopActions.getValue("lucide_italic").invoke()
        "lucide_japanese-yen" -> states.stopActions.getValue("lucide_japanese-yen").invoke()
        "lucide_kanban" -> states.stopActions.getValue("lucide_kanban").invoke()
        "lucide_key" -> states.stopActions.getValue("lucide_key").invoke()
        "lucide_key-round" -> states.stopActions.getValue("lucide_key-round").invoke()
        "lucide_key-square" -> states.stopActions.getValue("lucide_key-square").invoke()
        "lucide_keyboard" -> states.stopActions.getValue("lucide_keyboard").invoke()
        "lucide_landmark" -> states.stopActions.getValue("lucide_landmark").invoke()
        "lucide_languages" -> states.stopActions.getValue("lucide_languages").invoke()
        "lucide_laptop" -> states.stopActions.getValue("lucide_laptop").invoke()
        "lucide_laptop-minimal-check" -> states.stopActions.getValue("lucide_laptop-minimal-check").invoke()
        "lucide_laptop-minimal" -> states.stopActions.getValue("lucide_laptop-minimal").invoke()
        "lucide_layers" -> states.stopActions.getValue("lucide_layers").invoke()
        "lucide_layout-dashboard" -> states.stopActions.getValue("lucide_layout-dashboard").invoke()
        "lucide_layout-grid" -> states.stopActions.getValue("lucide_layout-grid").invoke()
        "lucide_layout-list" -> states.stopActions.getValue("lucide_layout-list").invoke()
        "lucide_layout-panel-left" -> states.stopActions.getValue("lucide_layout-panel-left").invoke()
        "lucide_layout-panel-top" -> states.stopActions.getValue("lucide_layout-panel-top").invoke()
        "lucide_layout-template" -> states.stopActions.getValue("lucide_layout-template").invoke()
        "lucide_leaf" -> states.stopActions.getValue("lucide_leaf").invoke()
        "lucide_life-buoy" -> states.stopActions.getValue("lucide_life-buoy").invoke()
        "lucide_lightbulb" -> states.stopActions.getValue("lucide_lightbulb").invoke()
        "lucide_link" -> states.stopActions.getValue("lucide_link").invoke()
        "lucide_linkedin" -> states.stopActions.getValue("lucide_linkedin").invoke()
        "lucide_list-check" -> states.stopActions.getValue("lucide_list-check").invoke()
        "lucide_list-checks" -> states.stopActions.getValue("lucide_list-checks").invoke()
        "lucide_list-chevrons-down-up" -> states.stopActions.getValue("lucide_list-chevrons-down-up").invoke()
        else -> Unit
    }
}

private fun lucideStopChunk5(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_list-chevrons-up-down" -> states.stopActions.getValue("lucide_list-chevrons-up-down").invoke()
        "lucide_list-collapse" -> states.stopActions.getValue("lucide_list-collapse").invoke()
        "lucide_list-end" -> states.stopActions.getValue("lucide_list-end").invoke()
        "lucide_list-filter" -> states.stopActions.getValue("lucide_list-filter").invoke()
        "lucide_list-filter-plus" -> states.stopActions.getValue("lucide_list-filter-plus").invoke()
        "lucide_list" -> states.stopActions.getValue("lucide_list").invoke()
        "lucide_list-minus" -> states.stopActions.getValue("lucide_list-minus").invoke()
        "lucide_list-ordered" -> states.stopActions.getValue("lucide_list-ordered").invoke()
        "lucide_list-plus" -> states.stopActions.getValue("lucide_list-plus").invoke()
        "lucide_list-start" -> states.stopActions.getValue("lucide_list-start").invoke()
        "lucide_list-todo" -> states.stopActions.getValue("lucide_list-todo").invoke()
        "lucide_list-tree" -> states.stopActions.getValue("lucide_list-tree").invoke()
        "lucide_list-x" -> states.stopActions.getValue("lucide_list-x").invoke()
        "lucide_loader-circle" -> states.stopActions.getValue("lucide_loader-circle").invoke()
        "lucide_loader" -> states.stopActions.getValue("lucide_loader").invoke()
        "lucide_locate-fixed" -> states.stopActions.getValue("lucide_locate-fixed").invoke()
        "lucide_locate" -> states.stopActions.getValue("lucide_locate").invoke()
        "lucide_locate-off" -> states.stopActions.getValue("lucide_locate-off").invoke()
        "lucide_lock" -> states.stopActions.getValue("lucide_lock").invoke()
        "lucide_lock-keyhole" -> states.stopActions.getValue("lucide_lock-keyhole").invoke()
        "lucide_lock-keyhole-open" -> states.stopActions.getValue("lucide_lock-keyhole-open").invoke()
        "lucide_lock-open" -> states.stopActions.getValue("lucide_lock-open").invoke()
        "lucide_log-in" -> states.stopActions.getValue("lucide_log-in").invoke()
        "lucide_log-out" -> states.stopActions.getValue("lucide_log-out").invoke()
        "lucide_magnet" -> states.stopActions.getValue("lucide_magnet").invoke()
        "lucide_mail-check" -> states.stopActions.getValue("lucide_mail-check").invoke()
        "lucide_mail-clock" -> states.stopActions.getValue("lucide_mail-clock").invoke()
        "lucide_mail" -> states.stopActions.getValue("lucide_mail").invoke()
        "lucide_mail-minus" -> states.stopActions.getValue("lucide_mail-minus").invoke()
        "lucide_mail-open" -> states.stopActions.getValue("lucide_mail-open").invoke()
        "lucide_mail-plus" -> states.stopActions.getValue("lucide_mail-plus").invoke()
        "lucide_mail-search" -> states.stopActions.getValue("lucide_mail-search").invoke()
        "lucide_mail-warning" -> states.stopActions.getValue("lucide_mail-warning").invoke()
        "lucide_mail-x" -> states.stopActions.getValue("lucide_mail-x").invoke()
        "lucide_mailbox" -> states.stopActions.getValue("lucide_mailbox").invoke()
        "lucide_mails" -> states.stopActions.getValue("lucide_mails").invoke()
        "lucide_map" -> states.stopActions.getValue("lucide_map").invoke()
        "lucide_map-minus" -> states.stopActions.getValue("lucide_map-minus").invoke()
        "lucide_map-pin-check" -> states.stopActions.getValue("lucide_map-pin-check").invoke()
        "lucide_map-pin-check-inside" -> states.stopActions.getValue("lucide_map-pin-check-inside").invoke()
        "lucide_map-pin-house" -> states.stopActions.getValue("lucide_map-pin-house").invoke()
        "lucide_map-pin" -> states.stopActions.getValue("lucide_map-pin").invoke()
        "lucide_map-pin-minus" -> states.stopActions.getValue("lucide_map-pin-minus").invoke()
        "lucide_map-pin-minus-inside" -> states.stopActions.getValue("lucide_map-pin-minus-inside").invoke()
        "lucide_map-pin-off" -> states.stopActions.getValue("lucide_map-pin-off").invoke()
        "lucide_map-pin-pen" -> states.stopActions.getValue("lucide_map-pin-pen").invoke()
        "lucide_map-pin-plus" -> states.stopActions.getValue("lucide_map-pin-plus").invoke()
        "lucide_map-pin-plus-inside" -> states.stopActions.getValue("lucide_map-pin-plus-inside").invoke()
        "lucide_map-pin-search" -> states.stopActions.getValue("lucide_map-pin-search").invoke()
        "lucide_map-pin-x" -> states.stopActions.getValue("lucide_map-pin-x").invoke()
        "lucide_map-pin-x-inside" -> states.stopActions.getValue("lucide_map-pin-x-inside").invoke()
        "lucide_map-pinned" -> states.stopActions.getValue("lucide_map-pinned").invoke()
        "lucide_map-plus" -> states.stopActions.getValue("lucide_map-plus").invoke()
        "lucide_mars" -> states.stopActions.getValue("lucide_mars").invoke()
        "lucide_maximize-2" -> states.stopActions.getValue("lucide_maximize-2").invoke()
        "lucide_maximize" -> states.stopActions.getValue("lucide_maximize").invoke()
        "lucide_medal" -> states.stopActions.getValue("lucide_medal").invoke()
        "lucide_megaphone" -> states.stopActions.getValue("lucide_megaphone").invoke()
        "lucide_memory-stick" -> states.stopActions.getValue("lucide_memory-stick").invoke()
        "lucide_menu" -> states.stopActions.getValue("lucide_menu").invoke()
        "lucide_message-circle-check" -> states.stopActions.getValue("lucide_message-circle-check").invoke()
        "lucide_message-circle-code" -> states.stopActions.getValue("lucide_message-circle-code").invoke()
        "lucide_message-circle-dashed-check" -> states.stopActions.getValue("lucide_message-circle-dashed-check").invoke()
        "lucide_message-circle-dashed" -> states.stopActions.getValue("lucide_message-circle-dashed").invoke()
        "lucide_message-circle-heart" -> states.stopActions.getValue("lucide_message-circle-heart").invoke()
        "lucide_message-circle" -> states.stopActions.getValue("lucide_message-circle").invoke()
        "lucide_message-circle-more" -> states.stopActions.getValue("lucide_message-circle-more").invoke()
        "lucide_message-circle-off" -> states.stopActions.getValue("lucide_message-circle-off").invoke()
        "lucide_message-circle-plus" -> states.stopActions.getValue("lucide_message-circle-plus").invoke()
        "lucide_message-circle-question-mark" -> states.stopActions.getValue("lucide_message-circle-question-mark").invoke()
        "lucide_message-circle-reply" -> states.stopActions.getValue("lucide_message-circle-reply").invoke()
        "lucide_message-circle-warning" -> states.stopActions.getValue("lucide_message-circle-warning").invoke()
        "lucide_message-circle-x" -> states.stopActions.getValue("lucide_message-circle-x").invoke()
        "lucide_message-square-check" -> states.stopActions.getValue("lucide_message-square-check").invoke()
        "lucide_message-square-code" -> states.stopActions.getValue("lucide_message-square-code").invoke()
        "lucide_message-square-dashed" -> states.stopActions.getValue("lucide_message-square-dashed").invoke()
        "lucide_message-square-diff" -> states.stopActions.getValue("lucide_message-square-diff").invoke()
        "lucide_message-square-dot" -> states.stopActions.getValue("lucide_message-square-dot").invoke()
        "lucide_message-square-heart" -> states.stopActions.getValue("lucide_message-square-heart").invoke()
        "lucide_message-square" -> states.stopActions.getValue("lucide_message-square").invoke()
        "lucide_message-square-lock" -> states.stopActions.getValue("lucide_message-square-lock").invoke()
        "lucide_message-square-more" -> states.stopActions.getValue("lucide_message-square-more").invoke()
        "lucide_message-square-off" -> states.stopActions.getValue("lucide_message-square-off").invoke()
        "lucide_message-square-plus" -> states.stopActions.getValue("lucide_message-square-plus").invoke()
        "lucide_message-square-quote" -> states.stopActions.getValue("lucide_message-square-quote").invoke()
        "lucide_message-square-reply" -> states.stopActions.getValue("lucide_message-square-reply").invoke()
        "lucide_message-square-share" -> states.stopActions.getValue("lucide_message-square-share").invoke()
        "lucide_message-square-text" -> states.stopActions.getValue("lucide_message-square-text").invoke()
        "lucide_message-square-warning" -> states.stopActions.getValue("lucide_message-square-warning").invoke()
        "lucide_message-square-x" -> states.stopActions.getValue("lucide_message-square-x").invoke()
        "lucide_messages-circle" -> states.stopActions.getValue("lucide_messages-circle").invoke()
        "lucide_messages-square" -> states.stopActions.getValue("lucide_messages-square").invoke()
        "lucide_mic-audio-lines" -> states.stopActions.getValue("lucide_mic-audio-lines").invoke()
        "lucide_mic" -> states.stopActions.getValue("lucide_mic").invoke()
        "lucide_mic-off" -> states.stopActions.getValue("lucide_mic-off").invoke()
        "lucide_mic-signal" -> states.stopActions.getValue("lucide_mic-signal").invoke()
        "lucide_mic-vocal" -> states.stopActions.getValue("lucide_mic-vocal").invoke()
        "lucide_microchip" -> states.stopActions.getValue("lucide_microchip").invoke()
        "lucide_milestone" -> states.stopActions.getValue("lucide_milestone").invoke()
        "lucide_minimize-2" -> states.stopActions.getValue("lucide_minimize-2").invoke()
        else -> Unit
    }
}

private fun lucideStopChunk6(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_minimize" -> states.stopActions.getValue("lucide_minimize").invoke()
        "lucide_minus" -> states.stopActions.getValue("lucide_minus").invoke()
        "lucide_monitor-check" -> states.stopActions.getValue("lucide_monitor-check").invoke()
        "lucide_monitor-cloud" -> states.stopActions.getValue("lucide_monitor-cloud").invoke()
        "lucide_monitor-cog" -> states.stopActions.getValue("lucide_monitor-cog").invoke()
        "lucide_monitor-dot" -> states.stopActions.getValue("lucide_monitor-dot").invoke()
        "lucide_monitor-down" -> states.stopActions.getValue("lucide_monitor-down").invoke()
        "lucide_monitor" -> states.stopActions.getValue("lucide_monitor").invoke()
        "lucide_monitor-off" -> states.stopActions.getValue("lucide_monitor-off").invoke()
        "lucide_monitor-pause" -> states.stopActions.getValue("lucide_monitor-pause").invoke()
        "lucide_monitor-pc" -> states.stopActions.getValue("lucide_monitor-pc").invoke()
        "lucide_monitor-play" -> states.stopActions.getValue("lucide_monitor-play").invoke()
        "lucide_monitor-smartphone" -> states.stopActions.getValue("lucide_monitor-smartphone").invoke()
        "lucide_monitor-speaker" -> states.stopActions.getValue("lucide_monitor-speaker").invoke()
        "lucide_monitor-stop" -> states.stopActions.getValue("lucide_monitor-stop").invoke()
        "lucide_monitor-up" -> states.stopActions.getValue("lucide_monitor-up").invoke()
        "lucide_monitor-x" -> states.stopActions.getValue("lucide_monitor-x").invoke()
        "lucide_moon" -> states.stopActions.getValue("lucide_moon").invoke()
        "lucide_moon-star" -> states.stopActions.getValue("lucide_moon-star").invoke()
        "lucide_mountain" -> states.stopActions.getValue("lucide_mountain").invoke()
        "lucide_mouse" -> states.stopActions.getValue("lucide_mouse").invoke()
        "lucide_mouse-pointer-click" -> states.stopActions.getValue("lucide_mouse-pointer-click").invoke()
        "lucide_mouse-pointer" -> states.stopActions.getValue("lucide_mouse-pointer").invoke()
        "lucide_move-diagonal-2" -> states.stopActions.getValue("lucide_move-diagonal-2").invoke()
        "lucide_move-diagonal" -> states.stopActions.getValue("lucide_move-diagonal").invoke()
        "lucide_move-down" -> states.stopActions.getValue("lucide_move-down").invoke()
        "lucide_move-down-left" -> states.stopActions.getValue("lucide_move-down-left").invoke()
        "lucide_move-down-right" -> states.stopActions.getValue("lucide_move-down-right").invoke()
        "lucide_move-horizontal" -> states.stopActions.getValue("lucide_move-horizontal").invoke()
        "lucide_move" -> states.stopActions.getValue("lucide_move").invoke()
        "lucide_move-left" -> states.stopActions.getValue("lucide_move-left").invoke()
        "lucide_move-right" -> states.stopActions.getValue("lucide_move-right").invoke()
        "lucide_move-up" -> states.stopActions.getValue("lucide_move-up").invoke()
        "lucide_move-up-left" -> states.stopActions.getValue("lucide_move-up-left").invoke()
        "lucide_move-up-right" -> states.stopActions.getValue("lucide_move-up-right").invoke()
        "lucide_move-vertical" -> states.stopActions.getValue("lucide_move-vertical").invoke()
        "lucide_music-2" -> states.stopActions.getValue("lucide_music-2").invoke()
        "lucide_music-3" -> states.stopActions.getValue("lucide_music-3").invoke()
        "lucide_music-4" -> states.stopActions.getValue("lucide_music-4").invoke()
        "lucide_music" -> states.stopActions.getValue("lucide_music").invoke()
        "lucide_navigation-2" -> states.stopActions.getValue("lucide_navigation-2").invoke()
        "lucide_navigation" -> states.stopActions.getValue("lucide_navigation").invoke()
        "lucide_network" -> states.stopActions.getValue("lucide_network").invoke()
        "lucide_nfc" -> states.stopActions.getValue("lucide_nfc").invoke()
        "lucide_notebook-pen" -> states.stopActions.getValue("lucide_notebook-pen").invoke()
        "lucide_octagon-alert" -> states.stopActions.getValue("lucide_octagon-alert").invoke()
        "lucide_octagon" -> states.stopActions.getValue("lucide_octagon").invoke()
        "lucide_octagon-minus" -> states.stopActions.getValue("lucide_octagon-minus").invoke()
        "lucide_octagon-pause" -> states.stopActions.getValue("lucide_octagon-pause").invoke()
        "lucide_octagon-x" -> states.stopActions.getValue("lucide_octagon-x").invoke()
        "lucide_orbit" -> states.stopActions.getValue("lucide_orbit").invoke()
        "lucide_package-check" -> states.stopActions.getValue("lucide_package-check").invoke()
        "lucide_package" -> states.stopActions.getValue("lucide_package").invoke()
        "lucide_package-open" -> states.stopActions.getValue("lucide_package-open").invoke()
        "lucide_package-plus" -> states.stopActions.getValue("lucide_package-plus").invoke()
        "lucide_package-search" -> states.stopActions.getValue("lucide_package-search").invoke()
        "lucide_package-x" -> states.stopActions.getValue("lucide_package-x").invoke()
        "lucide_paint-bucket" -> states.stopActions.getValue("lucide_paint-bucket").invoke()
        "lucide_paintbrush" -> states.stopActions.getValue("lucide_paintbrush").invoke()
        "lucide_palette" -> states.stopActions.getValue("lucide_palette").invoke()
        "lucide_panel-bottom-close" -> states.stopActions.getValue("lucide_panel-bottom-close").invoke()
        "lucide_panel-bottom-dashed" -> states.stopActions.getValue("lucide_panel-bottom-dashed").invoke()
        "lucide_panel-bottom" -> states.stopActions.getValue("lucide_panel-bottom").invoke()
        "lucide_panel-bottom-open" -> states.stopActions.getValue("lucide_panel-bottom-open").invoke()
        "lucide_panel-left-close" -> states.stopActions.getValue("lucide_panel-left-close").invoke()
        "lucide_panel-left-dashed" -> states.stopActions.getValue("lucide_panel-left-dashed").invoke()
        "lucide_panel-left" -> states.stopActions.getValue("lucide_panel-left").invoke()
        "lucide_panel-left-open" -> states.stopActions.getValue("lucide_panel-left-open").invoke()
        "lucide_panel-left-right-dashed" -> states.stopActions.getValue("lucide_panel-left-right-dashed").invoke()
        "lucide_panel-right-close" -> states.stopActions.getValue("lucide_panel-right-close").invoke()
        "lucide_panel-right-dashed" -> states.stopActions.getValue("lucide_panel-right-dashed").invoke()
        "lucide_panel-right" -> states.stopActions.getValue("lucide_panel-right").invoke()
        "lucide_panel-right-open" -> states.stopActions.getValue("lucide_panel-right-open").invoke()
        "lucide_panel-top-bottom-dashed" -> states.stopActions.getValue("lucide_panel-top-bottom-dashed").invoke()
        "lucide_panel-top-close" -> states.stopActions.getValue("lucide_panel-top-close").invoke()
        "lucide_panel-top-dashed" -> states.stopActions.getValue("lucide_panel-top-dashed").invoke()
        "lucide_panel-top" -> states.stopActions.getValue("lucide_panel-top").invoke()
        "lucide_panel-top-open" -> states.stopActions.getValue("lucide_panel-top-open").invoke()
        "lucide_paperclip" -> states.stopActions.getValue("lucide_paperclip").invoke()
        "lucide_party-popper" -> states.stopActions.getValue("lucide_party-popper").invoke()
        "lucide_pause" -> states.stopActions.getValue("lucide_pause").invoke()
        "lucide_paw-print" -> states.stopActions.getValue("lucide_paw-print").invoke()
        "lucide_pen-tool" -> states.stopActions.getValue("lucide_pen-tool").invoke()
        "lucide_pencil" -> states.stopActions.getValue("lucide_pencil").invoke()
        "lucide_pentagon" -> states.stopActions.getValue("lucide_pentagon").invoke()
        "lucide_percent" -> states.stopActions.getValue("lucide_percent").invoke()
        "lucide_philippine-peso" -> states.stopActions.getValue("lucide_philippine-peso").invoke()
        "lucide_phone-call" -> states.stopActions.getValue("lucide_phone-call").invoke()
        "lucide_phone-forwarded" -> states.stopActions.getValue("lucide_phone-forwarded").invoke()
        "lucide_phone" -> states.stopActions.getValue("lucide_phone").invoke()
        "lucide_phone-incoming" -> states.stopActions.getValue("lucide_phone-incoming").invoke()
        "lucide_phone-missed" -> states.stopActions.getValue("lucide_phone-missed").invoke()
        "lucide_phone-off" -> states.stopActions.getValue("lucide_phone-off").invoke()
        "lucide_piano" -> states.stopActions.getValue("lucide_piano").invoke()
        "lucide_picture-in-picture" -> states.stopActions.getValue("lucide_picture-in-picture").invoke()
        "lucide_piggy-bank" -> states.stopActions.getValue("lucide_piggy-bank").invoke()
        "lucide_pilcrow" -> states.stopActions.getValue("lucide_pilcrow").invoke()
        "lucide_pilcrow-left" -> states.stopActions.getValue("lucide_pilcrow-left").invoke()
        "lucide_pilcrow-right" -> states.stopActions.getValue("lucide_pilcrow-right").invoke()
        "lucide_pill" -> states.stopActions.getValue("lucide_pill").invoke()
        else -> Unit
    }
}

private fun lucideStopChunk7(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_pin" -> states.stopActions.getValue("lucide_pin").invoke()
        "lucide_pin-off" -> states.stopActions.getValue("lucide_pin-off").invoke()
        "lucide_pipette" -> states.stopActions.getValue("lucide_pipette").invoke()
        "lucide_plane" -> states.stopActions.getValue("lucide_plane").invoke()
        "lucide_play" -> states.stopActions.getValue("lucide_play").invoke()
        "lucide_play-off" -> states.stopActions.getValue("lucide_play-off").invoke()
        "lucide_plug-2" -> states.stopActions.getValue("lucide_plug-2").invoke()
        "lucide_plug" -> states.stopActions.getValue("lucide_plug").invoke()
        "lucide_plug-zap" -> states.stopActions.getValue("lucide_plug-zap").invoke()
        "lucide_plus" -> states.stopActions.getValue("lucide_plus").invoke()
        "lucide_podcast" -> states.stopActions.getValue("lucide_podcast").invoke()
        "lucide_pound-sterling" -> states.stopActions.getValue("lucide_pound-sterling").invoke()
        "lucide_power" -> states.stopActions.getValue("lucide_power").invoke()
        "lucide_power-off" -> states.stopActions.getValue("lucide_power-off").invoke()
        "lucide_presentation" -> states.stopActions.getValue("lucide_presentation").invoke()
        "lucide_printer" -> states.stopActions.getValue("lucide_printer").invoke()
        "lucide_puzzle" -> states.stopActions.getValue("lucide_puzzle").invoke()
        "lucide_qr-code" -> states.stopActions.getValue("lucide_qr-code").invoke()
        "lucide_quote" -> states.stopActions.getValue("lucide_quote").invoke()
        "lucide_rabbit" -> states.stopActions.getValue("lucide_rabbit").invoke()
        "lucide_radar" -> states.stopActions.getValue("lucide_radar").invoke()
        "lucide_radio" -> states.stopActions.getValue("lucide_radio").invoke()
        "lucide_radio-off" -> states.stopActions.getValue("lucide_radio-off").invoke()
        "lucide_radio-receiver" -> states.stopActions.getValue("lucide_radio-receiver").invoke()
        "lucide_rainbow" -> states.stopActions.getValue("lucide_rainbow").invoke()
        "lucide_receipt" -> states.stopActions.getValue("lucide_receipt").invoke()
        "lucide_receipt-text" -> states.stopActions.getValue("lucide_receipt-text").invoke()
        "lucide_recycle" -> states.stopActions.getValue("lucide_recycle").invoke()
        "lucide_redo" -> states.stopActions.getValue("lucide_redo").invoke()
        "lucide_refresh-ccw-dot" -> states.stopActions.getValue("lucide_refresh-ccw-dot").invoke()
        "lucide_refresh-ccw" -> states.stopActions.getValue("lucide_refresh-ccw").invoke()
        "lucide_refresh-cw" -> states.stopActions.getValue("lucide_refresh-cw").invoke()
        "lucide_refresh-cw-off" -> states.stopActions.getValue("lucide_refresh-cw-off").invoke()
        "lucide_regex" -> states.stopActions.getValue("lucide_regex").invoke()
        "lucide_remove-formatting" -> states.stopActions.getValue("lucide_remove-formatting").invoke()
        "lucide_repeat-1" -> states.stopActions.getValue("lucide_repeat-1").invoke()
        "lucide_repeat-2" -> states.stopActions.getValue("lucide_repeat-2").invoke()
        "lucide_repeat" -> states.stopActions.getValue("lucide_repeat").invoke()
        "lucide_reply-all" -> states.stopActions.getValue("lucide_reply-all").invoke()
        "lucide_reply" -> states.stopActions.getValue("lucide_reply").invoke()
        "lucide_rewind" -> states.stopActions.getValue("lucide_rewind").invoke()
        "lucide_rocket" -> states.stopActions.getValue("lucide_rocket").invoke()
        "lucide_rotate-ccw" -> states.stopActions.getValue("lucide_rotate-ccw").invoke()
        "lucide_rotate-cw" -> states.stopActions.getValue("lucide_rotate-cw").invoke()
        "lucide_route" -> states.stopActions.getValue("lucide_route").invoke()
        "lucide_route-off" -> states.stopActions.getValue("lucide_route-off").invoke()
        "lucide_router" -> states.stopActions.getValue("lucide_router").invoke()
        "lucide_rows-2" -> states.stopActions.getValue("lucide_rows-2").invoke()
        "lucide_rows-3" -> states.stopActions.getValue("lucide_rows-3").invoke()
        "lucide_rows-4" -> states.stopActions.getValue("lucide_rows-4").invoke()
        "lucide_rss" -> states.stopActions.getValue("lucide_rss").invoke()
        "lucide_ruler" -> states.stopActions.getValue("lucide_ruler").invoke()
        "lucide_russian-ruble" -> states.stopActions.getValue("lucide_russian-ruble").invoke()
        "lucide_saudi-riyal" -> states.stopActions.getValue("lucide_saudi-riyal").invoke()
        "lucide_save" -> states.stopActions.getValue("lucide_save").invoke()
        "lucide_scan" -> states.stopActions.getValue("lucide_scan").invoke()
        "lucide_scan-line" -> states.stopActions.getValue("lucide_scan-line").invoke()
        "lucide_scan-qr-code" -> states.stopActions.getValue("lucide_scan-qr-code").invoke()
        "lucide_school" -> states.stopActions.getValue("lucide_school").invoke()
        "lucide_scissors" -> states.stopActions.getValue("lucide_scissors").invoke()
        "lucide_screen-share" -> states.stopActions.getValue("lucide_screen-share").invoke()
        "lucide_scroll-text" -> states.stopActions.getValue("lucide_scroll-text").invoke()
        "lucide_search" -> states.stopActions.getValue("lucide_search").invoke()
        "lucide_search-x" -> states.stopActions.getValue("lucide_search-x").invoke()
        "lucide_send-horizontal" -> states.stopActions.getValue("lucide_send-horizontal").invoke()
        "lucide_send" -> states.stopActions.getValue("lucide_send").invoke()
        "lucide_server-cog" -> states.stopActions.getValue("lucide_server-cog").invoke()
        "lucide_server-crash" -> states.stopActions.getValue("lucide_server-crash").invoke()
        "lucide_server" -> states.stopActions.getValue("lucide_server").invoke()
        "lucide_server-off" -> states.stopActions.getValue("lucide_server-off").invoke()
        "lucide_server-plus" -> states.stopActions.getValue("lucide_server-plus").invoke()
        "lucide_settings" -> states.stopActions.getValue("lucide_settings").invoke()
        "lucide_shapes" -> states.stopActions.getValue("lucide_shapes").invoke()
        "lucide_share" -> states.stopActions.getValue("lucide_share").invoke()
        "lucide_shield-alert" -> states.stopActions.getValue("lucide_shield-alert").invoke()
        "lucide_shield-ban" -> states.stopActions.getValue("lucide_shield-ban").invoke()
        "lucide_shield-check" -> states.stopActions.getValue("lucide_shield-check").invoke()
        "lucide_shield-cog-corner" -> states.stopActions.getValue("lucide_shield-cog-corner").invoke()
        "lucide_shield-cog" -> states.stopActions.getValue("lucide_shield-cog").invoke()
        "lucide_shield-ellipsis" -> states.stopActions.getValue("lucide_shield-ellipsis").invoke()
        "lucide_shield-half" -> states.stopActions.getValue("lucide_shield-half").invoke()
        "lucide_shield" -> states.stopActions.getValue("lucide_shield").invoke()
        "lucide_shield-keyhole" -> states.stopActions.getValue("lucide_shield-keyhole").invoke()
        "lucide_shield-lock" -> states.stopActions.getValue("lucide_shield-lock").invoke()
        "lucide_shield-minus" -> states.stopActions.getValue("lucide_shield-minus").invoke()
        "lucide_shield-off" -> states.stopActions.getValue("lucide_shield-off").invoke()
        "lucide_shield-plus" -> states.stopActions.getValue("lucide_shield-plus").invoke()
        "lucide_shield-question-mark" -> states.stopActions.getValue("lucide_shield-question-mark").invoke()
        "lucide_shield-user" -> states.stopActions.getValue("lucide_shield-user").invoke()
        "lucide_shield-x" -> states.stopActions.getValue("lucide_shield-x").invoke()
        "lucide_shopping-bag" -> states.stopActions.getValue("lucide_shopping-bag").invoke()
        "lucide_shopping-basket" -> states.stopActions.getValue("lucide_shopping-basket").invoke()
        "lucide_shopping-cart" -> states.stopActions.getValue("lucide_shopping-cart").invoke()
        "lucide_shopping-cart-minus" -> states.stopActions.getValue("lucide_shopping-cart-minus").invoke()
        "lucide_shopping-cart-plus" -> states.stopActions.getValue("lucide_shopping-cart-plus").invoke()
        "lucide_shrink" -> states.stopActions.getValue("lucide_shrink").invoke()
        "lucide_shuffle" -> states.stopActions.getValue("lucide_shuffle").invoke()
        "lucide_sigma" -> states.stopActions.getValue("lucide_sigma").invoke()
        "lucide_signal-high" -> states.stopActions.getValue("lucide_signal-high").invoke()
        "lucide_signal" -> states.stopActions.getValue("lucide_signal").invoke()
        else -> Unit
    }
}

private fun lucideStopChunk8(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_signal-low" -> states.stopActions.getValue("lucide_signal-low").invoke()
        "lucide_signal-medium" -> states.stopActions.getValue("lucide_signal-medium").invoke()
        "lucide_signal-zero" -> states.stopActions.getValue("lucide_signal-zero").invoke()
        "lucide_signature" -> states.stopActions.getValue("lucide_signature").invoke()
        "lucide_signpost-big" -> states.stopActions.getValue("lucide_signpost-big").invoke()
        "lucide_signpost" -> states.stopActions.getValue("lucide_signpost").invoke()
        "lucide_siren" -> states.stopActions.getValue("lucide_siren").invoke()
        "lucide_skip-back" -> states.stopActions.getValue("lucide_skip-back").invoke()
        "lucide_skip-forward" -> states.stopActions.getValue("lucide_skip-forward").invoke()
        "lucide_sliders-horizontal" -> states.stopActions.getValue("lucide_sliders-horizontal").invoke()
        "lucide_smartphone-charging" -> states.stopActions.getValue("lucide_smartphone-charging").invoke()
        "lucide_smartphone" -> states.stopActions.getValue("lucide_smartphone").invoke()
        "lucide_smartphone-nfc" -> states.stopActions.getValue("lucide_smartphone-nfc").invoke()
        "lucide_snowflake" -> states.stopActions.getValue("lucide_snowflake").invoke()
        "lucide_sparkles" -> states.stopActions.getValue("lucide_sparkles").invoke()
        "lucide_speaker" -> states.stopActions.getValue("lucide_speaker").invoke()
        "lucide_spell-check" -> states.stopActions.getValue("lucide_spell-check").invoke()
        "lucide_spray-can" -> states.stopActions.getValue("lucide_spray-can").invoke()
        "lucide_sprout" -> states.stopActions.getValue("lucide_sprout").invoke()
        "lucide_square-arrow-down" -> states.stopActions.getValue("lucide_square-arrow-down").invoke()
        "lucide_square-arrow-down-left" -> states.stopActions.getValue("lucide_square-arrow-down-left").invoke()
        "lucide_square-arrow-down-right" -> states.stopActions.getValue("lucide_square-arrow-down-right").invoke()
        "lucide_square-arrow-left" -> states.stopActions.getValue("lucide_square-arrow-left").invoke()
        "lucide_square-arrow-out-down-left" -> states.stopActions.getValue("lucide_square-arrow-out-down-left").invoke()
        "lucide_square-arrow-out-down-right" -> states.stopActions.getValue("lucide_square-arrow-out-down-right").invoke()
        "lucide_square-arrow-out-up-left" -> states.stopActions.getValue("lucide_square-arrow-out-up-left").invoke()
        "lucide_square-arrow-out-up-right" -> states.stopActions.getValue("lucide_square-arrow-out-up-right").invoke()
        "lucide_square-arrow-right-enter" -> states.stopActions.getValue("lucide_square-arrow-right-enter").invoke()
        "lucide_square-arrow-right-exit" -> states.stopActions.getValue("lucide_square-arrow-right-exit").invoke()
        "lucide_square-arrow-right" -> states.stopActions.getValue("lucide_square-arrow-right").invoke()
        "lucide_square-arrow-up" -> states.stopActions.getValue("lucide_square-arrow-up").invoke()
        "lucide_square-arrow-up-left" -> states.stopActions.getValue("lucide_square-arrow-up-left").invoke()
        "lucide_square-arrow-up-right" -> states.stopActions.getValue("lucide_square-arrow-up-right").invoke()
        "lucide_square-check-big" -> states.stopActions.getValue("lucide_square-check-big").invoke()
        "lucide_square-check" -> states.stopActions.getValue("lucide_square-check").invoke()
        "lucide_square-chevron-down" -> states.stopActions.getValue("lucide_square-chevron-down").invoke()
        "lucide_square-chevron-left" -> states.stopActions.getValue("lucide_square-chevron-left").invoke()
        "lucide_square-chevron-right" -> states.stopActions.getValue("lucide_square-chevron-right").invoke()
        "lucide_square-chevron-up" -> states.stopActions.getValue("lucide_square-chevron-up").invoke()
        "lucide_square-code" -> states.stopActions.getValue("lucide_square-code").invoke()
        "lucide_square-dashed" -> states.stopActions.getValue("lucide_square-dashed").invoke()
        "lucide_square-dot" -> states.stopActions.getValue("lucide_square-dot").invoke()
        "lucide_square" -> states.stopActions.getValue("lucide_square").invoke()
        "lucide_square-minus" -> states.stopActions.getValue("lucide_square-minus").invoke()
        "lucide_square-pause" -> states.stopActions.getValue("lucide_square-pause").invoke()
        "lucide_square-pen" -> states.stopActions.getValue("lucide_square-pen").invoke()
        "lucide_square-play" -> states.stopActions.getValue("lucide_square-play").invoke()
        "lucide_square-plus" -> states.stopActions.getValue("lucide_square-plus").invoke()
        "lucide_square-slash" -> states.stopActions.getValue("lucide_square-slash").invoke()
        "lucide_square-stop" -> states.stopActions.getValue("lucide_square-stop").invoke()
        "lucide_square-terminal" -> states.stopActions.getValue("lucide_square-terminal").invoke()
        "lucide_square-user" -> states.stopActions.getValue("lucide_square-user").invoke()
        "lucide_square-user-round" -> states.stopActions.getValue("lucide_square-user-round").invoke()
        "lucide_square-x" -> states.stopActions.getValue("lucide_square-x").invoke()
        "lucide_stamp" -> states.stopActions.getValue("lucide_stamp").invoke()
        "lucide_star-check" -> states.stopActions.getValue("lucide_star-check").invoke()
        "lucide_star-half" -> states.stopActions.getValue("lucide_star-half").invoke()
        "lucide_star" -> states.stopActions.getValue("lucide_star").invoke()
        "lucide_star-minus" -> states.stopActions.getValue("lucide_star-minus").invoke()
        "lucide_star-off" -> states.stopActions.getValue("lucide_star-off").invoke()
        "lucide_star-plus" -> states.stopActions.getValue("lucide_star-plus").invoke()
        "lucide_star-x" -> states.stopActions.getValue("lucide_star-x").invoke()
        "lucide_step-back" -> states.stopActions.getValue("lucide_step-back").invoke()
        "lucide_step-forward" -> states.stopActions.getValue("lucide_step-forward").invoke()
        "lucide_stethoscope" -> states.stopActions.getValue("lucide_stethoscope").invoke()
        "lucide_sticky-note" -> states.stopActions.getValue("lucide_sticky-note").invoke()
        "lucide_store" -> states.stopActions.getValue("lucide_store").invoke()
        "lucide_strikethrough" -> states.stopActions.getValue("lucide_strikethrough").invoke()
        "lucide_subscript" -> states.stopActions.getValue("lucide_subscript").invoke()
        "lucide_sun-dim" -> states.stopActions.getValue("lucide_sun-dim").invoke()
        "lucide_sun" -> states.stopActions.getValue("lucide_sun").invoke()
        "lucide_sun-medium" -> states.stopActions.getValue("lucide_sun-medium").invoke()
        "lucide_sun-moon" -> states.stopActions.getValue("lucide_sun-moon").invoke()
        "lucide_sun-snow" -> states.stopActions.getValue("lucide_sun-snow").invoke()
        "lucide_sunrise" -> states.stopActions.getValue("lucide_sunrise").invoke()
        "lucide_sunset" -> states.stopActions.getValue("lucide_sunset").invoke()
        "lucide_superscript" -> states.stopActions.getValue("lucide_superscript").invoke()
        "lucide_swiss-franc" -> states.stopActions.getValue("lucide_swiss-franc").invoke()
        "lucide_swords" -> states.stopActions.getValue("lucide_swords").invoke()
        "lucide_table" -> states.stopActions.getValue("lucide_table").invoke()
        "lucide_tablet" -> states.stopActions.getValue("lucide_tablet").invoke()
        "lucide_tag" -> states.stopActions.getValue("lucide_tag").invoke()
        "lucide_tag-plus" -> states.stopActions.getValue("lucide_tag-plus").invoke()
        "lucide_tag-x" -> states.stopActions.getValue("lucide_tag-x").invoke()
        "lucide_tags" -> states.stopActions.getValue("lucide_tags").invoke()
        "lucide_target" -> states.stopActions.getValue("lucide_target").invoke()
        "lucide_telescope" -> states.stopActions.getValue("lucide_telescope").invoke()
        "lucide_tent" -> states.stopActions.getValue("lucide_tent").invoke()
        "lucide_terminal" -> states.stopActions.getValue("lucide_terminal").invoke()
        "lucide_text-cursor" -> states.stopActions.getValue("lucide_text-cursor").invoke()
        "lucide_text-initial" -> states.stopActions.getValue("lucide_text-initial").invoke()
        "lucide_text-quote" -> states.stopActions.getValue("lucide_text-quote").invoke()
        "lucide_text-search" -> states.stopActions.getValue("lucide_text-search").invoke()
        "lucide_thermometer" -> states.stopActions.getValue("lucide_thermometer").invoke()
        "lucide_thermometer-sun" -> states.stopActions.getValue("lucide_thermometer-sun").invoke()
        "lucide_thumbs-down" -> states.stopActions.getValue("lucide_thumbs-down").invoke()
        "lucide_thumbs-up" -> states.stopActions.getValue("lucide_thumbs-up").invoke()
        "lucide_ticket-check" -> states.stopActions.getValue("lucide_ticket-check").invoke()
        "lucide_ticket" -> states.stopActions.getValue("lucide_ticket").invoke()
        "lucide_ticket-minus" -> states.stopActions.getValue("lucide_ticket-minus").invoke()
        else -> Unit
    }
}

private fun lucideStopChunk9(id: String, states: LucideIconStates) {
    when (id) {
        "lucide_ticket-percent" -> states.stopActions.getValue("lucide_ticket-percent").invoke()
        "lucide_ticket-plus" -> states.stopActions.getValue("lucide_ticket-plus").invoke()
        "lucide_ticket-slash" -> states.stopActions.getValue("lucide_ticket-slash").invoke()
        "lucide_ticket-x" -> states.stopActions.getValue("lucide_ticket-x").invoke()
        "lucide_timer" -> states.stopActions.getValue("lucide_timer").invoke()
        "lucide_timer-off" -> states.stopActions.getValue("lucide_timer-off").invoke()
        "lucide_timer-reset" -> states.stopActions.getValue("lucide_timer-reset").invoke()
        "lucide_toggle-left" -> states.stopActions.getValue("lucide_toggle-left").invoke()
        "lucide_toggle-right" -> states.stopActions.getValue("lucide_toggle-right").invoke()
        "lucide_tornado" -> states.stopActions.getValue("lucide_tornado").invoke()
        "lucide_traffic-cone" -> states.stopActions.getValue("lucide_traffic-cone").invoke()
        "lucide_trash-2" -> states.stopActions.getValue("lucide_trash-2").invoke()
        "lucide_trash" -> states.stopActions.getValue("lucide_trash").invoke()
        "lucide_trending-down" -> states.stopActions.getValue("lucide_trending-down").invoke()
        "lucide_trending-up-down" -> states.stopActions.getValue("lucide_trending-up-down").invoke()
        "lucide_trending-up" -> states.stopActions.getValue("lucide_trending-up").invoke()
        "lucide_triangle-alert" -> states.stopActions.getValue("lucide_triangle-alert").invoke()
        "lucide_triangle-dashed" -> states.stopActions.getValue("lucide_triangle-dashed").invoke()
        "lucide_triangle" -> states.stopActions.getValue("lucide_triangle").invoke()
        "lucide_triangle-right" -> states.stopActions.getValue("lucide_triangle-right").invoke()
        "lucide_trophy" -> states.stopActions.getValue("lucide_trophy").invoke()
        "lucide_truck" -> states.stopActions.getValue("lucide_truck").invoke()
        "lucide_turkish-lira" -> states.stopActions.getValue("lucide_turkish-lira").invoke()
        "lucide_tv" -> states.stopActions.getValue("lucide_tv").invoke()
        "lucide_tv-minimal" -> states.stopActions.getValue("lucide_tv-minimal").invoke()
        "lucide_twitter" -> states.stopActions.getValue("lucide_twitter").invoke()
        "lucide_type" -> states.stopActions.getValue("lucide_type").invoke()
        "lucide_umbrella" -> states.stopActions.getValue("lucide_umbrella").invoke()
        "lucide_underline" -> states.stopActions.getValue("lucide_underline").invoke()
        "lucide_undo" -> states.stopActions.getValue("lucide_undo").invoke()
        "lucide_unfold-vertical" -> states.stopActions.getValue("lucide_unfold-vertical").invoke()
        "lucide_university" -> states.stopActions.getValue("lucide_university").invoke()
        "lucide_unlink" -> states.stopActions.getValue("lucide_unlink").invoke()
        "lucide_upload" -> states.stopActions.getValue("lucide_upload").invoke()
        "lucide_usb" -> states.stopActions.getValue("lucide_usb").invoke()
        "lucide_user-check" -> states.stopActions.getValue("lucide_user-check").invoke()
        "lucide_user-cog" -> states.stopActions.getValue("lucide_user-cog").invoke()
        "lucide_user-group" -> states.stopActions.getValue("lucide_user-group").invoke()
        "lucide_user" -> states.stopActions.getValue("lucide_user").invoke()
        "lucide_user-key" -> states.stopActions.getValue("lucide_user-key").invoke()
        "lucide_user-lock" -> states.stopActions.getValue("lucide_user-lock").invoke()
        "lucide_user-minus" -> states.stopActions.getValue("lucide_user-minus").invoke()
        "lucide_user-pen" -> states.stopActions.getValue("lucide_user-pen").invoke()
        "lucide_user-plus" -> states.stopActions.getValue("lucide_user-plus").invoke()
        "lucide_user-round-arrow-left" -> states.stopActions.getValue("lucide_user-round-arrow-left").invoke()
        "lucide_user-round-check" -> states.stopActions.getValue("lucide_user-round-check").invoke()
        "lucide_user-round-cog" -> states.stopActions.getValue("lucide_user-round-cog").invoke()
        "lucide_user-round-group" -> states.stopActions.getValue("lucide_user-round-group").invoke()
        "lucide_user-round" -> states.stopActions.getValue("lucide_user-round").invoke()
        "lucide_user-round-key" -> states.stopActions.getValue("lucide_user-round-key").invoke()
        "lucide_user-round-minus" -> states.stopActions.getValue("lucide_user-round-minus").invoke()
        "lucide_user-round-pen" -> states.stopActions.getValue("lucide_user-round-pen").invoke()
        "lucide_user-round-plus" -> states.stopActions.getValue("lucide_user-round-plus").invoke()
        "lucide_user-round-search" -> states.stopActions.getValue("lucide_user-round-search").invoke()
        "lucide_user-round-x" -> states.stopActions.getValue("lucide_user-round-x").invoke()
        "lucide_user-search" -> states.stopActions.getValue("lucide_user-search").invoke()
        "lucide_user-shield" -> states.stopActions.getValue("lucide_user-shield").invoke()
        "lucide_user-star" -> states.stopActions.getValue("lucide_user-star").invoke()
        "lucide_user-x" -> states.stopActions.getValue("lucide_user-x").invoke()
        "lucide_users" -> states.stopActions.getValue("lucide_users").invoke()
        "lucide_users-round" -> states.stopActions.getValue("lucide_users-round").invoke()
        "lucide_utensils" -> states.stopActions.getValue("lucide_utensils").invoke()
        "lucide_variable" -> states.stopActions.getValue("lucide_variable").invoke()
        "lucide_vault" -> states.stopActions.getValue("lucide_vault").invoke()
        "lucide_venus" -> states.stopActions.getValue("lucide_venus").invoke()
        "lucide_video" -> states.stopActions.getValue("lucide_video").invoke()
        "lucide_video-off" -> states.stopActions.getValue("lucide_video-off").invoke()
        "lucide_volume-1" -> states.stopActions.getValue("lucide_volume-1").invoke()
        "lucide_volume-2" -> states.stopActions.getValue("lucide_volume-2").invoke()
        "lucide_volume" -> states.stopActions.getValue("lucide_volume").invoke()
        "lucide_volume-off" -> states.stopActions.getValue("lucide_volume-off").invoke()
        "lucide_volume-x" -> states.stopActions.getValue("lucide_volume-x").invoke()
        "lucide_wallet-cards" -> states.stopActions.getValue("lucide_wallet-cards").invoke()
        "lucide_wallet" -> states.stopActions.getValue("lucide_wallet").invoke()
        "lucide_wallet-minimal" -> states.stopActions.getValue("lucide_wallet-minimal").invoke()
        "lucide_wand-sparkles" -> states.stopActions.getValue("lucide_wand-sparkles").invoke()
        "lucide_warehouse" -> states.stopActions.getValue("lucide_warehouse").invoke()
        "lucide_watch" -> states.stopActions.getValue("lucide_watch").invoke()
        "lucide_waypoints" -> states.stopActions.getValue("lucide_waypoints").invoke()
        "lucide_webcam" -> states.stopActions.getValue("lucide_webcam").invoke()
        "lucide_webcam-off" -> states.stopActions.getValue("lucide_webcam-off").invoke()
        "lucide_webhook" -> states.stopActions.getValue("lucide_webhook").invoke()
        "lucide_webhook-off" -> states.stopActions.getValue("lucide_webhook-off").invoke()
        "lucide_wifi-cog" -> states.stopActions.getValue("lucide_wifi-cog").invoke()
        "lucide_wifi-high" -> states.stopActions.getValue("lucide_wifi-high").invoke()
        "lucide_wifi" -> states.stopActions.getValue("lucide_wifi").invoke()
        "lucide_wifi-low" -> states.stopActions.getValue("lucide_wifi-low").invoke()
        "lucide_wifi-off" -> states.stopActions.getValue("lucide_wifi-off").invoke()
        "lucide_wifi-pen" -> states.stopActions.getValue("lucide_wifi-pen").invoke()
        "lucide_wifi-sync" -> states.stopActions.getValue("lucide_wifi-sync").invoke()
        "lucide_wifi-zero" -> states.stopActions.getValue("lucide_wifi-zero").invoke()
        "lucide_wind" -> states.stopActions.getValue("lucide_wind").invoke()
        "lucide_workflow" -> states.stopActions.getValue("lucide_workflow").invoke()
        "lucide_wrap-text" -> states.stopActions.getValue("lucide_wrap-text").invoke()
        "lucide_wrench" -> states.stopActions.getValue("lucide_wrench").invoke()
        "lucide_x" -> states.stopActions.getValue("lucide_x").invoke()
        "lucide_zap" -> states.stopActions.getValue("lucide_zap").invoke()
        "lucide_zoom-in" -> states.stopActions.getValue("lucide_zoom-in").invoke()
        "lucide_zoom-out" -> states.stopActions.getValue("lucide_zoom-out").invoke()
        else -> Unit
    }
}

