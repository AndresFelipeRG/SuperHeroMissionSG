import 'rxjs';

import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {SuperHero} from './superhero';
@Injectable()
export class SuperHeroService{

  private superHeroUrl: string;
  private createSuperHeroUrl: string;
  private deleteSuperHeroUrl: string;
  private updateSuperHeroUrl: string;

  constructor(private http: HttpClient, private route: ActivatedRoute, private router: Router){
    this.superHeroUrl = 'http://localhost:8085/getAllSuperHeroes';
    this.createSuperHeroUrl  = 'http://localhost:8085/createSuperHero';
    this.deleteSuperHeroUrl  = 'http://localhost:8085/deleteSuperHero';
    this.updateSuperHeroUrl = 'http://localhost:8085//updateSuperHero'
  }

  public getAllSuperHeroes(): Observable<SuperHero[]>{
      return this.http.get<SuperHero[]>(this.superHeroUrl);
  }
  public saveSuperHero(superHero: SuperHero) {
    return this.http.post<SuperHero>(this.createSuperHeroUrl, superHero);
  }
  public deleteSuperHero(superHero: SuperHero){
    return this.http.post<SuperHero>(this.deleteSuperHeroUrl, superHero);
  }
  public updateSuperHero(superHero: SuperHero){
    return this.http.post<SuperHero>(this.updateSuperHeroUrl, superHero);
  }
  public navigateToSuperHeroes(){
    this.router.navigate(['/superheroes'])
  }
  public navigateToDetails(superHero: SuperHero){
    this.router.navigate(['/superHeroDetails', superHero.firstName,superHero.lastName, superHero.missionName, superHero.superHeroName])
  }

}
