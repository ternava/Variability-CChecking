package SPL_Analysed_Examples.MicrowaveOvenSPL

import SPL_Analysed_Examples.MicrowaveOvenSPL.tvms._
import dsl.traces
import specification_level.moven_spl

/**
  * Created by Tërnava on 3/1/2017.
  */

object FeatureVPtraces {

  import moven_spl._
  import dsl.traces._

  import tvm_temperature._
  import tvm_beeper._
  import tvm_display._
  import tvm_door._
  import tvm_language._
  import tvm_light._
  import tvm_rotate._
  import tvm_weight._



  traces {

    /* The trace links */
    Language implements f_DisplayLanguage
    English implements f_English
    French implements f_French
    Italian implements f_Italian
    German implements f_German
    Spanish implements  f_Spanish
    WeightSensor implements f_WeightSensor
    AnalogWeight implements f_AnalogWeight
    BooleanWeight implements f_BooleanWeight




  }


}