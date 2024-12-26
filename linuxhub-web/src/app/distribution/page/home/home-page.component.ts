import { Component, inject, OnInit } from "@angular/core";
import { NavBarComponent } from "../../../shared/components/navbar/navbar-component";
import { DistributionsService } from "../../services/distributions-service";
import { catchError, delay, map, Observable, of, startWith } from "rxjs";
import { Distribution } from "../../interfaces";
import { CommonModule } from "@angular/common";

@Component({
  imports: [
    NavBarComponent,
    CommonModule,
  ],
  styles: ``,
  template: `
    <shared-navbar-component></shared-navbar-component>
    <div class="container mx-auto">
    <ng-container *ngIf="distributions$ | async as res">
      <div *ngIf="res.loading; else content">
        <p class="text-center font-bold text-2xl">Cargando información...</p>
      </div>
      <ng-template #content>
        <div *ngIf="res.err; else distributions;">
          {{ res.err }}
        </div>
        <ng-template #distributions>
          @if (res.data.length <= 0) {
            <p class="text-center font-bold text-base">Sin distribuciones que mostrar <mark>:'(</mark></p>
          } @else {
            <ng-container>
            <div class="container m-auto grid grid-cols-1 lg:grid-cols-3 gap-10">
              @for (item of res.data; track $index) {
                <div class="flex flex-col justify-start gap-4 bg-slate-600 text-white border-2 border-red-900 rounded shadow-lg shadow-slate-700 lg:flex-row">
                  <div class="bg-slate-800 justify-items-center">
                    <img class="size-32 rounded-md object-scale-down hover:scale-75" :src="{{ item.logo }}" :alt="{{ item.logo }}" :srcset="{{ item.logo }}" />
                  </div>
                  <div class="flex flex-col align-middle justify-start gap-4">
                    <a class="font-black shadow-rose-700 shadow-lg text-end pr-4 rounded-md mt-2 text-white hover:text-yellow-400" :href="{{ item.official_site }}" target="_blank">{{ item.name }}</a>
                    <p class="w-full max-w-60 text-wrap whitespace-break-spaces hidden lg:block">{{ item.description }}</p>
                    <ul class="p-4">
                      <li>Detalles técnicos</li>
                      <li *ngFor="let tec of item.technician | keyvalue">
                        <span class="text-slate-300 text-base">{{ tec.key }}</span> : <span>{{ tec.value }}</span>
                      </li>
                    </ul>
                  </div>
                </div>
              }
            </div>
            </ng-container>
          }
        </ng-template>
      </ng-template>
    </ng-container>
    </div>
  `,
})
export class HomePageComponent implements OnInit
{
  title = "Home Page";

  distributionService = inject(DistributionsService);

  public distributions$ !:Observable<any>;

  constructor() { }

  ngOnInit(): void {
    this.distributions$ = this.distributionService
      .listDistributions()
      .pipe(
        map( (data: Distribution[] | []) => {
          console.log("async pipe: ", data);
          return {loading: false, data, err: null};
        }),
        startWith({loading: true, data: null, err: null}),
        catchError( (err) => of({ loading: false, data: null, err }) )
      );
  }


}
