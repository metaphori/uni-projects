/*******************************************************/
/********* store top-level (program) variables *********/
/*******************************************************/
push FUNCTIONlength0
lfp
push FUNCTIONmakelist5
lfp
push FUNCTIONnextInt10
lfp
push FUNCTIONnextBool11
lfp
/* retrieve function ref makelist(nesting_steps=0)*/
lfp
push 3
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push 4
sub
lw
/* end of function closure retrieval */
push FUNCTIONprintlist12
lfp
push FUNCTIONmakelist217
lfp
push FUNCTIONsuccLst20
lfp
push FUNCTIONeqList21
lfp
/******** 'makelist' function call ********/
lfp
/* actual parameters */
/* retrieve function ref nextInt(nesting_steps=0)*/
lfp
push 5
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push 6
sub
lw
/* end of function closure retrieval */
push 9 /* NAT */
push 5 /* NAT */
/* lookup access link */
lfp
push 4
sub
lw
/* lookup fun 'makelist' address */
lfp
push 3
sub
lw
js
/******** 'makelist' function call ********/
lfp
/* actual parameters */
/* retrieve function ref nextInt(nesting_steps=0)*/
lfp
push 5
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push 6
sub
lw
/* end of function closure retrieval */
push 9 /* NAT */
push 0 /* NAT */
/* lookup access link */
lfp
push 4
sub
lw
/* lookup fun 'makelist' address */
lfp
push 3
sub
lw
js
push FUNCTIONforeach42
lfp
push FUNCTIONprintint47
lfp
/* retrieve function ref printlist(nesting_steps=0)*/
lfp
push 11
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push 12
sub
lw
/* end of function closure retrieval */
push FUNCTIONfilter48
lfp
push FUNCTIONgetParityTest55
lfp


/****************************************/
/************* PROGRAM BODY *************/
/****************************************/

