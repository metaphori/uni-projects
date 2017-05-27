/*******************************************************/
/********* store top-level (program) variables *********/
/*******************************************************/
push 7 /* NAT */
push 8 /* NAT */
push FUNCTIONmyMult0
lfp
push FUNCTIONlength1
lfp
push FUNCTIONtestNot6
lfp
push FUNCTIONfWithLet7
lfp
push FUNCTIONmakelist9
lfp
push FUNCTIONmakelist214
lfp
push FUNCTIONtestf19
lfp
push FUNCTIONcallitWithFive25
lfp
push FUNCTIONprintlist26
lfp
push FUNCTIONdouble31
lfp
push FUNCTIONmap32
lfp
/* list construction */
/* head */
push 77 /* NAT */
/* tail */
/* list construction */
/* head */
push 88 /* NAT */
/* tail */
/* list construction */
/* head */
push 99 /* NAT */
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
push FUNCTIONfilter37
lfp
push FUNCTIONmypred55
lfp
push 50 /* NAT */
push FUNCTIONretf58
lfp
push FUNCTIONrunf64
lfp
push FUNCTIONfoldl65
lfp
push FUNCTIONsum70
lfp
push FUNCTIONf71
lfp
push FUNCTIONfoldleft72
lfp
push FUNCTIONcons77
lfp
push FUNCTIONreverse78
lfp
push FUNCTIONprintlist279
lfp
push FUNCTIONpippo84
lfp


/****************************************/
/************* PROGRAM BODY *************/
/****************************************/

