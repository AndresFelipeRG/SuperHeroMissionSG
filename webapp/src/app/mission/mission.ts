export class Mission{
  missionName: string;
  superHeroName: string;
  isCompleted: boolean;
  isDeleted: boolean;

  public  setIsDeleted(deleted: boolean){
    this.isDeleted = deleted;
  }
  public  setIsCompleted(completed: boolean){
    this.isCompleted= completed;
  }

}
