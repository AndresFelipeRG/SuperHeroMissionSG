import {ActivatedRoute, Router} from '@angular/router';
import {SuperHeroService} from '../superhero/superhero.service';
import { SuperHero } from './../superhero/superhero';
import { FormGroup, FormControl} from '@angular/forms';
import { Component, OnInit, Input } from '@angular/core';
@Component({
  selector: 'app-superhero-details',
  templateUrl: './superhero-details.component.html',
  styleUrls: ['./superhero-details.component.css']
})
export class SuperHeroDetailsComponent implements OnInit {
  superHero: SuperHero;
  private sub: any;
  profileForm = new FormGroup({
    _missionName: new FormControl(''),
    _superHeroName: new FormControl(''),
    _firstName: new FormControl(''),
    _lastName: new FormControl('')
  });
  constructor(private route: ActivatedRoute, private router: Router,  private superHeroService: SuperHeroService) { }


  ngOnInit() {
    this.superHero= new SuperHero();
    this.sub = this.route.params.subscribe(params => {

        this.superHero.setMissionName(params['missionName']);
        this.superHero.setSuperHeroName(params['superHeroName']);
        this.superHero.setFirstName(params['firstName']);
        this.superHero.setLastName(params['lastName']);
    });
  }
  onSubmit(){
    this.superHeroService.updateSuperHero(this.superHero).subscribe(result =>this.navigateToSuperHeroes());
  }
  cancel(){
    this.navigateToSuperHeroes();
  }
  navigateToSuperHeroes(){
    this.router.navigate(['/superheroes'])
  }

}
