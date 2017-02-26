package MicrowaveOvenSPL.oven

import MicrowaveOvenSPL.configure.OptionalFeatures

sealed class Light extends OptionalFeatures
case object LOn extends Light
case object LOff extends Light