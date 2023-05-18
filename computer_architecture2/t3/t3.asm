

add r0, #4, r9              ; global g

public min
min:
    add r26, r0, r1         ; r1 = v
    sub r27, r1, r0{C}      ; b < v
    jge min0
    xor r0,r0,r0            ; no-op in delay slot
    add r27, r0, r1         ; v = b
min0:
    sub r28, r1, r0{C}      ; c < v
    jge min1
    xor r0, r0, r0          ; no-op in delay slot
    add r28, r0, r1         ; v = c
min1:
    ret r25, 0              ; return
    xor r0, r0, r0          ; no-op in delay slot


public p
p:
    add r9, r0, r10         ; r10 = g
    add r26, r0, r11        ; r11 = i 
    callr r25, min
    add r27, r0, r12        ; r12 = j
    add r1, r0, r10         ; r10 = min(g, i, j)
    add r28, r0, r11        ; r11 = k
    callr r25, min
    add r29, r0, r12        ; r12 = l
    ret r25, 0              ; return
    xor r0, r0, r0          ; no-op in delay slot

public gcd
gcd:
    sub r27, #0, r0{C}      ; if b == 0
    jne gcd1
    xor r0, r0, r0          ; no-op in delay slot
    add r26, r0, r1         ; r1 = a
    ret r25, 0              ; return
    xor r0, r0, r0          ; no-op in delay slot
    
gcd1:
    add r26, r0, r10        ; r10 = a
    callr r25, AmodB
    add r27, r0, r11        ; r11 = b
    add r1, r0, r11         ; r11 = a%b
    callr r25, gcd          ; gcd(b, a%b)
    add r27, r0 , r10       ; r10 = b
    ret r25, 0              ; return
    xor r0, r0, r0          ; no-op in delay slot

