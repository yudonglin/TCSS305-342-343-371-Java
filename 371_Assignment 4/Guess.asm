; Yudong Lin 2/20/2022
; An assembly language program to simulate a simple guessing game.
; The program will continually ask the user to guess a number between 0 and 9 (see sample program input/output below.).
; Assume that the user gets it right within 9 guesses.  

        .ORIG        x3000

; init critical registers
        AND          R1, R1, #0      ; clear R1
        AND          R2, R2, #0      ; clear R2
        AND          R4, R4, #0      ; clear R4 - the counter
        AND          R5, R5, #0      ; clear R5
        ADD          R5, R5, #4      ; add the number that needs to guess into R5

        LEA          R0, PROMPT      ; load the first time guessing prompt message into R0
        BR           LOOP1           ; barnch to the LOOP1 so the not the first time guessing prompt message will not be loaded
LOOP    LEA          R0, GAGAIN      ; load guess again message into R0 
LOOP1   PUTS                         ; display the message prompt
        GETC                         ; read a char into R0
        OUT                          ; write a char from R0 to the monitor
        
        ADD          R4, R4, #1      ; R4 ++
        NOT          R1, R0          ; get the 2's comp of R0
        ADD          R1, R1, #1
        
; first, we need check whether input is valid
        LD           R2, ZERO        ; lead zero in ASCII into R2
        ADD          R2, R1, R2      ; add the 2's comp of user input to ZERO IN ASCII
        BRp          IFINVI          ; if positive, then the input is invalid
        AND          R3, R3, #0      ; clear R3
        ADD          R3, R2, #9      ; ADD 9 AND R2 to R3
        BRn          IFINVI          ; if negative, then the input is invalid
        
; now check whether the user guessed the right number       
        ADD          R2, R2, R5      ; Add the number that is needed to guess to R2
; if zero then quit (which mean the user guessed the right number)
        BRz          QUITMSG
; if not
        BRn          IFTOOB          ; if negative, then the guessed number is too small
        LEA          R0, TOOS
        BR           REPORT 
IFTOOB  LEA          R0, TOOB        ; else if too big
        BR           REPORT
IFINVI  LEA          R0, INVALID
REPORT  PUTS

        BR           LOOP

; Output the result  
QUITMSG LEA          R0, ENDMSG1     ; load the end message part 1
        PUTS                         ; display the end message part 1
        AND          R0, R0, #0      ; clear R0
        LD           R0, ZERO        ; LOAD ASCII zero to R0 as offset
        ADD          R0, R0, R4      ; add counter to R0 for output
        OUT                          ; output the number of guess
        LEA          R0, ENDMSG2     ; load the end message part 2
        PUTS                         ; display the end message part 2
        
        HALT                         ; That is it
        

TOOB    .STRINGZ     "\nToo big."
TOOS    .STRINGZ     "\nToo small."
INVALID .STRINGZ     "\nInvalid input."
PROMPT  .STRINGZ     "\nGuess a number: "
GAGAIN  .STRINGZ     "\nGuess again: "
ENDMSG1 .STRINGZ     "\nCorrect! You took "
ENDMSG2 .STRINGZ     " guesses."
ZERO    .FILL        x0030           ; ASCII zero
NINE    .FILL        x0039           ; ASCII nide

       