/******** 'pippo' function call ********/
lfp
/* actual parameters */
push 1 /* BOOL */
/* lookup access link */
lfp
push 50
sub
lw
/* lookup fun 'pippo' address */
lfp
push 49
sub
lw
js
halt
FUNCTIONmyMult0:
cfp
lra
/* function body */
/* retrieve var a (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* retrieve var x (nesting_steps=1)*/
lfp
lw
push 1
sub
lw
/* end of var retrieval */
mult
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
FUNCTIONlength1:
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
beq true4
push 0
b end5
true4:
push 1
end5:
push 1
beq then2
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
push 6
sub
lw
/* lookup fun 'length' address */
lfp
lw
push 5
sub
lw
js
add
b end3
then2:
push 0 /* NAT */
end3:
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
FUNCTIONtestNot6:
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
FUNCTIONsubf8:
cfp
lra
/* function body */
/* retrieve var x (nesting_steps=0)*/
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
FUNCTIONfWithLet7:
cfp
lra
/* function body */
/* LET BLOCK */
/* store variables local to a LET-BLOCK */
push 6 /* NAT */
push FUNCTIONsubf8
lfp
/* retrieve var x (nesting_steps=0)*/
lfp
push 2
sub
lw
/* end of var retrieval */
push 7 /* NAT */
/******** 'subf' function call ********/
lfp
/* actual parameters */
push 1 /* NAT */
/* lookup access link */
lfp
push 4
sub
lw
/* lookup fun 'subf' address */
lfp
push 3
sub
lw
js
mult
add
srv
/* pop locals */
pop
pop
pop
sra
/* pop access link */
pop
/* pop args */
sfp
lrv
lra
js
FUNCTIONmakelist9:
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
beq true12
push 0
b end13
true12:
push 1
end13:
push 1
beq then10
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
push 1 /* NAT */
add
/* lookup access link */
lfp
lw
push 12
sub
lw
/* lookup fun 'makelist' address */
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
b end11
then10:
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
end11:
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
FUNCTIONmakelist214:
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
push 1 /* NAT */
add
bgt true17
push 0
b end18
true17:
push 1
end18:
push 1
beq then15
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
/* retrieve var step (nesting_steps=0)*/
lfp
push -3
sub
lw
/* end of var retrieval */
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
/* retrieve var step (nesting_steps=0)*/
lfp
push -3
sub
lw
/* end of var retrieval */
add
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
b end16
then15:
/* EMPTY LIST */
push -1
end16:
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
FUNCTIONtest20:
cfp
lra
/* function body */
/* if */
/* retrieve var b (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
push 1
beq then21
/* else */
push 4 /* NAT */
b end22
then21:
push 3 /* NAT */
end22:
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
FUNCTIONtestf19:
cfp
lra
/* function body */
/* LET BLOCK */
/* store variables local to a LET-BLOCK */
push FUNCTIONtest20
lfp
/* retrieve function ref test(nesting_steps=0)*/
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
/******** 'myf' function call ********/
lfp
/* actual parameters */
push 1 /* BOOL */
/* lookup access link */
lfp
push 5
sub
lw
/* lookup fun 'myf' address */
lfp
push 4
sub
lw
js
push 3 /* NAT */
beq true23
push 0
b end24
true23:
push 1
end24:
print
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
sfp
lrv
lra
js
FUNCTIONcallitWithFive25:
cfp
lra
/* function body */
/******** 'argy' function call ********/
lfp
/* actual parameters */
push 5 /* NAT */
/* lookup access link */
lfp
push -1
sub
lw
/* lookup fun 'argy' address */
lfp
push -2
sub
lw
js
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
FUNCTIONprintlist26:
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
beq true29
push 0
b end30
true29:
push 1
end30:
push 1
beq then27
/* else */
/* first(lst) */
/* retrieve var l (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
lw
print
/******** 'printlist' function call ********/
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
push 20
sub
lw
/* lookup fun 'printlist' address */
lfp
lw
push 19
sub
lw
js
add
b end28
then27:
push 0 /* NAT */
end28:
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
FUNCTIONdouble31:
cfp
lra
/* function body */
/* retrieve var x (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
push 2 /* NAT */
mult
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
FUNCTIONmap32:
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
beq true35
push 0
b end36
true35:
push 1
end36:
push 1
beq then33
/* else */
/* list construction */
/* head */
/******** 'argf' function call ********/
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
/* lookup fun 'argf' address */
lfp
push -3
sub
lw
js
/* tail */
/******** 'map' function call ********/
lfp
/* actual parameters */
/* retrieve function ref argf(nesting_steps=0)*/
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
push 24
sub
lw
/* lookup fun 'map' address */
lfp
lw
push 23
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
b end34
then33:
/* EMPTY LIST */
push -1
end34:
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
FUNCTIONgetRest38:
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
beq true41
push 0
b end42
true41:
push 1
end42:
push 1
beq then39
/* else */
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
b end40
then39:
/* EMPTY LIST */
push -1
end40:
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
FUNCTIONgetHead43:
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
beq true46
push 0
b end47
true46:
push 1
end47:
push 1
beq then44
/* else */
/* first(lst) */
/* retrieve var l (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
lw
b end45
then44:
push 0 /* NAT */
end45:
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
FUNCTIONmyfref248:
cfp
lra
/* function body */
/* retrieve function ref myfref(nesting_steps=1)*/
lfp
lw
push 8
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
lw
push 9
sub
lw
/* end of function closure retrieval */
stemp
srv
/* pop locals */
sra
/* pop access link */
pop
/* pop args */
sfp
lrv
ltemp
lra
js
FUNCTIONfilter37:
cfp
lra
/* function body */
/* LET BLOCK */
/* store variables local to a LET-BLOCK */
push FUNCTIONgetRest38
lfp
push FUNCTIONgetHead43
lfp
/******** 'getRest' function call ********/
lfp
/* actual parameters */
/* retrieve var lst (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* lookup access link */
lfp
push 3
sub
lw
/* lookup fun 'getRest' address */
lfp
push 2
sub
lw
js
/******** 'getHead' function call ********/
lfp
/* actual parameters */
/* retrieve var lst (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* lookup access link */
lfp
push 5
sub
lw
/* lookup fun 'getHead' address */
lfp
push 4
sub
lw
js
/* retrieve function ref predicate(nesting_steps=0)*/
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
push FUNCTIONmyfref248
lfp
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
/******** 'predicate' function call ********/
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
/* lookup fun 'predicate' address */
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
/* retrieve function ref myfref(nesting_steps=0)*/
lfp
push 8
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push 9
sub
lw
/* end of function closure retrieval */
/* retrieve var rl (nesting_steps=0)*/
lfp
push 6
sub
lw
/* end of var retrieval */
/* lookup access link */
lfp
lw
push 27
sub
lw
/* lookup fun 'filter' address */
lfp
lw
push 26
sub
lw
js
b end54
then53:
/* list construction */
/* head */
/* retrieve var hd (nesting_steps=0)*/
lfp
push 7
sub
lw
/* end of var retrieval */
/* tail */
/******** 'filter' function call ********/
lfp
/* actual parameters */
/******** 'myfref2' function call ********/
lfp
/* actual parameters */
/* lookup access link */
lfp
push 11
sub
lw
/* lookup fun 'myfref2' address */
lfp
push 10
sub
lw
js
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
push 27
sub
lw
/* lookup fun 'filter' address */
lfp
lw
push 26
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
pop
pop
pop
pop
pop
pop
pop
pop
pop
pop
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
FUNCTIONmypred55:
cfp
lra
/* function body */
/* retrieve var x (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
push 5 /* NAT */
bgt true56
push 0
b end57
true56:
push 1
end57:
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
FUNCTIONfxx59:
cfp
lra
/* function body */
/* if */
/* retrieve var h (nesting_steps=1)*/
lfp
lw
push -1
sub
lw
/* end of var retrieval */
print
/* retrieve var k (nesting_steps=2)*/
lfp
lw
lw
push 30
sub
lw
/* end of var retrieval */
print
add
/* retrieve var z (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
print
bgt true62
push 0
b end63
true62:
push 1
end63:
push 1
beq then60
/* else */
push 0 /* BOOL */
b end61
then60:
push 1 /* BOOL */
end61:
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
FUNCTIONretf58:
cfp
lra
/* function body */
/* LET BLOCK */
/* store variables local to a LET-BLOCK */
push FUNCTIONfxx59
lfp
/* retrieve function ref fxx(nesting_steps=0)*/
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
stemp
srv
/* pop locals */
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
FUNCTIONrunf64:
cfp
lra
/* function body */
/******** 'ff' function call ********/
lfp
/* actual parameters */
push 100 /* NAT */
/* lookup access link */
lfp
push -1
sub
lw
/* lookup fun 'ff' address */
lfp
push -2
sub
lw
js
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
FUNCTIONfoldl65:
cfp
lra
/* function body */
/* if */
/* retrieve var lst (nesting_steps=0)*/
lfp
push -4
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true68
push 0
b end69
true68:
push 1
end69:
push 1
beq then66
/* else */
/******** 'foldl' function call ********/
lfp
/* actual parameters */
/* rest(lst) */
push 1
/* retrieve var lst (nesting_steps=0)*/
lfp
push -4
sub
lw
/* end of var retrieval */
add
lw
/******** 'f' function call ********/
lfp
/* actual parameters */
/* retrieve var acc (nesting_steps=0)*/
lfp
push -3
sub
lw
/* end of var retrieval */
/* first(lst) */
/* retrieve var lst (nesting_steps=0)*/
lfp
push -4
sub
lw
/* end of var retrieval */
lw
/* lookup access link */
lfp
push -1
sub
lw
/* lookup fun 'f' address */
lfp
push -2
sub
lw
js
/* retrieve function ref f(nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push -1
sub
lw
/* end of function closure retrieval */
/* lookup access link */
lfp
lw
push 36
sub
lw
/* lookup fun 'foldl' address */
lfp
lw
push 35
sub
lw
js
b end67
then66:
/* retrieve var acc (nesting_steps=0)*/
lfp
push -3
sub
lw
/* end of var retrieval */
end67:
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
FUNCTIONsum70:
cfp
lra
/* function body */
/* retrieve var a (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* retrieve var b (nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of var retrieval */
add
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
FUNCTIONf71:
cfp
lra
/* function body */
push 0 /* NAT */
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
FUNCTIONfoldleft72:
cfp
lra
/* function body */
/* if */
/* retrieve var l (nesting_steps=0)*/
lfp
push -4
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
beq true75
push 0
b end76
true75:
push 1
end76:
push 1
beq then73
/* else */
/******** 'foldleft' function call ********/
lfp
/* actual parameters */
/* rest(lst) */
push 1
/* retrieve var l (nesting_steps=0)*/
lfp
push -4
sub
lw
/* end of var retrieval */
add
lw
/******** 'f' function call ********/
lfp
/* actual parameters */
/* retrieve var acc (nesting_steps=0)*/
lfp
push -3
sub
lw
/* end of var retrieval */
/* first(lst) */
/* retrieve var l (nesting_steps=0)*/
lfp
push -4
sub
lw
/* end of var retrieval */
lw
/* lookup access link */
lfp
push -1
sub
lw
/* lookup fun 'f' address */
lfp
push -2
sub
lw
js
/* retrieve function ref f(nesting_steps=0)*/
lfp
push -2
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
push -1
sub
lw
/* end of function closure retrieval */
/* lookup access link */
lfp
lw
push 42
sub
lw
/* lookup fun 'foldleft' address */
lfp
lw
push 41
sub
lw
js
b end74
then73:
/* retrieve var acc (nesting_steps=0)*/
lfp
push -3
sub
lw
/* end of var retrieval */
end74:
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
FUNCTIONcons77:
cfp
lra
/* function body */
/* list construction */
/* head */
/* retrieve var head (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* tail */
/* retrieve var tail (nesting_steps=0)*/
lfp
push -2
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
pop
sfp
lrv
lra
js
FUNCTIONreverse78:
cfp
lra
/* function body */
/******** 'foldleft' function call ********/
lfp
/* actual parameters */
/* retrieve var l (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
/* EMPTY LIST */
push -1
/* retrieve function ref cons(nesting_steps=1)*/
lfp
lw
push 43
sub
lw
/* end of function ref retrieval, now retrieve the closure */
lfp
lw
push 44
sub
lw
/* end of function closure retrieval */
/* lookup access link */
lfp
lw
push 42
sub
lw
/* lookup fun 'foldleft' address */
lfp
lw
push 41
sub
lw
js
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
FUNCTIONprintlist279:
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
beq true82
push 0
b end83
true82:
push 1
end83:
push 1
beq then80
/* else */
/* list construction */
/* head */
/* first(lst) */
/* retrieve var l (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
lw
print
/* tail */
/******** 'printlist2' function call ********/
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
push 48
sub
lw
/* lookup fun 'printlist2' address */
lfp
lw
push 47
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
b end81
then80:
/* EMPTY LIST */
push -1
end81:
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
FUNCTIONpippo84:
cfp
lra
/* function body */
/* if */
/******** 'pippo' function call ********/
lfp
/* actual parameters */
push 5 /* NAT */
/* lookup access link */
lfp
lw
push 50
sub
lw
/* lookup fun 'pippo' address */
lfp
lw
push 49
sub
lw
js
push 5 /* NAT */
beq true87
push 0
b end88
true87:
push 1
end88:
push 1
beq then85
/* else */
/* retrieve var x (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
b end86
then85:
/* retrieve var x (nesting_steps=0)*/
lfp
push -1
sub
lw
/* end of var retrieval */
end86:
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
