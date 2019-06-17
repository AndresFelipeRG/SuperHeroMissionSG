import { Component } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SuperHeroService} from '../superhero/superhero.service';
import {SuperHero} from '..//superhero/superhero';
import { FormGroup, FormControl} from '@angular/forms';

@Component({
  selector: 'app-add-superhero',
  templateUrl: './add-superhero.component.html',
  styleUrls: ['./add-superhero.component.css']
})
export class AddSuperHeroComponent{
  superhero: SuperHero;
  profileForm = new FormGroup({
    missionName: new FormControl(''),
    superHeroName: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl('')
  });
  constructor(private route: ActivatedRoute, private router: Router, private superHeroService: SuperHeroService){
      this.superhero = new SuperHero();
  }
  onSubmit(){

     this.superHeroService.saveSuperHero(this.superhero).subscribe(result => this.navigateToSuperHeroes());
  }
  navigateToSuperHeroes(){
    this.router.navigate(['/superheroes'])
  }
}
