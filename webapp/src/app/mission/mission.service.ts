
import 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Mission} from './mission';

@Injectable()
export class MissionService{

  private missionsUrl: string;

  constructor(private http: HttpClient){
    this.missionsUrl = 'http://localhost:8085/getAllMissions';
  }

  public getAllMissions(): Observable<Mission[]>{
      return this.http.get<Mission[]>(this.missionsUrl);
  }

}
