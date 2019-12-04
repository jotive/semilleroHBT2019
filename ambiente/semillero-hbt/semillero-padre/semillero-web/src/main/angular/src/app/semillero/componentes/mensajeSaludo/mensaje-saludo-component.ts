import { Component, OnInit } from '@angular/core';

/**
 * @description La clase MensajeSaludoComponent imprime en pantalla el nombre y ciudad del autor del componente 
 * @author Jose Eduardo Tirado Verbel <Jotive@gmail.com>
 */

// Caracteristicas del componente
@Component({
    selector: 'mensaje-saludo',
    templateUrl: './mensaje-saludo-component.html',
    styleUrls:['./mensaje-saludo-component.css']
})

// Contenido del componente
export class MensajeSaludoComponent implements OnInit {
    
    // Declaramos las variables
    public nombreAutor: string;
    public ciudadAutor: string;
    
    // Asignamos valores del autor en el constructor
    constructor() {

        // Asignacion de valores
        this.nombreAutor = 'Jose Eduardo Tirado Verbel';
        this.ciudadAutor = 'Monteria';
    }

    // Metodo OnInit
    ngOnInit(): void {
        
    } 

}

    
  
    

    
 