/**
 * Entrenador.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombre:{
      type:'string',
      required:true, // Por defecto es false
    },
    color:{
      type: 'string',

    },
    nivel:{
      type:'string',

    },
    activo:{
      type:'string',

    },

   pokemones:{
      type:'string',
    }
/*
    pokemons: { // One to Many (plural)
      collection: 'pokemon', // Referencia al modelo
      via: 'entrenador' // Nombre Foreign Key en 'Pokemon'
    }
*/
  },
};
