package io.github.damir_tugushev.game_pub_app.backend.controller.game_structure.documentation

import io.github.damir_tugushev.game_pub_app.backend.controller.EntityController
import io.github.damir_tugushev.game_pub_app.backend.model.game_structure.documentation.GameDocumentationTypeEntity
import io.github.damir_tugushev.game_pub_app.backend.service.game_structure.documentation.GameDocumentationTypeService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("game_document_types")
class GameDocumentationTypeController(override val service: GameDocumentationTypeService)
    : EntityController<GameDocumentationTypeEntity, Int>()
