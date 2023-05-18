
.686                                ; create 32 bit code
.model flat, C                      ; 32 bit memory model
option casemap:none                ; case sensitive

.data					;start of a data section
public	g				;export variable g
g		DWORD	4		;declare global variable g initialised to 4
.code					;start of a code section

; ###############################################################################

public      min               ; make sure function name is exported

min:
	push	ebp				;save ebp
	mov		ebp, esp		;ebp -> new stack frame
	
	mov		edx, [ebp + 8]	;edx = v = a
	mov		ecx, [ebp + 12]	;ecx = b
	cmp		ecx,edx			; if b < v
	jge		greaterOrEq
	mov		edx, ecx		; v = b

greaterOrEq:
	mov		ecx, [ebp+16]	;ecx = c
	cmp		ecx, edx		; if c < v
	jge		exitMin
	mov		edx, ecx		; v = c

exitMin:
	mov		eax, edx		;eax = min
	mov		esp, ebp		;restore esp
	pop		ebp				;restore previous ebp
	ret		0				;return


; ###############################################################################

public      p              ; make sure function name is exported

p:	
	push	ebp
	mov		ebp, esp

	push	[ebp + 12]		;j
	push	[ebp + 8]		;i
	push	g				;g
	call	min
	add		esp, 12

	push	[ebp + 20]		;l
	push	[ebp + 16]		;k
	push	eax				;min(g, i, j)
	call	min
	add		esp, 12

	mov		esp, ebp		;restore esp
	pop		ebp				;restore previous ebp
	ret		0				;return


; ###############################################################################

public      gcd              ; make sure function name is exported

gcd:
	push	ebp
	mov		ebp, esp
	mov		eax, [ebp + 8]		;eax = a
	mov		ecx, [ebp + 12]		;ecx = b
	cmp		ecx, 0
	jne		gcd1
	mov		esp, ebp		;restore esp
	pop		ebp				;restore previous ebp
	ret		0

gcd1:
	cdq
	idiv	ecx
	mov		eax, edx
	push	eax				;a % b
	push	ecx				;push b
	call	gcd
	add		esp, 8

	mov		esp, ebp		;restore esp
	pop		ebp				;restore previous ebp
	ret		0				;return

end
