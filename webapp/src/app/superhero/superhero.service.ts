import 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {SuperHero} from './superhero';
import {HttpParams} from "@angular/common/http";
@Injectable()
export class SuperHeroService{

  private superHeroUrl: string;
  private createSuperHeroUrl: string;
  private deleteSuperHeroUrl: string;

  constructor(private http: HttpClient){
    this.superHeroUrl = 'http://localhost:8085/getAllSuperHeroes';
    this.createSuperHeroUrl  = 'http://localhost:8085/createSuperHero';
    this.deleteSuperHeroUrl  = 'http://localhost:8085/deleteSuperHero';
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


}
