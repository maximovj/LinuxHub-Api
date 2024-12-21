import { Component } from "@angular/core";

@Component({
  selector: 'shared-navbar-component',
  styles: ``,
  template: `
    <nav class="flex flex-col justify-between m-5 sm:flex-row sm:items-center">
      <div class="text-center">
        <a href="/" class="text-2xl font-bold uppercase">Distribuciones Linux</a>
      </div>
      <ul class="grid grid-cols-1 sm:grid-cols-3">
        <li class="px-4 py-2 text-white bg-slate-700 text-center"><a href="#inicio">Inicio</a></li>
        <li class="px-4 py-2 text-white bg-slate-800 hover:bg-slate-700 text-center"><a href="#buscar">Buscar</a></li>
        <li class="px-4 py-2 text-white bg-slate-800 hover:bg-slate-700 text-center"><a href="#comparar">Comparar</a></li>
      </ul>
    </nav>
  `,
})
export class NavBarComponent
{

}
