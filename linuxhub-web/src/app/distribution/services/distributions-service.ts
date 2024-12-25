import { inject, Injectable } from "@angular/core";
import { environments } from "../../../environments/environments";
import { HttpClient } from "@angular/common/http";
import { catchError, map, Observable, throwError } from "rxjs";
import { DistributionsResponse } from "../interfaces/distributions-response-interface";
import { Distribution } from "../interfaces";

@Injectable({
  providedIn: "root"
})
export class DistributionsService
{
  private ANGULAR_BASEURL_LINUX_DISTRIBUTIONS_API :string = environments.ANGULAR_BASEURL_LINUX_DISTRIBUTIONS_API;

  private http = inject(HttpClient);

  constructor() { }

  public listDistributions() : Observable<Distribution[]>
  {
    return this.http.get<DistributionsResponse>(`${this.ANGULAR_BASEURL_LINUX_DISTRIBUTIONS_API}/v1/distribution`)
    .pipe(
      map(({success, data}) => {
        if(success && data?.items) {
          //console.log(`'DistributionsService::map | start'`);
          //console.log(`'map: '`, {data});
          //console.log(`'map: '`, {success});
          return data?.items;
        }
        return [];
      }),
      catchError( (err) => throwError(() => err.message)),
    );
  }

}
