import { SuperHeroService } from './superhero.service';
import {Component, OnInit} from '@angular/core';
import {SuperHero} from './superhero';

@Component({
  selector: 'app-superhero',
  templateUrl: './superhero.component.html',
  styleUrls: ['./superhero.component.css']
})
export class SuperHeroComponent implements OnInit{
    superHeroes: SuperHero[];

    constructor(private superHeroService: SuperHeroService){ }

    ngOnInit(): void {
     this. getSuperHeroes();


    }
    getSuperHeroes(): void {
      this.superHeroService.getAllSuperHeroes()
          .subscribe((superHeroesData) => {
                this.superHeroes = superHeroesData
          })
    }
}
