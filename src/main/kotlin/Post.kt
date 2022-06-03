data class Post(
    var id: Int,                        //Идентификатор записи.
    val ownerId: Int,                   //Идентификатор владельца стены, на которой размещена запись.
    val fromId: Int,                    //Идентификатор автора записи
    val date: Int,                      //Время публикации записи
    val text: String,                   //Текст записи.
    var comments: Array<Comments>?,             //Информация о комментариях к записи, объект с полями:
    val copyright: String?,             //Источник материала, объект с полями:
    val likes: Long,                    //Информация о лайках к записи, объект с полями:
    val reposts: Repost,                //Информация о репостах записи («Рассказать друзьям»), объект с полями:
    val views: Long,                    //Информация о просмотрах записи.
    val postType: String,               //Тип записи, может принимать следующие значения: post, copy, reply, postpone, suggest.
    val createdBy: Int? = null,         //Идентификатор администратора, который опубликовал запись (возвращается только для сообществ при запросе с ключом доступа администратора)
    val attachment: Array<Attachment> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (ownerId != other.ownerId) return false
        if (fromId != other.fromId) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (comments != other.comments) return false
        if (copyright != other.copyright) return false
        if (likes != other.likes) return false
        if (reposts != other.reposts) return false
        if (views != other.views) return false
        if (postType != other.postType) return false
        if (createdBy != other.createdBy) return false
        if (!attachment.contentEquals(other.attachment)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + ownerId
        result = 31 * result + fromId
        result = 31 * result + date
        result = 31 * result + text.hashCode()
        result = 31 * result + comments.hashCode()
        result = 31 * result + (copyright?.hashCode() ?: 0)
        result = 31 * result + likes.hashCode()
        result = 31 * result + reposts.hashCode()
        result = 31 * result + views.hashCode()
        result = 31 * result + postType.hashCode()
        result = 31 * result + (createdBy ?: 0)
        result = 31 * result + attachment.contentHashCode()
        return result
    }
}

data class Repost(val count: Int = 0, val userReposts: Boolean = false)

data class Comments(var count: Int = 0, val fromId: Int, val date: Int, val comment: String)

object WallService {
    private var id = 0
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comments>()

    fun createComment(postId: Int, comment: Comments): Comments {
        for ((index) in posts.withIndex())
            if (postId == posts[index].id){
                val post = posts[index]
                post.comments = post.comments?.plus(comment)

            } else {
                throw PostNotFoundException("Ошибка! Пост с id = $postId не существует")
            }
        return comment
    }

    fun addPost(post: Post): Post {
        id++
        val post = post.copy(id = id)
        posts += post
        return posts.last()
    }

    fun updatePost(post: Post): Boolean {
        for ((index, item) in posts.withIndex()) {
            if (post.id == item.id) {
                posts[index] = post.copy(
                    ownerId = post.ownerId,
                    date = post.date
                )
                return true
            }
        }
        return false
    }

    fun printAttachments(post: Post) {
        for (attachment in post.attachment) {
            println(attachment)
        }
    }

    fun printAllPosts(){
        for(post in posts){
            println(post)
        }
    }
}

fun main() {
    val post = Post(
        2, 2, 2, 30, "post", null, null, 12, Repost(), 0, "post", 21,
        arrayOf(
            AudioAttachment(Audio(1, 2, "www", "singer", null, 130)),
            VideoAttachment(Video(2, 3, "www", "video", "", 200)),
            PhotoAttachment(Photo(3, 1, "www.ru"))
        )
    )
    WallService.addPost(post)
    WallService.printAllPosts()

    WallService.printAttachments(post)
}