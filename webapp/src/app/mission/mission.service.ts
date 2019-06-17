
import 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Mission} from './mission';
import {HttpParams} from "@angular/common/http";
@Injectable()
export class MissionService{

  private missionsUrl: string;
  private createMissionUrl: string;

  constructor(private http: HttpClient){
    this.missionsUrl = 'http://localhost:8085/getAllMissions';
    this.createMissionUrl  = 'http://localhost:8085/createMission';
  }

  public getAllMissions(): Observable<Mission[]>{
      return this.http.get<Mission[]>(this.missionsUrl);
  }
  public saveMission(mission: Mission) {
    return this.http.post<Mission>(this.createMissionUrl,  mission);
}

}
