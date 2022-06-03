abstract class Attachment(open val type: Type)

data class AudioAttachment(override val type: Audio) : Attachment(type)
data class VideoAttachment(override val type: Video) : Attachment(type)
data class PhotoAttachment(override val type: Photo) : Attachment(type)
data class DocumentAttachment(override val type: Document) : Attachment(type)
data class NoteAttachment(override val type: Note) : Attachment(type)

interface Type {
    val id: Int
    val ownerId: Int
    val url: String
}

data class Audio(
    override val id: Int,
    override val ownerId: Int,
    override val url: String,
    val artist: String,
    val title: String?,
    val duration: Int
) : Type

data class Video(
    override val id: Int,
    override val ownerId: Int,
    override val url: String,
    val title: String,
    val description: String?,
    val duration: Int
) : Type

data class Photo(
    override val id: Int,
    override val ownerId: Int,
    override val url: String
) : Type

data class Document(
    override val id: Int,
    override val ownerId: Int,
    override val url: String,
    val title: String?,
    val size: Int
) : Type

data class Note(
    override val id: Int,
    override val ownerId: Int,
    override val url: String,
    val text: String,
    val date: Long
) : Type
