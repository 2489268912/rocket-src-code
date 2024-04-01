package freechips.rocketchip.snapshoot

import chisel3._
import chisel3.util._


class Instcounter extends Module{
   val io = IO(new Bundle{
      val wb_valid    =    Input(Bool())
      val copy_valid  =    Output(Bool())
      val copy_done   =    Input(Bool())
})
   val instnumber     =    RegInit(0.U(64.W))
   
//copy request
when(io.wb_valid){
   instnumber := instnumber + 1.U
}
.otherwise{
   instnumber := instnumber
}
when(instnumber === 100.U){
   io.copy_valid := true.B
}
.otherwise{
   io.copy_valid := false.B
}

dontTouch(instnumber)

when(io.copy_done){
   instnumber := 0.U
}
}