/* retrieve function ref makelist2(nesting_steps=0)*/
lfp
push 13
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push 14
sub
lw
/* end of function closure retrieval */
halt
FUNCTIONlength0:
cfp
lra
/* function body */
/* if */
/* retrieve var l (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true3
push 0
b end4
true3:
push 1
end4:
push 1
beq then1
/* else */
push 1 /* NAT */
/******** 'length' function call ********/
lfp
/* actual parameters */
/* rest(lst) */
push 1
/* retrieve var l (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
add
lw
/* lookup access link */
lfp
lw
push 2
sub
lw
/* lookup fun 'length' address */
lfp
lw
push 1
sub
lw
js
add
b end2
then1:
push 0 /* NAT */
end2:
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
sfp
lrv
lra
js
FUNCTIONmakelist5:
cfp
lra
/* function body */
/* if */
/* retrieve var start (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* retrieve var end (nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of var retrieval */
beq true8
push 0
b end9
true8:
push 1
end9:
push 1
beq then6
/* else */
/* list construction */
/* head */
/* retrieve var start (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* tail */
/******** 'makelist' function call ********/
lfp
/* actual parameters */
/* retrieve function ref succ(nesting_steps=0)*/
lfp
push -4
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push -3
sub
lw
/* end of function closure retrieval */
/* retrieve var end (nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of var retrieval */
/******** 'succ' function call ********/
lfp
/* actual parameters */
/* retrieve var start (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* lookup access link */
lfp
push -3
sub
lw
/* lookup fun 'succ' address */
lfp
push -4
sub
lw
js
/* lookup access link */
lfp
lw
push 4
sub
lw
/* lookup fun 'makelist' address */
lfp
lw
push 3
sub
lw
js
/* put pointer to address of tail on the next-of-next free cell (HP+1) on heap */
push 1
lhp
add
sw
/* put head value on the next free cell (HP) on heap */
lhp
sw
lhp
push 2
lhp
add
shp
b end7
then6:
/* list construction */
/* head */
/* retrieve var start (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* tail */
/* EMPTY LIST */
push -1
/* put pointer to address of tail on the next-of-next free cell (HP+1) on heap */
push 1
lhp
add
sw
/* put head value on the next free cell (HP) on heap */
lhp
sw
lhp
push 2
lhp
add
shp
end7:
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
pop
pop
pop
sfp
lrv
lra
js
FUNCTIONnextInt10:
cfp
lra
/* function body */
/* retrieve var i (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
push 1 /* NAT */
add
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
sfp
lrv
lra
js
FUNCTIONnextBool11:
cfp
lra
/* function body */
/* retrieve var b (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
push 1
xor
push 1
and
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
sfp
lrv
lra
js
FUNCTIONprintlist12:
cfp
lra
/* function body */
/* if */
/* retrieve var ppp (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true15
push 0
b end16
true15:
push 1
end16:
push 1
beq then13
/* else */
/* list construction */
/* head */
/* first(lst) */
/* retrieve var ppp (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
lw
print
/* tail */
/******** 'printlist' function call ********/
lfp
/* actual parameters */
/* rest(lst) */
push 1
/* retrieve var ppp (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
add
lw
/* lookup access link */
lfp
lw
push 12
sub
lw
/* lookup fun 'printlist' address */
lfp
lw
push 11
sub
lw
js
/* put pointer to address of tail on the next-of-next free cell (HP+1) on heap */
push 1
lhp
add
sw
/* put head value on the next free cell (HP) on heap */
lhp
sw
lhp
push 2
lhp
add
shp
b end14
then13:
/* EMPTY LIST */
push -1
end14:
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
sfp
lrv
lra
js
FUNCTIONmakelist217:
cfp
lra
/* function body */
/* if */
/******** 'equal' function call ********/
lfp
/* actual parameters */
/* retrieve var end (nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of var retrieval */
/* retrieve var start (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* lookup access link */
lfp
push -5
sub
lw
/* lookup fun 'equal' address */
lfp
push -6
sub
lw
js
push 1
beq then18
/* else */
/* list construction */
/* head */
/* retrieve var start (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* tail */
/******** 'makelist2' function call ********/
lfp
/* actual parameters */
/* retrieve function ref equal(nesting_steps=0)*/
lfp
push -6
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push -5
sub
lw
/* end of function closure retrieval */
/* retrieve function ref succ(nesting_steps=0)*/
lfp
push -4
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push -3
sub
lw
/* end of function closure retrieval */
/* retrieve var end (nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of var retrieval */
/******** 'succ' function call ********/
lfp
/* actual parameters */
/* retrieve var start (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* lookup access link */
lfp
push -3
sub
lw
/* lookup fun 'succ' address */
lfp
push -4
sub
lw
js
/* lookup access link */
lfp
lw
push 14
sub
lw
/* lookup fun 'makelist2' address */
lfp
lw
push 13
sub
lw
js
/* put pointer to address of tail on the next-of-next free cell (HP+1) on heap */
push 1
lhp
add
sw
/* put head value on the next free cell (HP) on heap */
lhp
sw
lhp
push 2
lhp
add
shp
b end19
then18:
/* list construction */
/* head */
/* retrieve var start (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* tail */
/* EMPTY LIST */
push -1
/* put pointer to address of tail on the next-of-next free cell (HP+1) on heap */
push 1
lhp
add
sw
/* put head value on the next free cell (HP) on heap */
lhp
sw
lhp
push 2
lhp
add
shp
end19:
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
pop
pop
pop
pop
pop
sfp
lrv
lra
js
FUNCTIONsuccLst20:
cfp
lra
/* function body */
/* list construction */
/* head */
/* first(lst) */
/* retrieve var kkk (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
lw
push 1 /* NAT */
sub
/* tail */
/* retrieve var kkk (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* put pointer to address of tail on the next-of-next free cell (HP+1) on heap */
push 1
lhp
add
sw
/* put head value on the next free cell (HP) on heap */
lhp
sw
lhp
push 2
lhp
add
shp
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
sfp
lrv
lra
js
FUNCTIONeqList21:
cfp
lra
/* function body */
/* if */
/* retrieve var l1 (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true26
push 0
b end27
true26:
push 1
end27:
push 1
and
push 0
beq false24
/* retrieve var l2 (nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true28
push 0
b end29
true28:
push 1
end29:
push 1
and
b end25
false24:
push 0
end25:
push 1
beq then22
/* else */
/* if */
/* LOGICAL OR (short-circuit) *//* retrieve var l1 (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true34
push 0
b end35
true34:
push 1
end35:
push 1
and
push 1
beq true32
/* retrieve var l2 (nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true36
push 0
b end37
true36:
push 1
end37:
push 1
and
b end33
true32:
push 1
end33:
push 1
beq then30
/* else */
/* if */
/* first(lst) */
/* retrieve var l1 (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
lw
/* first(lst) */
/* retrieve var l2 (nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of var retrieval */
lw
beq true40
push 0
b end41
true40:
push 1
end41:
push 1
xor
push 1
and
push 1
beq then38
/* else */
/******** 'eqList' function call ********/
lfp
/* actual parameters */
/* rest(lst) */
push 1
/* retrieve var l2 (nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of var retrieval */
add
lw
/* rest(lst) */
push 1
/* retrieve var l1 (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
add
lw
/* lookup access link */
lfp
lw
push 18
sub
lw
/* lookup fun 'eqList' address */
lfp
lw
push 17
sub
lw
js
b end39
then38:
push 0 /* BOOL */
end39:
b end31
then30:
push 0 /* BOOL */
end31:
b end23
then22:
push 1 /* BOOL */
end23:
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
pop
sfp
lrv
lra
js
FUNCTIONforeach42:
cfp
lra
/* function body */
/* if */
/* retrieve var llll (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true45
push 0
b end46
true45:
push 1
end46:
push 1
beq then43
/* else */
/******** 'do' function call ********/
lfp
/* actual parameters */
/* first(lst) */
/* retrieve var llll (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
lw
/* lookup access link */
lfp
push -2
sub
lw
/* lookup fun 'do' address */
lfp
push -3
sub
lw
js
push 9999 /* NAT */
print
print
print
add
/******** 'foreach' function call ********/
lfp
/* actual parameters */
/* retrieve function ref do(nesting_steps=0)*/
lfp
push -3
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push -2
sub
lw
/* end of function closure retrieval */
/* rest(lst) */
push 1
/* retrieve var llll (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
add
lw
/* lookup access link */
lfp
lw
push 22
sub
lw
/* lookup fun 'foreach' address */
lfp
lw
push 21
sub
lw
js
add
b end44
then43:
push 0 /* NAT */
end44:
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
pop
pop
sfp
lrv
lra
js
FUNCTIONprintint47:
cfp
lra
/* function body */
/* retrieve var x (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
print
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
sfp
lrv
lra
js
FUNCTIONfilter48:
cfp
lra
/* function body */
/* if */
/* retrieve var lst (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true51
push 0
b end52
true51:
push 1
end52:
push 1
beq then49
/* else */
/* if */
/******** 'pred' function call ********/
lfp
/* actual parameters */
/* first(lst) */
/* retrieve var lst (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
lw
/* lookup access link */
lfp
push -2
sub
lw
/* lookup fun 'pred' address */
lfp
push -3
sub
lw
js
push 1
beq then53
/* else */
/******** 'filter' function call ********/
lfp
/* actual parameters */
/* retrieve function ref pred(nesting_steps=0)*/
lfp
push -3
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push -2
sub
lw
/* end of function closure retrieval */
/* rest(lst) */
push 1
/* retrieve var lst (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
add
lw
/* lookup access link */
lfp
lw
push 28
sub
lw
/* lookup fun 'filter' address */
lfp
lw
push 27
sub
lw
js
b end54
then53:
/* list construction */
/* head */
/* first(lst) */
/* retrieve var lst (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
lw
/* tail */
/******** 'filter' function call ********/
lfp
/* actual parameters */
/* retrieve function ref pred(nesting_steps=0)*/
lfp
push -3
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push -2
sub
lw
/* end of function closure retrieval */
/* rest(lst) */
push 1
/* retrieve var lst (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
add
lw
/* lookup access link */
lfp
lw
push 28
sub
lw
/* lookup fun 'filter' address */
lfp
lw
push 27
sub
lw
js
/* put pointer to address of tail on the next-of-next free cell (HP+1) on heap */
push 1
lhp
add
sw
/* put head value on the next free cell (HP) on heap */
lhp
sw
lhp
push 2
lhp
add
shp
end54:
b end50
then49:
/* EMPTY LIST */
push -1
end50:
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
pop
pop
sfp
lrv
lra
js
FUNCTIONisEven56:
cfp
lra
/* function body */
/* retrieve var v (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
push 2 /* NAT */
mod
push 0 /* NAT */
beq true57
push 0
b end58
true57:
push 1
end58:
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
sfp
lrv
lra
js
FUNCTIONisOdd59:
cfp
lra
/* function body */
/* retrieve var v (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
push 2 /* NAT */
mod
push 0 /* NAT */
beq true60
push 0
b end61
true60:
push 1
end61:
push 1
xor
push 1
and
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
pop
sfp
lrv
lra
js
FUNCTIONgetParityTest55:
cfp
lra
/* function body */
/* LET BLOCK */
/* store variables local to a LET-BLOCK */
push FUNCTIONisEven56
lfp
push FUNCTIONisOdd59
lfp
/* if */
/* retrieve var which (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
push 0 /* NAT */
beq true64
push 0
b end65
true64:
push 1
end65:
push 1
beq then62
/* else */
/* retrieve function ref isOdd(nesting_steps=0)*/
lfp
push 4
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push 5
sub
lw
/* end of function closure retrieval */
b end63
then62:
/* retrieve function ref isEven(nesting_steps=0)*/
lfp
push 2
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push 3
sub
lw
/* end of function closure retrieval */
end63:
stemp
srv
/* pop locals */
pop
pop
pop
pop
sra
/* pop access link */
pop
/* pop args */
pop
sfp
lrv
ltemp
lra
js
