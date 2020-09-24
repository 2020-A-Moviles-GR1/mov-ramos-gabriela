/**
 * Entrenador.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
   
    nombre:{
      type: 'string',
      minLength: 3,
      required: true, // Por defecto es false
    },
    color:{
      type: 'string'
      
    },
    nivel:{
      type: 'number'

    },
    activo:{
      type: 'boolean'

    },
   
    pokemons: { // One to Many (plural)
      collection: 'pokemon', // Referencia al modelo
      via: 'entrenador' // Nombre Foreign Key en 'Pokemon'
    }
  },
};