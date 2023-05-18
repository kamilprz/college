option casemap:none 
 
includelib legacy_stdio_definitions.lib
extrn printf:near
.data
 
qnsx db    'qns', 0AH, 00H  
wah  db    'a = %I64d b = %I64d c = %I64d d = %I64d e = %I64d sum = %I64d', 0AH, 00H        
 
public g 
g   QWORD   4
 
.code
 
public  min
 
min:
    ;r8  = c
    ;rdx = b
    ;rcx = a
    mov rax, rcx        ;v = a
    cmp rdx, rax        ;if b < v
    jge greaterEqual
    mov rax, rdx        ;v = b
greaterEqual:
    cmp r8, rax         ;if c < v
    jge greaterEqual2
    mov rax, r8         ;v = c
greaterEqual2:
    ret
 
; ###############################################################################
 
public  p
 
p:
    ;r9 = l
    ;r8 = k
    ;rdx = l
    ;rcx = i
    mov [rsp + 32], r9  ;store l in shadow space
    mov [rsp + 24], r8  ;store k in shadow space
 
    mov r8, rdx         ;j = 3rd param
    mov rdx, rcx        ;i = 2nd param
    mov rcx, g          ;g = 1st param
 
    sub rsp, 32         ;allocate shadow space
    call min            ;result in rax
    add rsp, 32         ;deallocate shadow space
 
    mov r8, [rsp + 32]  ;l = 3rd param
    mov rdx, [rsp + 24] ;k = 2nd param
    sub rsp, 32         ;allocate shadow space
    mov rcx, rax        ;min(g,i,j) = 1st param
    call min            ;result in rax
    add rsp, 32         ;deallocate shadow space
    ret
   
; ###############################################################################
 
public gcd 
 
gcd:
	;rdx = b
	;rcx = a
	cmp rdx, 0          ;if b == 0
	jne notZero
	mov rax, rcx        ;rax = a
	jmp gcdRet          ;return a
notZero:
	mov [rsp + 32], rdx ;store b in shadow space
	mov rax, rcx        ;making rax the dividend
	mov r8, rdx         ;can't use rdx here
	cdq                 ;clears rdx
	idiv r8             ;a % b = 2nd param
	mov rcx, [rsp + 32] ;b = 1st param
	sub rsp, 32         ;allocate shadow space
	call gcd            ;gcd(b, a % b)
	add rsp, 32         ;deallocate shadow space
gcdRet:    
	ret                 ;return rax
   
 
; ###############################################################################

public q

q:  
	mov r10, [rsp+40]     ;r10 = e 
	mov rax, r10          ;sum = e
	add rax, r9           ;sum += d
	add rax, r8           ;sum += c
	add rax, rdx          ;sum += b
	add rax, rcx          ;sum += a
	sub rsp, 56           ;allocate shadow space and space for 3 params
	mov [rsp + 48], rax   ;push sum
	mov [rsp + 40], r10   ;push e
	mov [rsp + 32], r9    ;push d
	mov r9,  r8           ;push c
	mov r8,  rdx          ;push b
	mov rdx, rcx          ;push a
	lea rcx, wah          ;push string
	call printf
	mov rax, [rsp + 48]
	add rsp, 56           ;deallocate shadow space
	ret                   ;return rax
 
; ###############################################################################
 
public qns
 
qns:    
	; with shadow space - works as expected
    lea rcx, qnsx          ; push string
	sub rsp, 32            ; allocate shadow space
    call printf            ; printf("qns\n")
    add rsp, 32            ; deallocate shadow space

	; without shadow space - program crashes
    ;call printf            ; printf("qns\n")
	mov eax, 0
    ret                    ; return 0
 
end