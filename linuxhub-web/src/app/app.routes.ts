import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import("./distribution/page/home/home-page.component").then( c => c.HomePageComponent),
    pathMatch: 'full',
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '',
  }
];
