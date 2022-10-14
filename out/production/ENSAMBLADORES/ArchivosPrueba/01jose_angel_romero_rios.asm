; multi-segment executable file template

data segment
    
    argumento equ 45
    encuentra db "Hola"
    name db "Jose Angel"
    lsname1 db  "Romero"
    lsname2 db  "Rios"
    quiza db 0
    fluye db 20 dup(1010B)
    semana dw 2
    programa db 10 dup('q')
    posible dw 045h dup(023h)
ends


stack segment
    
    dw      128     dup(0)
ends


macro extinto raya,guion
    
    sub quiza, 10
    
endm



code segment
start:
;set segment regiter
    mov ax, bx
    int 010h
    letra:
    pintura:nop
    extinto 1,2
    call llamado
    and [di+bp],al
ends 

proc llamado
    add cx, semana
    ret

llamado endp

end start; set entry point and stop the assembler