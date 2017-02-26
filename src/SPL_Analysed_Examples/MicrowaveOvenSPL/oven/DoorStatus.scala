package MicrowaveOvenSPL.oven

sealed trait DoorStatus
case object Open extends DoorStatus
case object Close extends DoorStatus