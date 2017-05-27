prog:
push -1
lfp
push function0
lfp
push function1
lfp
push function2
lfp
push function3
lfp
push function6
lfp
push function9
push 1
push 7
push 6
push 4
push 2
push 3
push 7
push 4
push -1
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
lfp
lfp
lfp
push -12
add
lw /* QUI DA' ERRORE */



lfp
push -12
push 1
sub
add
lw
lfp
push -14
add
lw
lfp
push -8
add
lw
lfp
push -8
push 1
sub
add
lw
js
lfp
push -2
add
lw
lfp
push -2
push 1
sub
add
lw
js
halt

function0:
cfp  /* copia il valore di SP in FP*/
lra /* mette sullo stack il RA */
lfp /* mette sullo stack il FP */
push 1
add
lw
push -1
beq label2
push 0
b label3
label2:
push 1
label3:
push 1
beq label0
lfp
push 1
add
lw
lw
print
lfp
push 1
lfp
push 1
add
lw
add
lw
lfp
lw
push -2
add
lw
lfp
lw
push -2
push 1
sub
add
lw
js
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
b label1
label0:
push -1
label1:
srv
sra
pop
pop
sfp
lrv
lra
js

function1:
cfp
lra
lfp
push 1
add
lw
push -1
beq label6
push 0
b label7
label6:
push 1
label7:
push 1
beq label4
lfp
push 1
add
lw
lw
lfp
lfp
push 2
add
lw
push 1
lfp
push 1
add
lw
add
lw
lfp
lw
push -4
add
lw
lfp
lw
push -4
push 1
sub
add
lw
js
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
b label5
label4:
lfp
push 2
add
lw
label5:
srv
sra
pop
pop
pop
sfp
lrv
lra
js

function2:
cfp
lra
lfp
push 1
add
lw
push -1
beq label10
push 0
b label11
label10:
push 1
label11:
push 1
beq label8
lfp
lfp
push 1
add
lw
lw
lfp
push 3
add
lw
lfp
push 3
push 1
sub
add
lw
js
push 1
beq label12
lfp
lfp
push 3
add
lw
lfp
push 3
push 1
sub
add
lw
push 1
lfp
push 1
add
lw
add
lw
lfp
lw
push -6
add
lw
lfp
lw
push -6
push 1
sub
add
lw
js
b label13
label12:
lfp
push 1
add
lw
lw
lfp
lfp
push 3
add
lw
lfp
push 3
push 1
sub
add
lw
push 1
lfp
push 1
add
lw
add
lw
lfp
lw
push -6
add
lw
lfp
lw
push -6
push 1
sub
add
lw
js
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
label13:
b label9
label8:
push -1
label9:
srv
sra
pop
pop
pop
pop
sfp
lrv
lra
js

function4:
cfp
lra
lfp
lfp
lw
push 1
add
lw
lw
lfp
push 1
add
lw
lfp
lw
push 3
add
lw
lfp
lw
push 3
push 1
sub
add
lw
js
srv
sra
pop
pop
sfp
lrv
lra
js

function5:
cfp
lra
lfp
lfp
push 1
add
lw
lfp
lw
push 1
add
lw
lw
lfp
lw
push 3
add
lw
lfp
lw
push 3
push 1
sub
add
lw
js
srv
sra
pop
pop
sfp
lrv
lra
js

function3:
cfp
lra
lfp
push function4
lfp
push function5
lfp
push 1
add
lw
push -1
beq label16
push 0
b label17
label16:
push 1
label17:
push 1
beq label14
lfp
lfp
push 1
add
lw
lw
lfp
lfp
push 3
add
lw
lfp
push 3
push 1
sub
add
lw
lfp
lfp
push -4
add
lw
lfp
push -4
push 1
sub
add
lw
lfp
push 1
add
lw
lfp
lw
push -6
add
lw
lfp
lw
push -6
push 1
sub
add
lw
js
lfp
lw
push -8
add
lw
lfp
lw
push -8
push 1
sub
add
lw
js
push 1
lhp
add
sw
lhp
sw
lhp
push 2
lhp
add
shp
lfp
lfp
push 3
add
lw
lfp
push 3
push 1
sub
add
lw
lfp
lfp
push -2
add
lw
lfp
push -2
push 1
sub
add
lw
lfp
push 1
add
lw
lfp
lw
push -6
add
lw
lfp
lw
push -6
push 1
sub
add
lw
js
lfp
lw
push -8
add
lw
lfp
lw
push -8
push 1
sub
add
lw
js
lfp
lw
push -4
add
lw
lfp
lw
push -4
push 1
sub
add
lw
js
b label15
label14:
push -1
label15:
srv
pop
pop
pop
pop
sra
pop
pop
pop
pop
sfp
lrv
lra
js

function7:
cfp
lra
lfp
lfp
lw
push 1
add
lw
lw
lfp
push 1
add
lw
lfp
lw
push 3
add
lw
lfp
lw
push 3
push 1
sub
add
lw
js
srv
sra
pop
pop
sfp
lrv
lra
js

function8:
cfp
lra
lfp
lfp
push 1
add
lw
lfp
lw
push 1
add
lw
lw
lfp
lw
push 3
add
lw
lfp
lw
push 3
push 1
sub
add
lw
js
srv
sra
pop
pop
sfp
lrv
lra
js

function6:
cfp
lra
lfp
push function7
lfp
push function8
lfp
push 2
add
lw
lfp
push 1
add
lw
bless label20
push 0
b label21
label20:
push 1
label21:
push 1
beq label18
push 1
b label19
label18:
push 0
label19:
srv
pop
pop
pop
pop
sra
pop
pop
pop
sfp
lrv
lra
js

function10:
cfp
lra
lfp
lfp
lw
push 1
add
lw
lw
lfp
push 1
add
lw
lfp
lw
push 3
add
lw
lfp
lw
push 3
push 1
sub
add
lw
js
srv
sra
pop
pop
sfp
lrv
lra
js

function11:
cfp
lra
lfp
lfp
push 1
add
lw
lfp
lw
push 1
add
lw
lw
lfp
lw
push 3
add
lw
lfp
lw
push 3
push 1
sub
add
lw
js
srv
sra
pop
pop
sfp
lrv
lra
js

function9:
cfp
lra
lfp
push function10
lfp
push function11
lfp
push 1
add
lw
lfp
push 2
add
lw
bless label24
push 0
b label25
label24:
push 1
label25:
push 1
beq label22
push 1
b label23
label22:
push 0
label23:
srv
pop
pop
pop
pop
sra
pop
pop
pop
sfp
lrv
lra
js
halt
