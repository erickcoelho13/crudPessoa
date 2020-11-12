import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EnderecoComponent } from './pages/endereco/endereco.component';
import { PessoaComponent } from './pages/pessoa/pessoa.component';

const routes: Routes = [
  { path: 'pessoas/:id/enderecos', component: EnderecoComponent },
  { path: '', component: PessoaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }